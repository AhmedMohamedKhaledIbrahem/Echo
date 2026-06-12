package com.echo.core.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Upsert

@Dao
interface BaseDao<T> {
    @Upsert
    suspend fun upsert(value: T)

    @Delete
    suspend fun delete(value: T)
}