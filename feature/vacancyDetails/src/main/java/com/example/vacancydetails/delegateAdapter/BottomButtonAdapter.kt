package com.example.vacancydetails.delegateAdapter

import android.content.res.ColorStateList
import com.example.base.base.DisplayableItem
import com.example.presentation.databinding.ItemBottomBtnBinding
import com.example.presentation.item.BottomButtonItem
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import com.example.common.R as baseR

fun bottomButtonAdapter(
    onButtonClicked: () -> Unit
) =
    adapterDelegateViewBinding<BottomButtonItem, DisplayableItem, ItemBottomBtnBinding>(
        { layoutInflater, root ->
            ItemBottomBtnBinding.inflate(layoutInflater, root, false)
        }
    ) {
        bind {
            with(binding.moreVacanciesBtn) {
                text = resources.getString(baseR.string.respond)
                backgroundTintList =
                    ColorStateList.valueOf(resources.getColor(baseR.color.green, context.theme))
                setOnClickListener {
                    onButtonClicked()
                }
            }
        }
    }