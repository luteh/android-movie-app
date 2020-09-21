package com.luteh.movieapp.common.base

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.luteh.movieapp.R
import com.luteh.movieapp.ui.main.MainActivity

/**
 * Created by Luthfan Maftuh
 * Email : luthfanmaftuh@gmail.com
 */
abstract class BaseFragment : Fragment() {
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
        if (activity is MainActivity) { // if current fragment activity is MainActivity, then setup action bar view
            (activity as MainActivity).apply {
                setSupportActionBar(toolbar)
                supportActionBar?.apply {
                    setDisplayHomeAsUpEnabled(showBackIcon)
                    setDisplayShowHomeEnabled(showBackIcon)
                }

                title = strTitle
            }
        }
    }

    /** Init something when view has created */
    abstract fun onInit(savedInstanceState: Bundle?)
}