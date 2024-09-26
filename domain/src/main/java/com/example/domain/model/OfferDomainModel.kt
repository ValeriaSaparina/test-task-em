package com.example.domain.model


data class OfferDomainModel(
    val id: String,
    val title: String,
    val link: String,
    val button: ButtonDomainModel
) {

    companion object {
        fun getDefaultValue(): OfferDomainModel {
            return OfferDomainModel(
                id = "",
                title = "",
                link = "",
                button = ButtonDomainModel.getDefaultValue()
            )
        }
    }
}


data class ButtonDomainModel(
    val text: String
) {


    companion object {
        fun getDefaultValue(): ButtonDomainModel {
            return ButtonDomainModel(
                text = ""
            )
        }
    }
}

