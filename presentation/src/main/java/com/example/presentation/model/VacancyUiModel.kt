package com.example.presentation.model

import com.example.base.base.DisplayableItem
import com.example.presentation.item.ContentItem
import com.example.presentation.item.QuestionItem

data class VacancyUiModel(
    val id: String,
    val lookingNumber: Int,
    val title: String,
    val address: AddressUiModel,
    val company: String,
    val experience: ExperienceUiModel,
    val publishedDate: String,
    var isFavorite: Boolean,
    val salary: SalaryUiModel,
    val schedules: List<String>,
    val appliedNumber: String,
    val description: String,
    val responsibilities: String,
    val questions: List<String>
) : DisplayableItem {


    fun toContentItem(): ContentItem {
        return ContentItem(
            id = id,
            lookingNumber = lookingNumber,
            title = title,
            address = with(address) { "${town}, ${street}, ${house}" },
            company = company,
            experience = experience.text,
            publishedDate = publishedDate,
            isFavorite = isFavorite,
            salary = salary.full,
            schedules = schedules.toString().replace("[", "").replace("]", "")
                .replaceFirstChar { firstChar -> firstChar.uppercaseChar() },
            appliedNumber = if (appliedNumber.isEmpty()) 0 else appliedNumber.toInt(),
            description = description,
            responsibilities = responsibilities,
        )
    }

    fun toQuestionsItem(): List<QuestionItem> {
        return questions.map { questions -> QuestionItem(questions) }
    }

}

data class AddressUiModel(
    val town: String,
    val street: String,
    val house: String
) {

}

data class ExperienceUiModel(
    val previewText: String,
    val text: String,
) {

}

data class SalaryUiModel(
    val short: String,
    val full: String
) {

}
