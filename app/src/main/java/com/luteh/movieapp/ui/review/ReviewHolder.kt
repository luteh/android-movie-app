package com.luteh.movieapp.ui.review

import androidx.recyclerview.widget.RecyclerView
import com.luteh.movieapp.databinding.ItemReviewBinding
import com.luteh.movieapp.domain.model.moviedetail.ReviewResult

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