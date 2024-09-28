package com.example.vacancydetails.delegateAdapter

import android.view.ViewGroup
import androidx.core.view.isVisible
import com.example.base.base.DisplayableItem
import com.example.common.R
import com.example.presentation.databinding.ItemVacancyInformationBinding
import com.example.presentation.item.ContentItem
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

fun contentAdapter(
    onBackIconClicked: () -> Unit,
    onWatchIconClicked: () -> Unit,
    onShareIconClicked: () -> Unit,
    onFavIconClicked: (vacancy: ContentItem) -> Unit,
) =
    adapterDelegateViewBinding<ContentItem, DisplayableItem, ItemVacancyInformationBinding>(
        { layoutInflater, root ->
            ItemVacancyInformationBinding.inflate(layoutInflater, root, false)
        }
    ) {
        bind {
            with(binding) {
                val resources = responsesNumberTv.resources
                with(item) {
                    titleTv.text = title
                    addressTv.text = address
                    companyNameTv.text = company
                    descriptionTv.text = description
                    responsibilitiesTv.text = responsibilities
                    salaryTv.text = salary
                    schedulesTv.text = schedules

                    experienceTv.text = if (experience.contains("–")) {
                        val exp = experience.split("–")
                        resources.getString(
                            R.string.experience,
                            "${resources.getString(R.string.from)} ${exp[0]} ${resources.getString(R.string.to)} ${exp[1]}"
                        )
                    } else {
                        experience
                    }

                    isFavoriteIv.setImageResource(if (isFavorite) R.drawable.baseline_favorite_24 else R.drawable.ic_heart_border)

                    backIv.setOnClickListener {
                        onBackIconClicked()
                    }

                    watchIv.setOnClickListener {
                        onWatchIconClicked()
                    }

                    shareIv.setOnClickListener {
                        onShareIconClicked()
                    }

                    isFavoriteIv.setOnClickListener {
                        item.isFavorite = !isFavorite
                        isFavoriteIv.setImageResource(if (isFavorite) R.drawable.baseline_favorite_24 else R.drawable.ic_heart_border)
                        onFavIconClicked(item)
                    }

                    if (lookingNumber == 0) {
                        lookLl.isVisible = false
                    } else {
                        val peopleStr = resources.getString(
                            if (lookingNumber == 1) {
                                R.string.one_person
                            } else {
                                if (lookingNumber in 2..4) R.string.few_people
                                else R.string.many_people
                            }
                        )
                        val lookStr =
                            if (lookingNumber == 1) resources.getString(R.string.one_look) else resources.getString(
                                R.string.many_look
                            )
                        lookNumberTv.text = resources.getString(
                            R.string.lookingNumber,
                            lookingNumber,
                            peopleStr,
                            lookStr
                        )
                    }

                    if (appliedNumber == 0) {
                        responsesLl.isVisible = false
                    } else {
                        val peopleStr = resources.getString(
                            if (appliedNumber == 1) {
                                R.string.one_person
                            } else {
                                if (appliedNumber in 2..4) R.string.few_people
                                else R.string.many_people
                            }
                        )
                        val respondedStr =
                            if (appliedNumber == 1) resources.getString(R.string.one_responded) else resources.getString(
                                R.string.many_responded
                            )
                        responsesNumberTv.text = resources.getString(
                            R.string.responsesNumber,
                            appliedNumber,
                            peopleStr,
                            respondedStr
                        )

                        if (!responsesLl.isVisible) {
                            val layoutParams = lookLl.layoutParams as ViewGroup.MarginLayoutParams
                            layoutParams.marginStart *= 0
                            lookLl.layoutParams = layoutParams
                        }
                    }
                }
            }
        }
    }