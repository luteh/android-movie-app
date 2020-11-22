package com.luteh.main.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.luteh.core.data.Result
import com.luteh.core.domain.model.MovieDiscover
import com.luteh.main.databinding.ViewHomeContentBinding
import com.luteh.main.databinding.ViewHomeHeaderBinding
import com.luteh.main.home.HomeItemCallback

/**
 * Created by Luthfan Maftuh
 * Email : luthfanmaftuh@gmail.com
 */
enum class HomeType(val index: Int) {
    HEADER(0), CONTENT(1)
}

enum class HomeItem(val index: Int) {
    NOW_PLAYING(0), POPULAR(1), TOP_RATED(2)
}

class HomeAdapter(private val callback: HomeItemCallback) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val dataMaps = mutableMapOf<Int, Result<List<MovieDiscover>>>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            HomeType.HEADER.index -> {
                HomeHeaderHolder(
                    ViewHomeHeaderBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    ),
                    callback
                )
            }
            else -> {
                HomeContentHolder(ViewHomeContentBinding.inflate(LayoutInflater.from(parent.context)), callback)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            HomeType.HEADER.index -> {
                (holder as HomeHeaderHolder).bindTo(dataMaps[position])
            }
            else -> {
                (holder as HomeContentHolder).bindTo(dataMaps[position])
            }
        }
    }

    override fun getItemCount(): Int = HomeItem.values().size

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) HomeType.HEADER.index else HomeType.CONTENT.index
    }

    fun setDataSources(homeItem: HomeItem, data: Result<List<MovieDiscover>>) {
        dataMaps[homeItem.index] = data
        notifyItemChanged(homeItem.index)
    }
}