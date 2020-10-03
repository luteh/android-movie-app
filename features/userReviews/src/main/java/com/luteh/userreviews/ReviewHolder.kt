package com.luteh.userreviews

import androidx.recyclerview.widget.RecyclerView
import com.luteh.userreviews.databinding.ItemReviewBinding
import com.luteh.core.domain.model.moviedetail.ReviewResult

/**
 * Created by Luthfan Maftuh
 * Email : luthfanmaftuh@gmail.com
 */
class ReviewHolder(private val binding: ItemReviewBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(reviewResult: ReviewResult) {
        with(binding) {
            reviewResult.let {
                tvAuthor.text = it.author
                tvContent.text = it.content
            }
        }
    }
}