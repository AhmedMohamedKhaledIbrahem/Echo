package com.echo.feature.echo.data.di

import com.echo.core.database.dao.EchoDao
import com.echo.core.logger.ReportLogger
import com.echo.feature.echo.data.repository.EchoRepositoryImpl
import com.echo.feature.echo.data.soruce.local.EchoLocal
import com.echo.feature.echo.data.soruce.local.EchoLocalImpl
import com.echo.feature.echo.domain.repository.EchoRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class EchoModule {

    @Binds
    @Singleton
    abstract fun bindEchoRepository(impl: EchoRepositoryImpl): EchoRepository
}

@Module
@InstallIn(SingletonComponent::class)
abstract class EchoServiceModule {

    @Provides
    @Singleton
    fun provideEchoLocal(
        echoDao: EchoDao,
        logger: ReportLogger
    ): EchoLocal = EchoLocalImpl(echoDao, logger)

}