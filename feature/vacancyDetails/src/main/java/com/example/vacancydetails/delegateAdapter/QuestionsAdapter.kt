package com.example.vacancydetails.delegateAdapter

import com.example.base.base.DisplayableItem
import com.example.presentation.databinding.ItemQuestionBinding
import com.example.presentation.item.QuestionItem
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding


fun questionsAdapter() =
    adapterDelegateViewBinding<QuestionItem, DisplayableItem, ItemQuestionBinding>(
        { layoutInflater, root ->
            ItemQuestionBinding.inflate(layoutInflater, root, false)
        }
    ) {
        bind {
            binding.questionTextTv.text = item.text
        }
    }