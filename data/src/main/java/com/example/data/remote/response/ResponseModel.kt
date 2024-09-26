package com.example.data.remote.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseModel(
    @SerialName("offers") val offers: List<OfferDataModel>,
    @SerialName("vacancies") val vacancies: List<VacancyDataModel>
)