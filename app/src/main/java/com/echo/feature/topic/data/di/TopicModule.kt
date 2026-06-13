package com.echo.feature.topic.data.di

import com.echo.core.database.dao.TopicsDao
import com.echo.core.logger.ReportLogger
import com.echo.feature.topic.data.repository.TopicRepositoryImpl
import com.echo.feature.topic.data.service.local.TopicLocalService
import com.echo.feature.topic.data.service.local.TopicLocalServiceImpl
import com.echo.feature.topic.domain.repoistory.TopicRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TopicServiceModule {
    @Provides
    @Singleton
    fun provideTopicLocalService(
        dao: TopicsDao,
        logger: ReportLogger
    ): TopicLocalService = TopicLocalServiceImpl(dao, logger)

}

@Module
@InstallIn(SingletonComponent::class)
abstract class TopicRepositoryModule {
    @Binds
    @Singleton
    abstract fun bindTopicRepository(impl: TopicRepositoryImpl): TopicRepository
}