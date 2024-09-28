package com.example.vacancydetails

import com.example.base.base.DisplayableItem
import com.example.presentation.adapter.loadingAdapter
import com.example.presentation.item.ContentItem
import com.example.vacancydetails.delegateAdapter.bottomButtonAdapter
import com.example.vacancydetails.delegateAdapter.contentAdapter
import com.example.vacancydetails.delegateAdapter.questionsAdapter
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter

class VacancyDetailsAdapter(
    onRespondButtonClicked: () -> Unit,
    onBackIconClicked: () -> Unit,
    onWatchIconClicked: () -> Unit,
    onShareIconClicked: () -> Unit,
    onFavIconClicked: (vacancy: ContentItem) -> Unit,
) : ListDelegationAdapter<List<DisplayableItem>>(
    contentAdapter(
        onBackIconClicked = onBackIconClicked,
        onWatchIconClicked = onWatchIconClicked,
        onShareIconClicked = onShareIconClicked,
        onFavIconClicked = onFavIconClicked,
    ),
    questionsAdapter(),
    loadingAdapter(),
    bottomButtonAdapter(onRespondButtonClicked)
)