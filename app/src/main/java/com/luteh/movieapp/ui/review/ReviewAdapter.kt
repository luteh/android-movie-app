package com.luteh.movieapp.ui.review

import android.view.LayoutInflater
import android.view.ViewGroup
import com.luteh.movieapp.common.base.BaseAdapter
import com.luteh.movieapp.databinding.ItemReviewBinding
import com.luteh.movieapp.domain.model.moviedetail.ReviewResult

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