package com.example.data.repository

import com.example.data.remote.DataApi
import com.example.data.util.Constants
import com.example.domain.model.OfferDomainModel
import com.example.domain.repository.OfferRepository
import javax.inject.Inject

class OfferRepositoryImpl @Inject constructor(
    private val dataApi: DataApi,
) : OfferRepository {
    override suspend fun getOffers(): List<OfferDomainModel> {
        return dataApi.getData(
            Constants.API_ID,
            Constants.EXPORT_API
        )?.offers?.map { offerDataModel ->
            offerDataModel.toDomainModel()
        } ?: listOf()
    }
}