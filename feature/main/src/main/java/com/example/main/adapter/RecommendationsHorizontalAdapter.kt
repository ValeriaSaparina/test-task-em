package com.example.main.adapter

import androidx.recyclerview.widget.LinearLayoutManager
import com.example.base.base.DisplayableItem
import com.example.presentation.databinding.ItemRecommendationsBinding
import com.example.presentation.item.HorizontalItem
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

fun recommendationsHorizontalAdapterDelegate(
    recommendationAdapter: RecommendationAdapter,
) =
    adapterDelegateViewBinding<HorizontalItem, DisplayableItem, ItemRecommendationsBinding>(
        { layoutInflater, root ->
            ItemRecommendationsBinding.inflate(layoutInflater, root, false)
        }) {
        bind {
            with(binding.recommendationsRv) {
                adapter = recommendationAdapter
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            }
        }
    }

