package com.example.domain.usecase.vacancy

import com.example.base.util.runSuspendCatching
import com.example.domain.model.VacancyDomainModel
import com.example.domain.repository.VacancyRepository
import javax.inject.Inject

class AddFavoriteVacancyUseCase @Inject constructor(
    private val vacancyRepository: VacancyRepository
) {
    suspend operator fun invoke(vacancy: VacancyDomainModel) {
        runSuspendCatching {
            vacancyRepository.addAllFavoriteVacancies(vacancy)
        }
    }

    suspend operator fun invoke(id: String) {
        runSuspendCatching {
            vacancyRepository.addAllFavoriteVacancies(id)
        }
    }
}