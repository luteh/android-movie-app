package com.luteh.detail.adapter

import androidx.recyclerview.widget.RecyclerView
import com.luteh.detail.databinding.ItemDetailGenreBinding
import com.luteh.core.domain.model.moviedetail.Genre

/**
 * Created by Luthfan Maftuh
 * Email : luthfanmaftuh@gmail.com
 */
class DetailGenreHolder(private val binding: ItemDetailGenreBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(genre: Genre){
        with(binding){
            genre.let {
                chipName.text = it.name
            }
        }
    }
}