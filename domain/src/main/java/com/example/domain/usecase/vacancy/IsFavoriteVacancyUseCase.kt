package com.example.domain.usecase.vacancy

import com.example.base.util.runSuspendCatching
import com.example.domain.repository.VacancyRepository
import javax.inject.Inject

class IsFavoriteVacancyUseCase @Inject constructor(
    private val vacancyRepository: VacancyRepository
) {
    suspend operator fun invoke(id: String): Result<Boolean> {
        return runSuspendCatching {
            vacancyRepository.getFavoriteVacancyById(id).id != ""
        }
    }
}
