package com.echo.core.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "topics_table")
data class TopicEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val name: String,
)
