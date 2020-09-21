package com.luteh.movieapp.ui.officialgenres.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.luteh.movieapp.common.base.BaseAdapter
import com.luteh.movieapp.databinding.ItemOfficialGenreBinding
import com.luteh.movieapp.domain.model.moviedetail.Genre

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