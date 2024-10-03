package com.example.favorite

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.base.base.BaseFragment
import com.example.base.util.Constants.VACANCY_ID
import com.example.di.coreComponent
import com.example.favorite.databinding.FragmentFavoriteBinding
import com.example.favorite.di.DaggerFavoriteComponent
import com.example.presentation.adapter.FavoriteAdapter
import com.example.presentation.model.VacancyUiModel
import com.example.presentation.viewmodel.FavoriteViewModel
import javax.inject.Inject
import com.example.common.R as baseR
import com.example.navigation.R as navR

class FavoriteFragment : BaseFragment(R.layout.fragment_favorite) {

    private val viewBinding: FragmentFavoriteBinding by viewBinding(FragmentFavoriteBinding::bind)
    private var isObserved = false

    private val favoriteAdapter =
        FavoriteAdapter(::onFavIcClicked, ::onVacancyItemClicked, ::onRespondButtonClicked)

    @Inject
    lateinit var factory: ViewModelProvider.Factory
    private val viewModel: FavoriteViewModel by viewModels { factory }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        DaggerFavoriteComponent.factory().create(coreComponent()).inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (!isObserved) {
            initObservers()
            initAdapter()
            isObserved = true
        }
        getData()
    }

    private fun initObservers() {
        with(viewModel) {
            vacancies.observe { vacancies ->
                if (vacancies != null) {
                    showData(vacancies)
                    showFavNumberBadge(vacancies.count { vacancy -> vacancy.isFavorite })
                }
            }
            loading.observe { isLoading ->
                with(viewBinding) {
                    progressBar.isVisible = isLoading
                    numberOfVacanciesTv.isVisible = !isLoading
                    favVacanciesRv.isVisible = !isLoading
                }
            }
        }
    }

    private fun showData(vacancies: List<VacancyUiModel>) {
        if (vacancies.isNotEmpty()) {
            val favNumber = vacancies.count { vacancy -> vacancy.isFavorite }
            showFavNumberBadge(favNumber)
            setNumberVacanciesText(favNumber)
        }
        favoriteAdapter.submitList(vacancies)
    }

    private fun initAdapter() {
        with(viewBinding.favVacanciesRv) {
            adapter = favoriteAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }


    private fun getData() {
        viewModel.getFavoriteVacancies()
    }

    private fun onFavIcClicked(vacancy: VacancyUiModel) {
        setNumberVacanciesText(favoriteAdapter.itemCount - 1)
        updateFavBadge(vacancy.isFavorite)
        val newList =  favoriteAdapter.currentList.toMutableList().apply { remove(vacancy) }
        favoriteAdapter.submitList(newList)
        viewModel.onFavIcClicked(vacancy)

    }

    private fun onVacancyItemClicked(id: String) {
        findNavController().navigate(navR.id.vacancyDetailsFragment, Bundle().apply {
            putString(
                VACANCY_ID, id
            )
        })
    }

    private fun onRespondButtonClicked(vacancy: VacancyUiModel) {
        findNavController().navigate(
            navR.id.action_favoriteFragment_to_respondBottomSheet,
            Bundle().apply {
                putString(VACANCY_ID, vacancy.id)
                putString(com.example.base.util.Constants.VACANCY_NAME, vacancy.company)
            })
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

}