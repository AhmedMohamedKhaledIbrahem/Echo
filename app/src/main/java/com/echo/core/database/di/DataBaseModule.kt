package com.echo.core.database.di

import android.content.Context
import androidx.room.Room
import com.echo.core.database.AppDataBase
import com.echo.core.database.converter.Converters
import com.echo.core.database.dao.EchoDao
import com.echo.core.database.dao.TopicsDao
import com.echo.core.json.JsonSerializer
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {
    @Provides
    @Singleton
    fun providerDataBase(
        @ApplicationContext context: Context,
        jsonSerializer: JsonSerializer,
    ): AppDataBase {
        return Room.databaseBuilder(
            context,
            AppDataBase::class.java,
            "echo_db"
        ).addTypeConverter(Converters(jsonSerializer = jsonSerializer))
            .fallbackToDestructiveMigration(true)
            .build()
    }

    @Provides
    @Singleton
    fun provideDao(database: AppDataBase): EchoDao = database.echoDao()

    @Provides
    @Singleton
    fun provideTopicsDao(database: AppDataBase): TopicsDao = database.topicsDao()


}