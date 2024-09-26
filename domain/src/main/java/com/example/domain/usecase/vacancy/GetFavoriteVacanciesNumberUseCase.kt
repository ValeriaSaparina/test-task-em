package com.example.domain.usecase.vacancy

import com.example.base.util.runSuspendCatching
import com.example.domain.repository.VacancyRepository
import javax.inject.Inject

class GetFavoriteVacanciesNumberUseCase @Inject constructor(
    private val vacancyRepository: VacancyRepository
) {
    suspend operator fun invoke(): Result<Int> {
        return runSuspendCatching {
            vacancyRepository.getFavoriteVacanciesNumber()
        }
    }
}