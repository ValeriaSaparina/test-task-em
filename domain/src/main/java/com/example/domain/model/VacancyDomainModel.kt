package com.example.domain.model

data class VacancyDomainModel(
    val id: String,
    val lookingNumber: Int,
    val title: String,
    val address: AddressDomainModel,
    val company: String,
    val experience: ExperienceDomainModel,
    val publishedDate: String,
    val isFavorite: Boolean,
    val salary: SalaryDomainModel,
    val schedules: List<String>,
    val appliedNumber: Int,
    val description: String,
    val responsibilities: String,
    val questions: List<String>
) {

    fun isDefault(): Boolean {
        return id == ""
    }


    companion object {
        fun getDefaultValue(): VacancyDomainModel {
            return VacancyDomainModel(
                id = "",
                lookingNumber = 0,
                title = "",
                address = AddressDomainModel.getDefaultValue(),
                company = "",
                experience = ExperienceDomainModel.getDefaultValue(),
                publishedDate = "",
                isFavorite = false,
                salary = SalaryDomainModel.getDefaultValue(),
                schedules = listOf(),
                appliedNumber = 0,
                description = "",
                responsibilities = "",
                questions = listOf()
            )
        }
    }
}

data class AddressDomainModel(
    val town: String,
    val street: String,
    val house: String
) {


    companion object {
        fun getDefaultValue(): AddressDomainModel {
            return AddressDomainModel(
                town = "",
                street = "",
                house = ""
            )
        }
    }
}

data class ExperienceDomainModel(
    val previewText: String,
    val text: String,
) {


    companion object {
        fun getDefaultValue(): ExperienceDomainModel {
            return ExperienceDomainModel(
                previewText = "",
                text = "",
            )
        }
    }
}

data class SalaryDomainModel(
    val short: String,
    val full: String
) {


    companion object {
        fun getDefaultValue(): SalaryDomainModel {
            return SalaryDomainModel(
                short = "",
                full = ""
            )
        }
    }
}