package com.example.data.mapper

import com.example.data.remote.response.AddressResponse
import com.example.data.remote.response.ExperienceResponse
import com.example.data.remote.response.SalaryResponse
import com.example.data.remote.response.VacancyDataModel
import com.example.domain.model.AddressDomainModel
import com.example.domain.model.ExperienceDomainModel
import com.example.domain.model.SalaryDomainModel
import com.example.domain.model.VacancyDomainModel
import javax.inject.Inject

class DataDomainMapper @Inject constructor() {
    fun toDataModel(domainModel: VacancyDomainModel): VacancyDataModel {
        return with(domainModel) {
            VacancyDataModel(
                id = id,
                lookingNumber = lookingNumber,
                title = title,
                address = toDataModel(address),
                company = company,
                experience = toDataModel(experience),
                publishedDate = publishedDate,
                isFavorite = isFavorite,
                salary = toDataModel(salary),
                schedules = schedules,
                appliedNumber = appliedNumber,
                description = description,
                responsibilities = responsibilities,
                questions = questions

            )
        }
    }

    fun toDataModel(domainModel: AddressDomainModel): AddressResponse {
        return with(domainModel) {
            AddressResponse(
                town = town,
                street = street,
                house = house
            )
        }
    }

    fun toDataModel(domainModel: ExperienceDomainModel): ExperienceResponse {
        return with(domainModel) {
            ExperienceResponse(
                previewText = previewText,
                text = text
            )
        }
    }

    fun toDataModel(domainModel: SalaryDomainModel): SalaryResponse {
        return with(domainModel) {
            SalaryResponse(
                short = short,
                full = full
            )
        }
    }

}