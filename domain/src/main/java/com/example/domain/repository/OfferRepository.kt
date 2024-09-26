package com.example.domain.repository

import com.example.domain.model.OfferDomainModel

interface OfferRepository {
    suspend fun getOffers(): List<OfferDomainModel>
}
