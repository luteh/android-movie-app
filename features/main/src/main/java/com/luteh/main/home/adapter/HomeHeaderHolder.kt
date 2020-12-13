package com.luteh.main.home.adapter

import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.luteh.core.common.extensions.shouldVisible
import com.luteh.core.common.utils.CustomPageTransformer
import com.luteh.core.common.utils.HorizontalMarginItemDecoration
import com.luteh.core.data.Result
import com.luteh.core.domain.model.MovieDiscover
import com.luteh.main.R
import com.luteh.main.databinding.ViewHomeHeaderBinding
import com.luteh.main.home.HomeItemCallback

/**
 * Created by Luthfan Maftuh
 * Email : luthfanmaftuh@gmail.com
 */
class HomeHeaderHolder(
    private val binding: ViewHomeHeaderBinding,
    private val callback: HomeItemCallback
) :
    RecyclerView.ViewHolder(binding.root) {

    private val adapter = HeaderAdapter(callback)

    init {
        with(binding) {
            vpHeader.apply {
                adapter = this@HomeHeaderHolder.adapter
                offscreenPageLimit = 1
                addItemDecoration(
                    HorizontalMarginItemDecoration(
                        resources.getDimension(R.dimen.spacing_medium__),
                        true
                    )
                )
                setPageTransformer(
                    CustomPageTransformer(
                        resources.getDimension(R.dimen.spacing_medium),
                        resources.getDimension(R.dimen.spacing_medium__)
                    )
                )
                registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                    override fun onPageSelected(position: Int) {
                        indicator.selection =
                            (position % this@HomeHeaderHolder.adapter.dataSources.size)
                    }
                })
            }
        }
    }

    fun bindTo(result: Result<List<MovieDiscover>>?) {
        with(binding) {
            result?.let {
                vLoading.pbCommon.shouldVisible(it is Result.Loading)
                vLoading.tvRetry.shouldVisible(it is Result.Error)

                when (it) {
                    is Result.Success -> {
                        adapter.setDataSource(it.data)
                        indicator.count = it.data.size
                        vpHeader.setCurrentItem(it.data.size * 1000, false)
                    }
                    is Result.Empty -> {
                    }
                }
            }

            vLoading.tvRetry.setOnClickListener {
                callback.reloadItemData(HomeItem.NOW_PLAYING)
            }
        }
    }
}