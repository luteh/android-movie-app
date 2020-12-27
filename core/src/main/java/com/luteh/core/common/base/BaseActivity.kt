package com.luteh.core.common.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity

/**
 * Created by Luthfan Maftuh
 * Email : luthfanmaftuh@gmail.com
 */
abstract class BaseActivity constructor(@LayoutRes contentLayoutId: Int) :
    AppCompatActivity(contentLayoutId) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onInit()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    /** Init something when view created */
    abstract fun onInit()
}