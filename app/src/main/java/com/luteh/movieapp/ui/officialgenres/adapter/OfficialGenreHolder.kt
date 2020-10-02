package com.luteh.movieapp.ui.officialgenres.adapter

import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.luteh.movieapp.databinding.ItemOfficialGenreBinding
import com.luteh.core.domain.model.moviedetail.Genre
import com.luteh.movieapp.ui.officialgenres.OfficialGenreFragmentDirections

/**
 * Created by Luthfan Maftuh
 * Email : luthfanmaftuh@gmail.com
 */
class OfficialGenreHolder(private val binding: ItemOfficialGenreBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(genre: Genre) {
        with(binding) {
            tvName.text = genre.name

            itemView.setOnClickListener {
                val action = OfficialGenreFragmentDirections.actionOfficialGenreFragmentToDiscoverFragment(genre.id)
                it.findNavController().navigate(action)
            }
        }
    }
}