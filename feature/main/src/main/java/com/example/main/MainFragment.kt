package com.example.main

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.base.base.BaseActivity
import com.example.base.base.BaseFragment
import com.example.base.util.Constants.VACANCY_ID
import com.example.base.util.Constants.VACANCY_NAME
import com.example.di.coreComponent
import com.example.main.adapter.MainAdapter
import com.example.main.adapter.RecommendationAdapter
import com.example.main.databinding.FragmentMainBinding
import com.example.main.di.DaggerMainComponent
import com.example.presentation.item.BottomButtonItem
import com.example.presentation.item.HorizontalItem
import com.example.presentation.item.LoadingBarItem
import com.example.presentation.item.TopBarItem
import com.example.presentation.item.VacanciesTitleItem
import com.example.presentation.model.OfferUiModel
import com.example.presentation.model.VacancyUiModel
import com.example.presentation.viewmodel.MainViewModel
import javax.inject.Inject
import com.example.navigation.R as navR


class MainFragment : BaseFragment(R.layout.fragment_main) {
    private val viewBinding: FragmentMainBinding by viewBinding(FragmentMainBinding::bind)

    private var observed = false

    @Inject
    lateinit var factory: ViewModelProvider.Factory
    private val viewModel: MainViewModel by viewModels { factory }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        DaggerMainComponent.factory().create(coreComponent()).inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setFragmentResultListener("requestKey") { _, bundle ->
            updPosition(bundle.getString(VACANCY_ID) ?: "")
        }
    }

    private fun updPosition(id: String) {
        Log.d("MAIN", "in updPosition(); id = $id")
        if (id != "") {
            var position = -1
            mainAdapter.items = mainAdapter.items?.toMutableList()?.apply {
                for ((index, elem) in this.withIndex()) {
                    if ((elem as? VacancyUiModel)?.id.equals(id)) {
                        Log.d("MAIN", "in updPosition(); index = $index")
                        this[index] = (elem as VacancyUiModel).copy(isFavorite = !elem.isFavorite)
                        position = index
                        break
                    }
                }
            }
            mainAdapter.notifyItemChanged(position)
        }
    }

    private val recommendationAdapter = RecommendationAdapter(::onRecommendationItemClicked)
    private val mainAdapter = MainAdapter(
        recommendationAdapter = recommendationAdapter,
        onMoreButtonClicked = ::onMoreButtonClicked,
        onFavIcClicked = ::onFavIcClicked,
        onVacancyItemClicked = ::onVacancyItemClicked,
        onRespondButtonClicked = ::onRespondButtonClicked
    )

    private fun onMoreButtonClicked() {
        findNavController().navigate(navR.id.relevantVacanciesFragment)
    }

    private fun onFavIcClicked(vacancy: VacancyUiModel) {
        viewModel.onFavIcClicked(vacancy)
        updateFavBadge(vacancy.isFavorite)
    }

    private fun onRecommendationItemClicked(link: String) {
        val intent = Intent(
            Intent.ACTION_VIEW,
            Uri.parse(link)
        )
        startActivity(intent)
    }

    private fun onVacancyItemClicked(id: String) {
        findNavController().navigate(navR.id.vacancyDetailsFragment, Bundle().apply {
            putString(VACANCY_ID, id)
        })
    }

    private fun onRespondButtonClicked(vacancy: VacancyUiModel) {
        findNavController().navigate(
            navR.id.action_mainFragment_to_respondBottomSheet,
            Bundle().apply {
                putString(VACANCY_ID, vacancy.id)
                putString(VACANCY_NAME, vacancy.company)
            })
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity() as BaseActivity).isBottomNavVisible = true
        (requireActivity() as BaseActivity).setupBottomNav()
        showFavNumberBadge()
        initAdapter()
        if (!observed) {
            initObservers()
            observed = true
        }
        showData()

    }

    private fun showData() {

        if (mainAdapter.items.isNullOrEmpty()) {
            viewModel.getOffers()
            viewModel.getVacancies()
            showTopBar()
            showVacanciesTitle()
        }
    }

    private fun showVacanciesTitle() {
        val position = 1
        mainAdapter.items = mainAdapter.items.orEmpty().toMutableList().apply {
            add(position, VacanciesTitleItem())
        }
        mainAdapter.notifyItemInserted(position)
    }

    private fun initAdapter() {
        with(viewBinding.mainRv) {
            adapter = mainAdapter
            layoutManager = LinearLayoutManager(context)
        }

    }

    private fun initObservers() {
        with(viewModel) {
            offersLoading.observe { loading ->
                if (loading) {
                    showProgressBar(1)
                }
            }

            vacanciesLoading.observe { loading ->
                if (loading) {
                    showProgressBar(3)
                }
            }

            offers.observe { offers ->
                if (!offers.isNullOrEmpty()) {
                    showOffers(offers)
                }
            }

            vacancies.observe { vacancies ->
                if (!vacancies.isNullOrEmpty()) {
                    showVacancies(vacancies)
                    showFavNumberBadge(vacancies.count { vacancy -> vacancy.isFavorite })
                }
            }
        }
    }

    private fun showProgressBar(position: Int) {
        mainAdapter.items = mainAdapter.items.orEmpty().toMutableList().apply {
            if (this.find { item -> item is HorizontalItem || item is VacancyUiModel } != null) {
                removeIf { item -> item is VacancyUiModel }
                removeIf { item -> item is HorizontalItem }
                removeIf { item -> item is BottomButtonItem }
                this[position] = LoadingBarItem()
                mainAdapter.notifyItemChanged(position)
            } else {
                add(position, LoadingBarItem())
                mainAdapter.notifyItemInserted(position)
            }
        }
    }

    private fun showTopBar() {
        mainAdapter.items = mainAdapter.items.orEmpty().toMutableList().apply {
            if (this.isEmpty()) {
                add(TopBarItem())
            } else {
                this[0] = TopBarItem()
            }
        }
        mainAdapter.notifyItemInserted(0)
    }

    private fun showOffers(offers: List<OfferUiModel>) {
        recommendationAdapter.submitList(offers)
        var position = 1
        mainAdapter.items = mainAdapter.items?.toMutableList()?.apply {
            position = indexOf(
                find { item -> item is LoadingBarItem }
            )
            position = if (position == -1) 1 else position
            this[position] = HorizontalItem()
        }
        mainAdapter.notifyItemChanged(position)
    }

    private fun showVacancies(vacancies: List<VacancyUiModel>) {
        var position = 1
        mainAdapter.items = mainAdapter.items?.toMutableList()?.apply {
            position = indexOf(
                findLast { item -> item is LoadingBarItem }
            )
            if (position != -1) {
                removeAt(position)
            }
            addAll(vacancies.slice(IntRange(0, 2)))
        }
        mainAdapter.notifyItemRangeChanged(position, vacancies.size + 1)
        showBottomBtn(vacancies.size.toString())
    }

    private fun showBottomBtn(size: String) {
        mainAdapter.items = mainAdapter.items?.toMutableList()?.apply {
            add(BottomButtonItem(size))
        }
        mainAdapter.notifyItemInserted(mainAdapter.items?.size!! - 1)
    }
}

