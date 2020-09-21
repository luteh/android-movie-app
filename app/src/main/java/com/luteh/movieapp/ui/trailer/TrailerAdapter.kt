package com.luteh.movieapp.ui.trailer

import android.view.LayoutInflater
import android.view.ViewGroup
import com.luteh.movieapp.common.base.BaseAdapter
import com.luteh.movieapp.databinding.ItemTrailerBinding
import com.luteh.movieapp.domain.model.moviedetail.VideoResult

/**
 * Created by Luthfan Maftuh
 * Email : luthfanmaftuh@gmail.com
 */
class TrailerAdapter(private val callback: (VideoResult, Int) -> Unit) : BaseAdapter<TrailerHolder, VideoResult>() {

    private var selectedPosition = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrailerHolder {
        return TrailerHolder(ItemTrailerBinding.inflate(LayoutInflater.from(parent.context), parent, false), callback)
    }

    override fun onBindViewHolder(holder: TrailerHolder, position: Int) {
        holder.bind(dataSources[position], selectedPosition)
    }

    fun setSelectedPosition(selectedPosition: Int) {
        this.selectedPosition = selectedPosition
        notifyDataSetChanged()
    }
}