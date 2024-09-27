package com.example.main.adapter

import com.example.base.base.DisplayableItem
import com.example.presentation.databinding.ItemTitleBinding
import com.example.presentation.item.VacanciesTitleItem
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

fun vacanciesTitleAdapter() =
    adapterDelegateViewBinding<VacanciesTitleItem, DisplayableItem, ItemTitleBinding>(
        { layoutInflater, root ->
            ItemTitleBinding.inflate(layoutInflater, root, false)
        }
    ) {}
