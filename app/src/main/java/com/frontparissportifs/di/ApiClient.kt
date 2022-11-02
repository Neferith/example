package com.frontparissportifs.di

import android.content.Context
import com.frontparissportifs.network.league.LeagueApi
import com.frontparissportifs.network.team.TeamApi
import com.frontparissportifs.network.utils.hasNetwork
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
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
    fun provideRetrofit(okHttpClient: OkHttpClient, gson: Gson): Retrofit.Builder {
        return  Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
    }

    @Singleton
    @Provides
    fun provideHttpCache(@ApplicationContext appContext: Context): Cache {
        val cacheSize = 10 * 1024 * 1024
        return Cache(appContext.cacheDir, cacheSize.toLong())
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(@ApplicationContext appContext: Context,cache: Cache):OkHttpClient {
        return OkHttpClient.Builder().cache(cache).addInterceptor { chain ->
            var request = chain.request()
            request = if (hasNetwork(appContext))
                request
                    .newBuilder()
                    .header(
                        "Cache-Control",
                        "public, max-age=" + 60
                    )
                    .build()
            else
                request
                    .newBuilder()
                    .header(
                        "Cache-Control",
                        "public, only-if-cached, " +
                                "max-stale=" + 60 * 60 * 24 * 7
                    )
                    .build()
            chain.proceed(request)
        }.build()
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