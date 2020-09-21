package com.luteh.movieapp.ui.detail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.luteh.movieapp.common.base.BaseAdapter
import com.luteh.movieapp.databinding.ItemDetailGenreBinding
import com.luteh.movieapp.domain.model.moviedetail.Genre

/**
 * Created by Luthfan Maftuh
 * Email : luthfanmaftuh@gmail.com
 */
class DetailGenreAdapter : BaseAdapter<DetailGenreHolder, Genre>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailGenreHolder =
        DetailGenreHolder(ItemDetailGenreBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: DetailGenreHolder, position: Int) {
        holder.bind(dataSources[position])
    }
}