package com.example.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.presentation.databinding.ItemVacancyBinding
import com.example.presentation.model.VacancyUiModel
import com.example.common.R as baseR

class FavoriteAdapter(
    private val onFavIcClicked: (VacancyUiModel) -> Unit,
    private val onVacancyItemClicked: (String) -> Unit,
    private val onRespondButtonClicked: (VacancyUiModel) -> Unit
) : ListAdapter<VacancyUiModel, FavoriteAdapter.ViewHolder>(DiffCallback()) {
    inner class ViewHolder(private val viewBinding: ItemVacancyBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {
        fun bind(vacancy: VacancyUiModel) {
            with(viewBinding) {
                with(vacancy) {
                    root.setOnClickListener {
                        onVacancyItemClicked(id)
                    }
                    respBtn.setOnClickListener {
                        onRespondButtonClicked(vacancy)
                    }
                    cityTv.text = address.town
                    companyTv.text = company
                    with(dateTv) {
                        text = resources.getString(
                            baseR.string.published,
                            com.example.base.util.MyDateFormatter.getDay(publishedDate),
                            when (com.example.base.util.MyDateFormatter.getMonth(publishedDate)) {
                                1 -> resources.getString(baseR.string.published_january)
                                2 -> resources.getString(baseR.string.published_february)
                                3 -> resources.getString(baseR.string.published_march)
                                4 -> resources.getString(baseR.string.published_april)
                                5 -> resources.getString(baseR.string.published_may)
                                6 -> resources.getString(baseR.string.published_june)
                                7 -> resources.getString(baseR.string.published_july)
                                8 -> resources.getString(baseR.string.published_august)
                                9 -> resources.getString(baseR.string.published_september)
                                10 -> resources.getString(baseR.string.published_october)
                                11 -> resources.getString(baseR.string.published_november)
                                12 -> resources.getString(baseR.string.published_december)
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
                                    baseR.string.one_person
                                } else {
                                    if (lookingNumber in 2..4) baseR.string.few_people
                                    else baseR.string.many_people
                                }
                            )
                            text = resources.getString(
                                baseR.string.watched_by, lookingNumber, peopleStr
                            )
                        }
                    }
                    with(favIc) {
                        setImageResource(if (isFavorite) baseR.drawable.baseline_favorite_24 else baseR.drawable.ic_heart_border)
                        setOnClickListener {
                            isFavorite = !isFavorite
                            onFavIcClicked(vacancy)
                            setImageResource(if (isFavorite) baseR.drawable.baseline_favorite_24 else baseR.drawable.ic_heart_border)
                        }
                    }
                }
            }
        }
    }

    private class DiffCallback : DiffUtil.ItemCallback<VacancyUiModel>() {
        override fun areItemsTheSame(
            oldItem: VacancyUiModel, newItem: VacancyUiModel
        ) = oldItem.id == newItem.id

        override fun areContentsTheSame(
            oldItem: VacancyUiModel, newItem: VacancyUiModel
        ): Boolean = oldItem == newItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        ItemVacancyBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}