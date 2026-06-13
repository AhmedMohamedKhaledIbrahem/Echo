package com.echo.feature.settings.data.di

import android.content.Context
import com.echo.core.json.JsonSerializer
import com.echo.core.logger.ReportLogger
import com.echo.feature.settings.data.repository.SettingsRepositoryImpl
import com.echo.feature.settings.data.service.local.SettingsLocal
import com.echo.feature.settings.data.service.local.SettingsLocalImpl
import com.echo.feature.settings.domain.repository.SettingsRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object SettingsServiceModule {
    @Provides
    @Singleton
    fun provideSettingsLocal(
        @ApplicationContext context: Context,
        jsonSerializer: JsonSerializer,
        logger: ReportLogger
    ): SettingsLocal = SettingsLocalImpl(context, jsonSerializer,logger)
}
@Module
@InstallIn(SingletonComponent::class)
abstract class SettingsRepositoryModule {
    @Binds
    @Singleton
    abstract fun bindSettingsRepository(impl: SettingsRepositoryImpl): SettingsRepository
}