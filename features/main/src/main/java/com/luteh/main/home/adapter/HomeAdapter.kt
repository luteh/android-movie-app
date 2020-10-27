package com.luteh.main.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.luteh.core.data.Result
import com.luteh.core.domain.model.MovieDiscover
import com.luteh.main.databinding.ViewHomeContentBinding
import com.luteh.main.databinding.ViewHomeHeaderBinding

/**
 * Created by Luthfan Maftuh
 * Email : luthfanmaftuh@gmail.com
 */
enum class HomeType(val index: Int) {
    HEADER(0), CONTENT(1)
}

class HomeAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val dataMaps = mutableMapOf<String, Result<List<MovieDiscover>>>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            HomeType.HEADER.index -> {
                HomeHeaderHolder(
                    ViewHomeHeaderBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
            HomeType.CONTENT.index -> {
                ContentHolder(ViewHomeContentBinding.inflate(LayoutInflater.from(parent.context)))
            }
            else -> {
                ContentHolder(ViewHomeContentBinding.inflate(LayoutInflater.from(parent.context)))
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            HomeType.HEADER.index -> {
                (holder as HomeHeaderHolder).bindTo(dataMaps[HomeType.HEADER.name])
            }
            HomeType.CONTENT.index -> {
                (holder as ContentHolder).bindTo(dataMaps[HomeType.CONTENT.name])
            }
        }
    }

    override fun getItemCount(): Int = HomeType.values().size

    override fun getItemViewType(position: Int): Int {
        return position
    }

    fun setDataSources(homeType: HomeType, data: Result<List<MovieDiscover>>) {
        dataMaps[homeType.name] = data
        notifyItemChanged(homeType.index)
    }
}