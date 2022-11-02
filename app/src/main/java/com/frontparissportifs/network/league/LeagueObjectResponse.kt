package com.frontparissportifs.network.league

class LeagueResponse (val leagues: List<LeagueObjectResponse>)

data class LeagueObjectResponse(
    val idLeague: String,
    val strLeague: String,
    val strSport: String?,
    val strLeagueAlternate: String?
)