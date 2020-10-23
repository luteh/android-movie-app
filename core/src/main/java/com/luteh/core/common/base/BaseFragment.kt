package com.luteh.core.common.base

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.luteh.core.R

/**
 * Created by Luthfan Maftuh
 * Email : luthfanmaftuh@gmail.com
 */
abstract class BaseFragment : Fragment() {

    val baseActivity: BaseActivity get() = activity as BaseActivity

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        onInit(savedInstanceState)
    }

    /**
     * Init action bar
     *
     * @param toolbar The toolbar component
     * @param showBackIcon Determine the back icon on toolbar is show or not
     */
    fun initActionBar(toolbar: Toolbar?, showBackIcon: Boolean, strTitle: String = getString(R.string.app_name)) {
        baseActivity.apply {
            setSupportActionBar(toolbar)
            supportActionBar?.apply {
                setDisplayHomeAsUpEnabled(showBackIcon)
                setDisplayShowHomeEnabled(showBackIcon)
            }

            title = strTitle
        }

    }

    /** Init something when view has created */
    abstract fun onInit(savedInstanceState: Bundle?)
}