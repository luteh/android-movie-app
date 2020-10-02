package com.luteh.core.domain.model.moviedetail

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class VideoResult(
    val id: String, // 5d221db394d8a87d3441b212
    val iso6391: String, // en
    val iso31661: String, // US
    val key: String, // 01ON04GCwKs
    val name: String, // Disney's Mulan - Official Teaser
    val site: String, // YouTube
    val size: Int, // 1080
    val type: String // Teaser
) : Parcelable