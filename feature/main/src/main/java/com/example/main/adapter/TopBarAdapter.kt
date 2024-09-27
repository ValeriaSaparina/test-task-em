package com.example.main.adapter

import com.example.base.base.DisplayableItem
import com.example.presentation.databinding.ItemTopBarBinding
import com.example.presentation.item.TopBarItem
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

fun topBarAdapterDelegate() =
    adapterDelegateViewBinding<TopBarItem, DisplayableItem, ItemTopBarBinding>(
        { layoutInflater, root ->
            ItemTopBarBinding.inflate(layoutInflater, root, false)
        }
    ) {}