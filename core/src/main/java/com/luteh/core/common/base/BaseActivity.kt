package com.luteh.core.common.base

import android.view.View
import androidx.appcompat.app.AppCompatActivity

/**
 * Created by Luthfan Maftuh
 * Email : luthfanmaftuh@gmail.com
 */
abstract class BaseActivity : AppCompatActivity() {

    override fun setContentView(view: View?) {
        super.setContentView(view)
        onInit()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    /** Init something when view created */
    abstract fun onInit()
}