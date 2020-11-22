package com.luteh.main.home.adapter

import androidx.recyclerview.widget.RecyclerView
import com.luteh.core.common.extensions.shouldVisible
import com.luteh.core.common.utils.HorizontalMarginItemDecoration
import com.luteh.core.data.Result
import com.luteh.core.domain.model.MovieDiscover
import com.luteh.main.R
import com.luteh.main.databinding.ViewHomeContentBinding
import com.luteh.main.home.HomeItemCallback

/**
 * Created by Luthfan Maftuh
 * Email : luthfanmaftuh@gmail.com
 */
class HomeContentHolder(
    private val binding: ViewHomeContentBinding,
    private val callback: HomeItemCallback
) :
    RecyclerView.ViewHolder(binding.root) {

    private val adapter = ContentAdapter(callback)

    init {
        with(binding) {
            rvContent.apply {
                addItemDecoration(HorizontalMarginItemDecoration(resources.getDimension(R.dimen.spacing_medium__)))
                adapter = this@HomeContentHolder.adapter
            }
        }
    }

    fun bindTo(result: Result<List<MovieDiscover>>?) {
        with(binding) {
            tvTitle.text = root.resources.getStringArray(R.array.categories)[adapterPosition - 1]

            result?.let {
                vLoading.pbCommon.shouldVisible(it is Result.Loading)
                vLoading.tvRetry.shouldVisible(it is Result.Error)

                when (it) {
                    is Result.Success -> {
                        adapter.setDataSource(it.data)
                    }
                    is Result.Empty -> {
                    }
                }
            }

            vLoading.tvRetry.setOnClickListener {
                if (adapterPosition == HomeItem.TOP_RATED.index) {
                    callback.reloadItemData(HomeItem.TOP_RATED)
                } else {
                    callback.reloadItemData(HomeItem.POPULAR)
                }
            }
        }
    }
}