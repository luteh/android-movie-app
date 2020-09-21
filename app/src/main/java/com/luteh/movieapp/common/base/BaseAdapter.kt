package com.luteh.movieapp.common.base

import androidx.recyclerview.widget.RecyclerView

/**
 * Created by Luthfan Maftuh
 * Email : luthfanmaftuh@gmail.com
 */
abstract class BaseAdapter<Holder : RecyclerView.ViewHolder, Data> : RecyclerView.Adapter<Holder>() {

    val dataSources = mutableListOf<Data>()

    override fun getItemCount(): Int = dataSources.size

    fun setDataSource(data: List<Data>?) {
        data?.let {
            dataSources.run {
                clear()
                addAll(it)

                notifyDataSetChanged()
            }
        }
    }

    fun addDataSource(data: List<Data>?) {
        data?.let {
            dataSources.run {
                val previous = size
                addAll(it)

                notifyItemRangeChanged(previous, it.size)
            }
        }
    }
}