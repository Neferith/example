package com.frontparissportifs.network.league

import retrofit2.http.GET

interface LeagueApi {

    @GET("all_leagues.php")
    suspend fun getAllLeagues(): LeagueResponse

}