package com.luteh.discover.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.luteh.core.domain.model.MovieDiscover
import com.luteh.discover.databinding.ItemDiscoverBinding

/**
 * Created by Luthfan Maftuh on 8/31/2020.
 * Email : luthfanmaftuh@gmail.com
 */
class DiscoverAdapter : RecyclerView.Adapter<DiscoverHolder>() {

    private val dataList = mutableListOf<MovieDiscover>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DiscoverHolder {
        val binding =
            ItemDiscoverBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DiscoverHolder(binding)
    }

    override fun onBindViewHolder(holder: DiscoverHolder, position: Int) {
        holder.bind(dataList[position])
    }

    override fun getItemCount(): Int = dataList.size

    fun setDataSource(dataList: List<MovieDiscover>?) {
        dataList?.let {
            val previous = this.dataList.size
            this.dataList.addAll(it)

            notifyItemRangeChanged(previous, dataList.size)
        }
    }
}