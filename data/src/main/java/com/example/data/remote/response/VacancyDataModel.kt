package com.example.data.remote.response

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.domain.model.AddressDomainModel
import com.example.domain.model.ExperienceDomainModel
import com.example.domain.model.SalaryDomainModel
import com.example.domain.model.VacancyDomainModel
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "favorite_vacancies")
data class VacancyDataModel(
    @PrimaryKey val id: String,
    val lookingNumber: Int? = null,
    val title: String,
    val address: AddressResponse,
    val company: String,
    val experience: ExperienceResponse,
    @ColumnInfo(name = "published_date") val publishedDate: String,
    @ColumnInfo(name = "is_favorite") val isFavorite: Boolean,
    val salary: SalaryResponse,
    val schedules: List<String>,
    @ColumnInfo(name = "applied_number") val appliedNumber: Int? = null,
    val description: String? = null,
    val responsibilities: String,
    val questions: List<String>
) {
    fun toDomainModel(): VacancyDomainModel {
        return with(this) {
            VacancyDomainModel(
                id = id,
                lookingNumber = lookingNumber ?: 0,
                title = title,
                address = address.toDomainModel(),
                company = company,
                experience = experience.toDomainModel(),
                publishedDate = publishedDate,
                isFavorite = isFavorite,
                salary = salary.toDomainModel(),
                schedules = schedules,
                appliedNumber = appliedNumber ?: 0,
                description = description ?: "",
                responsibilities = responsibilities,
                questions = questions

            )
        }
    }
}

@Serializable
data class AddressResponse(
    val town: String,
    val street: String,
    val house: String
) {
    fun toDomainModel(): AddressDomainModel {
        return with(this) {
            AddressDomainModel(
                town = town,
                street = street,
                house = house
            )
        }
    }
}

@Serializable
data class ExperienceResponse(
    val previewText: String,
    val text: String,
) {
    fun toDomainModel(): ExperienceDomainModel {
        return with(this) {
            ExperienceDomainModel(
                previewText = previewText,
                text = text
            )
        }
    }
}

@Serializable
data class SalaryResponse(
    val short: String? = null,
    val full: String? = null
) {
    fun toDomainModel(): SalaryDomainModel {
        return with(this) {
            SalaryDomainModel(
                short = short ?: "",
                full = full ?: ""

            )
        }
    }
}

