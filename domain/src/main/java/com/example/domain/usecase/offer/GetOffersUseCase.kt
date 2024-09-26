package com.example.domain.usecase.offer

import com.example.base.util.runSuspendCatching
import com.example.domain.model.OfferDomainModel
import com.example.domain.repository.OfferRepository
import javax.inject.Inject

class GetOffersUseCase @Inject constructor(
    private val offerRepository: OfferRepository
) {
    suspend operator fun invoke(): Result<List<OfferDomainModel>> {
        return runSuspendCatching {
            offerRepository.getOffers()
        }
    }
}