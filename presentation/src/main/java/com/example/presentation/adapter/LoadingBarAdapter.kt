package com.example.presentation.adapter

import com.example.base.base.DisplayableItem
import com.example.presentation.databinding.ItemLoadingBarBinding
import com.example.presentation.item.LoadingBarItem
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

fun loadingAdapter() =
    adapterDelegateViewBinding<LoadingBarItem, DisplayableItem, ItemLoadingBarBinding>(
        { layoutInflater, root ->
            ItemLoadingBarBinding.inflate(layoutInflater, root, false)
        }
    ) {}