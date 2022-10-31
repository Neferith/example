package com.frontparissportifs.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class League(
    val id:String,
    val name:String,
    val sport:String?,
    val alternateName:String?):Parcelable {

    constructor( id:String,
                 name:String) : this(id,name,null,null)
}