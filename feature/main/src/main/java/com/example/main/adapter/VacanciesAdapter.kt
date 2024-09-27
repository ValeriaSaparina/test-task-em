package com.example.main.adapter

import android.util.Log
import androidx.core.view.isVisible
import com.example.base.base.DisplayableItem
import com.example.common.R
import com.example.presentation.databinding.ItemVacancyBinding
import com.example.presentation.model.VacancyUiModel
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

fun vacanciesAdapterDelegate(
    onFavIcClicked: (item: VacancyUiModel) -> Unit,
    onVacancyItemClicked: (String) -> Unit,
    onRespondButtonClicked: (VacancyUiModel) -> Unit
) =
    adapterDelegateViewBinding<VacancyUiModel, DisplayableItem, ItemVacancyBinding>(
        { layoutInflater, root ->
            ItemVacancyBinding.inflate(layoutInflater, root, false)
        }
    ) {
        bind {
            with(binding) {
                with(item) {
                    root.setOnClickListener { onVacancyItemClicked(id) }
                    cityTv.text = address.town
                    companyTv.text = company
                    with(dateTv) {
                        text = resources.getString(
                            R.string.published,
                            com.example.base.util.MyDateFormatter.getDay(publishedDate),
                            when (com.example.base.util.MyDateFormatter.getMonth(publishedDate)) {
                                1 -> resources.getString(R.string.published_january)
                                2 -> resources.getString(R.string.published_february)
                                3 -> resources.getString(R.string.published_march)
                                4 -> resources.getString(R.string.published_april)
                                5 -> resources.getString(R.string.published_may)
                                6 -> resources.getString(R.string.published_june)
                                7 -> resources.getString(R.string.published_july)
                                8 -> resources.getString(R.string.published_august)
                                9 -> resources.getString(R.string.published_september)
                                10 -> resources.getString(R.string.published_october)
                                11 -> resources.getString(R.string.published_november)
                                12 -> resources.getString(R.string.published_december)
                                else -> ""
                            }
                        )
                    }
                    nameVacanciesTv.text = title
                    experienceTv.text = title
                    if (lookingNumber != 0) {
                        with(viewsTv) {
                            isVisible = true
                            val peopleStr = resources.getString(
                                if (lookingNumber == 1) {
                                    R.string.one_person
                                } else {
                                    if (lookingNumber in 2..4) R.string.few_people
                                    else R.string.many_people
                                }
                            )
                            text =
                                resources.getString(R.string.watched_by, lookingNumber, peopleStr)
                        }
                    }
                    respBtn.setOnClickListener {
                        onRespondButtonClicked(item)
                    }
                    with(favIc) {
                        setImageResource(if (isFavorite) R.drawable.baseline_favorite_24 else R.drawable.ic_heart_border)
                        setOnClickListener {
                            isFavorite = !isFavorite
                            onFavIcClicked(item)
                            setImageResource(if (isFavorite) R.drawable.baseline_favorite_24 else R.drawable.ic_heart_border)
                        }
                    }
                }
            }
        }
    }
