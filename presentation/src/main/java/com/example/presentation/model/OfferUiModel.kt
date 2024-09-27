package com.example.presentation.model

import com.example.base.base.DisplayableItem

data class OfferUiModel(
    val id: String,
    val title: String,
    val link: String,
    val button: ButtonUiModel
) : DisplayableItem

data class ButtonUiModel(
    val text: String
)