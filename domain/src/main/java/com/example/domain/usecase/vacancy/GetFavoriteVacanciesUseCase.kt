package com.example.domain.usecase.vacancy

import com.example.base.util.runSuspendCatching
import com.example.domain.model.VacancyDomainModel
import com.example.domain.repository.VacancyRepository
import javax.inject.Inject

class GetFavoriteVacanciesUseCase @Inject constructor(
    private val vacancyRepository: VacancyRepository
) {
    suspend operator fun invoke(): Result<List<VacancyDomainModel>> {
        return runSuspendCatching {
            vacancyRepository.getAllFavoriteVacancies()
        }
    }
}