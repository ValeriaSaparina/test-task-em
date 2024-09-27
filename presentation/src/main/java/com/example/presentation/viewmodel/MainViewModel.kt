package com.example.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.base.base.BaseViewModel
import com.example.domain.usecase.offer.GetOffersUseCase
import com.example.domain.usecase.vacancy.AddFavoriteVacancyUseCase
import com.example.domain.usecase.vacancy.DeleteFavoriteVacancyUseCase
import com.example.domain.usecase.vacancy.GetVacanciesUseCase
import com.example.presentation.mapper.UiDomainMapper
import com.example.presentation.model.OfferUiModel
import com.example.presentation.model.VacancyUiModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val getOffersUseCase: GetOffersUseCase,
    private val getVacanciesUseCase: GetVacanciesUseCase,
    private val addFavoriteVacancyUseCase: AddFavoriteVacancyUseCase,
    private val deleteFavoriteVacancyUseCase: DeleteFavoriteVacancyUseCase,
    private val mapper: UiDomainMapper
) : BaseViewModel() {

    private val _vacanciesLoading: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val vacanciesLoading: StateFlow<Boolean> get() = _vacanciesLoading


    private val _offersLoading: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val offersLoading: StateFlow<Boolean> get() = _offersLoading

    private val _vacancies: MutableStateFlow<List<VacancyUiModel>?> = MutableStateFlow(null)
    val vacancies: StateFlow<List<VacancyUiModel>?> get() = _vacancies

    private val _offers: MutableStateFlow<List<OfferUiModel>?> = MutableStateFlow(null)
    val offers: StateFlow<List<OfferUiModel>?> get() = _offers


    fun getVacancies() {
        viewModelScope.launch {
            _vacanciesLoading.emit(true)
            getVacanciesUseCase.invoke()
                .onSuccess { vacancies ->
                    _vacancies.emit(vacancies.map { vacancyDomainModel ->
                        mapper.toUiModel(
                            vacancyDomainModel
                        )
                    })
                }
                .onFailure {
                    Log.d("MAIN-SCREEN", "$it")
                    _error.emit(true)
                }
            _vacanciesLoading.emit(false)
        }
    }

    fun getOffers() {
        viewModelScope.launch {
            _offersLoading.emit(true)
            getOffersUseCase.invoke()
                .onSuccess { offers ->
                    _offers.emit(offers.map { offerDomainModel -> mapper.toUiModel(offerDomainModel) })
                }
                .onFailure {
                    Log.d("MAIN-SCREEN", "$it")
                    _error.emit(true)
                }
            _offersLoading.emit(false)
        }
    }

    fun onFavIcClicked(vacancy: VacancyUiModel) {
        viewModelScope.launch {
            if (vacancy.isFavorite) {
                addFavoriteVacancyUseCase.invoke(mapper.toDomainModel(vacancy))
            } else {
                deleteFavoriteVacancyUseCase.invoke(mapper.toDomainModel(vacancy))
            }
        }
    }
}