package com.frontparissportifs.model

class Team (
    var id: String,
    val name: String,
    val banner: String?,
    val country: String,
    val leagues: List<League>,
    val description: String?
)