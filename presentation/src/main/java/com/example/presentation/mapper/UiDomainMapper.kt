package com.example.presentation.mapper

import com.example.domain.model.AddressDomainModel
import com.example.domain.model.ButtonDomainModel
import com.example.domain.model.ExperienceDomainModel
import com.example.domain.model.OfferDomainModel
import com.example.domain.model.SalaryDomainModel
import com.example.domain.model.VacancyDomainModel
import com.example.presentation.model.AddressUiModel
import com.example.presentation.model.ButtonUiModel
import com.example.presentation.model.ExperienceUiModel
import com.example.presentation.model.OfferUiModel
import com.example.presentation.model.SalaryUiModel
import com.example.presentation.model.VacancyUiModel
import javax.inject.Inject

class UiDomainMapper @Inject constructor() {

    fun toUiModel(domainModel: VacancyDomainModel): VacancyUiModel {
        return with(domainModel) {
            VacancyUiModel(
                id = id,
                lookingNumber = lookingNumber,
                title = title,
                address = toUiModel(address),
                company = company,
                experience = toUiModel(experience),
                publishedDate = publishedDate,
                isFavorite = isFavorite,
                salary = toUiModel(salary),
                schedules = schedules,
                appliedNumber = if (appliedNumber == 0) "" else appliedNumber.toString(),
                description = description,
                responsibilities = responsibilities,
                questions = questions
            )
        }
    }


    fun toUiModel(domainModel: ExperienceDomainModel): ExperienceUiModel {
        return with(domainModel) {
            ExperienceUiModel(
                previewText = previewText,
                text = text
            )
        }
    }

    fun toUiModel(domainModel: SalaryDomainModel): SalaryUiModel {
        return with(domainModel) {
            SalaryUiModel(
                short = short,
                full = full
            )
        }
    }

    fun toUiModel(domainModel: AddressDomainModel): AddressUiModel {
        return with(domainModel) {
            AddressUiModel(
                town = town,
                street = street,
                house = house
            )
        }
    }


    fun toDomainModel(uiModel: VacancyUiModel): VacancyDomainModel {
        return with(uiModel) {
            VacancyDomainModel(
                id = id,
                lookingNumber = lookingNumber,
                title = title,
                address = toDomainModel(address),
                company = company,
                experience = toDomainModel(experience),
                publishedDate = publishedDate,
                isFavorite = isFavorite,
                salary = toDomainModel(salary),
                schedules = schedules,
                appliedNumber = if (appliedNumber.isEmpty()) 0 else appliedNumber.toInt(),
                description = description,
                responsibilities = responsibilities,
                questions = questions
            )
        }
    }

    fun toDomainModel(uiModel: AddressUiModel): AddressDomainModel {
        return with(uiModel) {
            AddressDomainModel(
                town = town,
                street = street,
                house = house
            )
        }
    }

    fun toDomainModel(uiModel: ExperienceUiModel): ExperienceDomainModel {
        return with(uiModel) {
            ExperienceDomainModel(
                previewText = previewText,
                text = text
            )
        }
    }

    fun toDomainModel(uiModel: SalaryUiModel): SalaryDomainModel {
        return with(uiModel) {
            SalaryDomainModel(
                short = short,
                full = full
            )
        }
    }

    fun toUiModel(domainModel: ButtonDomainModel): ButtonUiModel {
        return with(domainModel) {
            ButtonUiModel(text = text)
        }
    }

    fun toUiModel(domainModel: OfferDomainModel): OfferUiModel {
        return with(domainModel) {
            OfferUiModel(
                id = id,
                title = title,
                link = link,
                button = toUiModel(button)
            )
        }
    }

}