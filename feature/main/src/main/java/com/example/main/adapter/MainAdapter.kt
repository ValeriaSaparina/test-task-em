package com.example.main.adapter

import com.example.base.base.DisplayableItem
import com.example.presentation.adapter.loadingAdapter
import com.example.presentation.model.VacancyUiModel
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter

class MainAdapter(
    recommendationAdapter: RecommendationAdapter,
    onMoreButtonClicked: () -> Unit,
    onFavIcClicked: (VacancyUiModel) -> Unit,
    onVacancyItemClicked: (String) -> Unit,
    onRespondButtonClicked: (VacancyUiModel) -> Unit,
) : ListDelegationAdapter<List<DisplayableItem>>(
    loadingAdapter(),
    topBarAdapterDelegate(),
    recommendationsHorizontalAdapterDelegate(recommendationAdapter),
    vacanciesTitleAdapter(),
    vacanciesAdapterDelegate(onFavIcClicked, onVacancyItemClicked, onRespondButtonClicked),
    bottomButtonAdapter(onMoreButtonClicked)
)