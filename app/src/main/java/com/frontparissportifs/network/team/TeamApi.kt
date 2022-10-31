package com.frontparissportifs.network.team

import retrofit2.http.GET
import retrofit2.http.Query

interface TeamApi {

    @GET("search_all_teams.php")
    suspend fun getByLeagues(@Query("l") leagueName: String): TeamResponse

    suspend fun getTeamByid()

}