package com.example.data.remote.response

import com.example.domain.model.ButtonDomainModel
import com.example.domain.model.OfferDomainModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class OfferDataModel(
    @SerialName("id") val id: String? = null,
    @SerialName("title") val title: String,
    @SerialName("link") val link: String,
    @SerialName("button") val button: ButtonResponse? = null
) {
    fun toDomainModel(): OfferDomainModel {
        return with(this) {
            OfferDomainModel(
                id = id ?: "",
                title = title,
                link = link,
                button = button?.toDomainModel() ?: ButtonDomainModel.getDefaultValue()

            )
        }
    }
}

@Serializable
data class ButtonResponse(
    @SerialName("text") val text: String
) {
    fun toDomainModel(): ButtonDomainModel {
        return with(this) {
            ButtonDomainModel(
                text = text
            )
        }
    }
}
