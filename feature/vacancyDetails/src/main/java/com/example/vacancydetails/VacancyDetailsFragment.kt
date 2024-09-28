package com.example.vacancydetails

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.base.base.BaseFragment
import com.example.base.util.Constants
import com.example.base.util.Constants.VACANCY_ID
import com.example.di.coreComponent
import com.example.login.di.DaggerVacancyDetailsComponent
import com.example.presentation.item.BottomButtonItem
import com.example.presentation.item.ContentItem
import com.example.presentation.item.LoadingBarItem
import com.example.presentation.model.VacancyUiModel
import com.example.presentation.viewmodel.VacancyDetailsViewModel
import com.example.vacancydetails.databinding.FragmentVacancyDetailsBinding
import javax.inject.Inject
import com.example.common.R as baseR
import com.example.navigation.R as navR

class VacancyDetailsFragment :
    BaseFragment(R.layout.fragment_vacancy_details) {

    private var isUpdated = false

    private val viewBinding: FragmentVacancyDetailsBinding by viewBinding(
        FragmentVacancyDetailsBinding::bind
    )

    @Inject
    lateinit var factory: ViewModelProvider.Factory
    private val viewModel: VacancyDetailsViewModel by viewModels { factory }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        DaggerVacancyDetailsComponent.factory().create(coreComponent()).inject(this)
    }

    private val vacancyAdapter = VacancyDetailsAdapter(
        onRespondButtonClicked = ::onRespondButtonClicked,
        onBackIconClicked = ::onBackIconClicked,
        onWatchIconClicked = ::onWatchIconClicked,
        onShareIconClicked = ::onShareIconClicked,
        onFavIconClicked = ::onFavIconClicked
    )

    private var currentVacancy: VacancyUiModel? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservers()
        initAdapter()
        viewModel.getVacancy(arguments?.getString(VACANCY_ID) ?: "")
    }

    private fun initObservers() {
        with(viewModel) {
            vacancy.observe { vacancy ->
                if (vacancy != null) {
                    currentVacancy = vacancy
                    showData(vacancy)
                    Log.d("DETAILS", "$vacancy")
                }
            }
            loading.observe {
                if (it) {
                    vacancyAdapter.items = vacancyAdapter.items.orEmpty().toMutableList().apply {
                        add(LoadingBarItem())
                    }
                    vacancyAdapter.notifyItemInserted(0)
                }
            }
        }
    }

    private fun initAdapter() {
        with(viewBinding.detailsRv) {
            adapter = vacancyAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }

    private fun showData(vacancy: VacancyUiModel) {
        var position = -1
        vacancyAdapter.items = vacancyAdapter.items?.toMutableList()?.apply {
            position = indexOf(
                find { item -> item is LoadingBarItem }
            )
            removeAt(position)
        }
        vacancyAdapter.notifyItemRemoved(position)

        vacancyAdapter.items = vacancyAdapter.items.orEmpty().toMutableList().apply {
            add(vacancy.toContentItem())
            addAll(vacancy.toQuestionsItem())
            add(BottomButtonItem(resources.getString(baseR.string.respond)))
        }
        vacancyAdapter.notifyItemRangeInserted(0, vacancyAdapter.itemCount)
    }

    private fun onRespondButtonClicked() {
        findNavController().navigate(
            navR.id.action_vacancyDetailsFragment_to_respondBottomSheet,
            Bundle().apply {
                putString(VACANCY_ID, currentVacancy?.id)
                putString(Constants.VACANCY_NAME, currentVacancy?.company)
            })
    }

    private fun onBackIconClicked() {
        if (isUpdated) setFragmentResult(
            "requestKey", bundleOf(
                VACANCY_ID to arguments?.getString(VACANCY_ID)
            )
        )
        findNavController().popBackStack()
    }

    private fun onWatchIconClicked() {
        Log.d("DETAILS-SCREEN", "Eye icon was clicked")
    }

    private fun onShareIconClicked() {
        Log.d("DETAILS-SCREEN", "Share icon was clicked")

    }

    private fun onFavIconClicked(contentItem: ContentItem) {
        viewModel.onFavIconClicked(contentItem)
        updateFavBadge(contentItem.isFavorite)
        isUpdated = !isUpdated
    }
}