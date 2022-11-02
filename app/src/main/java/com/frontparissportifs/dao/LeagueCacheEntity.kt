package com.frontparissportifs.dao

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "leagues")
data class LeagueCacheEntity (

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    var id:String,

    @ColumnInfo(name = "name")
    val name:String,

    @ColumnInfo(name = "sport")
    val sport:String?,

    @ColumnInfo(name = "alternateName")
    val alternateName:String?

)