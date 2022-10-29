package com.frontparissportifs.dao

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [LeagueCacheEntity::class], version = 1)
abstract class LeagueDatabase : RoomDatabase() {
    abstract fun leagueDao(): LeagueDao

    companion object {
        const val DATABASE_NAME: String = "sport_db"
    }

}