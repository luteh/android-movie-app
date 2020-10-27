package com.luteh.main.home.adapter

import androidx.recyclerview.widget.RecyclerView
import com.luteh.core.data.Result
import com.luteh.core.domain.model.MovieDiscover
import com.luteh.main.databinding.ViewHomeContentBinding

/**
 * Created by Luthfan Maftuh
 * Email : luthfanmaftuh@gmail.com
 */
class ContentHolder(private val binding: ViewHomeContentBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bindTo(value: Result<List<MovieDiscover>>?) {
        with(binding) {
        }
    }
}