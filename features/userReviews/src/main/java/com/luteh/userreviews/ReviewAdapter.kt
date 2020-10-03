package com.luteh.userreviews

import android.view.LayoutInflater
import android.view.ViewGroup
import com.luteh.core.common.base.BaseAdapter
import com.luteh.userreviews.databinding.ItemReviewBinding
import com.luteh.core.domain.model.moviedetail.ReviewResult

/**
 * Created by Luthfan Maftuh
 * Email : luthfanmaftuh@gmail.com
 */
class ReviewAdapter : BaseAdapter<ReviewHolder, ReviewResult>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewHolder =
        ReviewHolder(ItemReviewBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: ReviewHolder, position: Int) {
        holder.bind(dataSources[position])
    }
}