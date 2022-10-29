package com.frontparissportifs.network

import com.frontparissportifs.network.team.TeamResponse
import retrofit2.http.GET

interface LeagueApi {

    @GET("all_leagues.php")
    suspend fun getAllLeagues(): LeagueResponse

}