package com.example.presentation.item

import com.example.base.base.DisplayableItem

data class ContentItem(
    val id: String,
    val lookingNumber: Int,
    val title: String,
    val address: String,
    val company: String,
    val experience: String,
    val publishedDate: String,
    var isFavorite: Boolean,
    val salary: String,
    val schedules: String,
    val appliedNumber: Int,
    val description: String,
    val responsibilities: String,
) : DisplayableItem
