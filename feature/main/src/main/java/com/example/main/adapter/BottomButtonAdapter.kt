package com.example.main.adapter

import com.example.common.R
import com.example.presentation.databinding.ItemBottomBtnBinding
import com.example.presentation.item.BottomButtonItem
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

fun bottomButtonAdapter(
    onButtonClicked: () -> Unit,
) =
    adapterDelegateViewBinding<BottomButtonItem, com.example.base.base.DisplayableItem, ItemBottomBtnBinding>(
        { layoutInflater, root ->
            ItemBottomBtnBinding.inflate(layoutInflater, root, false)
        }
    ) {
        bind {
            with(binding.moreVacanciesBtn) {
                text = resources.getString(R.string.more_vacancies, item.text)
                setOnClickListener {
                    onButtonClicked()
                }
            }
        }
    }