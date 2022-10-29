package com.frontparissportifs.di

import android.content.Context
import androidx.room.Room
import com.frontparissportifs.dao.LeagueDao
import com.frontparissportifs.dao.LeagueDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class RoomModule {
    @Singleton
    @Provides
    fun provideTeamgDb(@ApplicationContext context: Context): LeagueDatabase {
        return Room.databaseBuilder(
            context, LeagueDatabase::class.java,
            LeagueDatabase.DATABASE_NAME
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideTeamDAO(leagueDatabase: LeagueDatabase): LeagueDao {
        return leagueDatabase.leagueDao()
    }
}