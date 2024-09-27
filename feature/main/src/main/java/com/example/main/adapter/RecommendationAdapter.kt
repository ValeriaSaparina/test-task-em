package com.example.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.common.R
import com.example.presentation.databinding.ItemRecommendationBinding
import com.example.presentation.model.OfferUiModel

class RecommendationAdapter(
    private val onItemClicked: (String) -> Unit,
) : ListAdapter<OfferUiModel, RecommendationAdapter.ViewHolder>(DiffCallback()) {
    inner class ViewHolder(
        private val viewBinding: ItemRecommendationBinding,
    ) : RecyclerView.ViewHolder(viewBinding.root) {
        fun bind(item: OfferUiModel, position: Int) {
            with(viewBinding) {
                if (position == 0) {
                    val layoutParams = root.layoutParams as ViewGroup.MarginLayoutParams
                    layoutParams.marginStart *= 2
                    root.layoutParams = layoutParams
                } else {
                    if (position == itemCount - 1) {
                        val layoutParams = root.layoutParams as ViewGroup.MarginLayoutParams
                        layoutParams.marginEnd = layoutParams.marginStart * 2
                        root.layoutParams = layoutParams
                    }
                }
                with(item) {
                    root.setOnClickListener {
                        onItemClicked(item.link)
                    }
                    recommendationTitleTv.text = title
                    when (item.id) {
                        "near_vacancies" -> {
                            recommendationIc.setBackgroundResource(R.drawable.shape_round_dark_blue)
                            recommendationIc.setImageResource(R.drawable.ic_pin_drop)
                        }

                        "level_up_resume" -> {
                            recommendationIc.setBackgroundResource(R.drawable.shape_round_dark_green)
                            recommendationIc.setImageResource(R.drawable.leve_up_resume)
                        }

                        "temporary_job" -> {
                            recommendationIc.setBackgroundResource(R.drawable.shape_round_dark_green)
                            recommendationIc.setImageResource(R.drawable.ic_tempopary_job)
                        }
                    }
                    if (button.text.isNotEmpty())
                        recommendationBtn.isVisible = true
                    recommendationBtn.text = button.text
                }
            }
        }
    }

    private class DiffCallback :
        DiffUtil.ItemCallback<OfferUiModel>() {
        override fun areItemsTheSame(
            oldItem: OfferUiModel,
            newItem: OfferUiModel
        ) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(
            oldItem: OfferUiModel,
            newItem: OfferUiModel
        ): Boolean =
            oldItem == newItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            ItemRecommendationBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position), position)
    }
}