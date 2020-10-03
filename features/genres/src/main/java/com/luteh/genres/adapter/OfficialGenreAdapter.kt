package com.luteh.genres.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.luteh.core.common.base.BaseAdapter
import com.luteh.genres.databinding.ItemOfficialGenreBinding
import com.luteh.core.domain.model.moviedetail.Genre

/**
 * Created by Luthfan Maftuh
 * Email : luthfanmaftuh@gmail.com
 */
class OfficialGenreAdapter : BaseAdapter<OfficialGenreHolder, Genre>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OfficialGenreHolder =
        OfficialGenreHolder(ItemOfficialGenreBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: OfficialGenreHolder, position: Int) {
        holder.bind(dataSources[position])
    }
}