package com.frontparissportifs.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Team (
    var id: String,
    val name: String,
    val badge: String?,
    val country: String,
    val leagues: List<League>,
    val description: String?
) : Parcelable