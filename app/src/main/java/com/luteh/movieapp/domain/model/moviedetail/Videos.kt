package com.luteh.movieapp.domain.model.moviedetail

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Videos(
    val videoResults: List<VideoResult>
) : Parcelable