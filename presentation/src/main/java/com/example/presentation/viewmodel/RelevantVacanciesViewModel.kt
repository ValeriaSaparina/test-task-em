package com.example.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.base.base.BaseViewModel
import com.example.domain.usecase.vacancy.AddFavoriteVacancyUseCase
import com.example.domain.usecase.vacancy.DeleteFavoriteVacancyUseCase
import com.example.domain.usecase.vacancy.GetVacanciesUseCase
import com.example.presentation.mapper.UiDomainMapper
import com.example.presentation.model.VacancyUiModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class RelevantVacanciesViewModel @Inject constructor(
    private val getVacanciesUseCase: GetVacanciesUseCase,
    private val addFavoriteVacancyUseCase: AddFavoriteVacancyUseCase,
    private val deleteFavoriteVacancyUseCase: DeleteFavoriteVacancyUseCase,
    private val mapper: UiDomainMapper,
) : BaseViewModel() {

    private val _vacancies: MutableStateFlow<List<VacancyUiModel>> = MutableStateFlow(emptyList())
    val vacancies: StateFlow<List<VacancyUiModel>> get() = _vacancies

    fun getVacancies() {
        viewModelScope.launch {
            _loading.emit(true)
            getVacanciesUseCase.invoke()
                .onSuccess { vacancies ->
                    _vacancies.emit(vacancies.map { vacancyDomainModel ->
                        mapper.toUiModel(
                            vacancyDomainModel
                        )
                    })
                }
                .onFailure {
                    Log.d("RELEVANT-SCREEN", "$it")
                    _error.emit(true)
                }
            _loading.emit(false)
        }
    }

    fun onFavIconClicked(vacancy: VacancyUiModel) {
        viewModelScope.launch {
            if (vacancy.isFavorite) {
                addFavoriteVacancyUseCase.invoke(mapper.toDomainModel(vacancy))
            } else {
                deleteFavoriteVacancyUseCase.invoke(mapper.toDomainModel(vacancy))
            }
        }
    }

}
