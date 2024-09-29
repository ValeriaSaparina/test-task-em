package com.example.relevantvacancies

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.base.base.BaseFragment
import com.example.base.util.Constants.VACANCY_ID
import com.example.di.coreComponent
import com.example.presentation.adapter.FavoriteAdapter
import com.example.presentation.model.VacancyUiModel
import com.example.presentation.viewmodel.RelevantVacanciesViewModel
import com.example.relevantvacancies.databinding.FragmentRelevantVacanciesBinding
import com.example.relevantvacancies.di.DaggerRelevantVacancyComponent
import javax.inject.Inject
import com.example.common.R as baseR
import com.example.navigation.R as navR

class RelevantVacanciesFragment : BaseFragment(R.layout.fragment_relevant_vacancies) {
    private val viewBinding: FragmentRelevantVacanciesBinding by viewBinding(
        FragmentRelevantVacanciesBinding::bind
    )
    private val adapterVacancies = FavoriteAdapter(
        onFavIcClicked = ::onFavIconClicked,
        onVacancyItemClicked = ::onVacancyClicked,
        onRespondButtonClicked = ::onRespondButtonClicked
    )

    private var observed = false

    @Inject
    lateinit var factory: ViewModelProvider.Factory
    private val viewModel: RelevantVacanciesViewModel by viewModels { factory }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        DaggerRelevantVacancyComponent.factory().create(coreComponent()).inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setFragmentResultListener("requestKey") { requestKey, bundle ->
            updPosition(bundle.getString(VACANCY_ID) ?: "")
        }
    }

    private fun updPosition(id: String) {
        if (id != "") {
            adapterVacancies.submitList(adapterVacancies.currentList.toMutableList().apply {
                for ((index, elem) in this.withIndex()) {
                    if (elem?.id.equals(id)) {
                        this[index] = (elem as VacancyUiModel).copy(isFavorite = !elem.isFavorite)
                        break
                    }
                }
            })
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (!observed) {
            initObservers()
            observed = true
        }
        initView()
        initListeners()
        initAdapter()
        loadData()
    }

    private fun initObservers() {
        with(viewModel) {
            loading.observe { isLoading ->
                viewBinding.progressBar.isVisible = isLoading
                viewBinding.relevantVacanciesRv.isVisible = !isLoading
                viewBinding.filterLl.isVisible = !isLoading
            }

            vacancies.observe { vacancies ->
                if (vacancies.isNotEmpty()) {
                    adapterVacancies.submitList(vacancies)
                    setNumberVacanciesText(vacancies.size)
                    showFavNumberBadge(vacancies.count { it.isFavorite })
                }
            }
        }
    }

    private fun initView() {
        with(viewBinding.topBar) {
            emailEt.hint = resources.getString(baseR.string.position_relevant_vacancies)
            startIc.setImageResource(baseR.drawable.ic_back)
        }
    }

    private fun initListeners() {
        viewBinding.topBar.startIc.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun initAdapter() {
        with(viewBinding.relevantVacanciesRv) {
            adapter = adapterVacancies
            layoutManager = LinearLayoutManager(context)
        }
    }

    private fun loadData() {
        viewModel.getVacancies()
    }

    private fun setNumberVacanciesText(size: Int) {
        val vacanciesStr = if (size == 1) {
            baseR.string.one_vacancy
        } else {
            if (size in 2..4) {
                baseR.string.few_vacancies
            } else {
                baseR.string.many_vacancies
            }
        }
        viewBinding.numberOfVacanciesTv.text = resources.getString(vacanciesStr, size)
    }

    private fun onVacancyClicked(vacancyId: String) {
        findNavController().navigate(navR.id.vacancyDetailsFragment, Bundle().apply {
            putString(VACANCY_ID, vacancyId)
        })
    }

    private fun onFavIconClicked(vacancy: VacancyUiModel) {
        viewModel.onFavIconClicked(vacancy)
        updateFavBadge(vacancy.isFavorite)
    }

    private fun onRespondButtonClicked(vacancy: VacancyUiModel) {
        findNavController().navigate(
            navR.id.action_relevantVacanciesFragment_to_respondBottomSheet,
            Bundle().apply {
                putString(VACANCY_ID, vacancy.id)
                putString(com.example.base.util.Constants.VACANCY_NAME, vacancy.company)
            })
    }
}