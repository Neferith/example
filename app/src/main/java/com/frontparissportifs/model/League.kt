package com.frontparissportifs.model


data class League(
    val id:String,
    val name:String,
    val sport:String?,
    val alternateName:String?) {

    constructor( id:String,
                 name:String) : this(id,name,null,null)
}