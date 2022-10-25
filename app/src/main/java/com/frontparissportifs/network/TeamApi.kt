package com.frontparissportifs.network

import retrofit2.http.GET

interface TeamApi {
    @GET
    suspend fun getByLeagues():List<TeamObjectResponse>
}