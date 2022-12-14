package com.frontparissportifs.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface LeagueDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(teamEntity: LeagueCacheEntity): Long

    @Query("SELECT * FROM leagues WHERE name == :keyword COLLATE NOCASE")
    suspend fun getByKeywordEqual(keyword:String): List<LeagueCacheEntity>

    @Query("SELECT * FROM leagues")
    suspend fun getAll(): List<LeagueCacheEntity>

}