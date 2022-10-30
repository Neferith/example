package com.frontparissportifs.di

import com.frontparissportifs.network.LeagueApi
import com.frontparissportifs.network.team.TeamApi
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton



@Module
@InstallIn(SingletonComponent::class)
object ApiClient {

    private const val BASE_URL: String = "https://www.thesportsdb.com/api/v1/json/2/"


    @Singleton
    @Provides
    fun provideGsonBuilder(): Gson {
        return  GsonBuilder().setLenient().create()
    }

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson): Retrofit.Builder {
        return  Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(OkHttpClient.Builder().build())
            .addConverterFactory(GsonConverterFactory.create(gson))

    }

    @Singleton
    @Provides
    fun provideTeamService(retrofit: Retrofit.Builder): TeamApi {
        return retrofit
            .build()
            .create(TeamApi::class.java)
    }

    @Singleton
    @Provides
    fun provideLeagueService(retrofit: Retrofit.Builder): LeagueApi {
        return retrofit
            .build()
            .create(LeagueApi::class.java)
    }
}