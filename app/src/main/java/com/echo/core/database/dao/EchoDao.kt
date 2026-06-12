package com.echo.core.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.echo.core.database.entity.EchoEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface EchoDao : BaseDao<EchoEntity> {
    @Query("SELECT * FROM echoes_table ORDER BY creation_time_stamp DESC")
    fun getEchoes(): Flow<List<EchoEntity>>
}