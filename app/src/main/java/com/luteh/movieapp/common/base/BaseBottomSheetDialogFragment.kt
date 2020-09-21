package com.luteh.movieapp.common.base

import android.os.Bundle
import android.view.View
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

/**
 * Created by Luthfan Maftuh
 * Email : luthfanmaftuh@gmail.com
 */
abstract class BaseBottomSheetDialogFragment : BottomSheetDialogFragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        onInit(savedInstanceState)
    }

    /** Init something when view has created */
    abstract fun onInit(savedInstanceState: Bundle?)
}