package com.echo.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.echo.core.database.converter.Converters
import com.echo.core.database.dao.EchoDao
import com.echo.core.database.dao.TopicsDao
import com.echo.core.database.entity.EchoEntity
import com.echo.core.database.entity.TopicEntity
@TypeConverters(Converters::class)
@Database(
    entities = [
        EchoEntity::class,
        TopicEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDataBase : RoomDatabase() {
    abstract fun echoDao(): EchoDao
    abstract fun topicsDao(): TopicsDao
}

