package com.example.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.base.base.BaseViewModel
import com.example.domain.usecase.vacancy.AddFavoriteVacancyUseCase
import com.example.domain.usecase.vacancy.DeleteFavoriteVacancyUseCase
import com.example.domain.usecase.vacancy.GetVacancyByIdUseCase
import com.example.presentation.item.ContentItem
import com.example.presentation.mapper.UiDomainMapper
import com.example.presentation.model.VacancyUiModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class VacancyDetailsViewModel @Inject constructor(
    private val getVacancyByIdUseCase: GetVacancyByIdUseCase,
    private val addFavoriteVacancyUseCase: AddFavoriteVacancyUseCase,
    private val deleteFavoriteVacancyUseCase: DeleteFavoriteVacancyUseCase,
    private val mapper: UiDomainMapper
) : BaseViewModel() {

    private val _vacancy: MutableStateFlow<VacancyUiModel?> = MutableStateFlow(null)
    val vacancy: StateFlow<VacancyUiModel?> get() = _vacancy

    fun getVacancy(id: String) {
        viewModelScope.launch {
            _loading.emit(true)
            getVacancyByIdUseCase.invoke(id)
                .onSuccess { vacancyDomainModel ->
                    _vacancy.emit(mapper.toUiModel(vacancyDomainModel))
                }
                .onFailure {
                    Log.d("VACANCY-DETAILS-SCREEN", "${it.message}")
                    _error.emit(true)
                }
            _loading.emit(false)
        }
    }

    fun onFavIconClicked(vacancy: ContentItem) {
        viewModelScope.launch {
            if (vacancy.isFavorite) {
                addFavoriteVacancyUseCase.invoke(vacancy.id)
            } else {
                deleteFavoriteVacancyUseCase.invoke(vacancy.id)
            }
        }
    }

}
