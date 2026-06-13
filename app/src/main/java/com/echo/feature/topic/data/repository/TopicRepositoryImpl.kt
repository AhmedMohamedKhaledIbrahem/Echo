package com.echo.feature.topic.data.repository

import com.echo.core.error.DomainError
import com.echo.core.error.Resource
import com.echo.core.error.mapper.toDomainError
import com.echo.core.model.Topic
import com.echo.core.util.fold
import com.echo.feature.topic.data.service.local.TopicLocalService
import com.echo.feature.topic.domain.repoistory.TopicRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TopicRepositoryImpl @Inject constructor(
    private val local: TopicLocalService,
) : TopicRepository {
    override fun getTopics(): Resource<Flow<List<Topic>>, DomainError> {
        return local.getTopics().fold(
            onFailure = {
                Resource.Failure(it.toDomainError())
            },
            onSuccess = {
                Resource.Success(it)
            },
        )
    }

    override suspend fun matchTopics(query: String): Resource<List<Topic>, DomainError> {
        return local.matchTopics(query).fold(
            onFailure = {
                Resource.Failure(it.toDomainError())
            },
            onSuccess = {
                Resource.Success(it)
            },
        )
    }

    override suspend fun insertTopic(topic: Topic): Resource<Unit, DomainError> {
        return local.insertTopic(topic).fold(
            onFailure = {
                Resource.Failure(it.toDomainError())
            },
            onSuccess = {
                Resource.Success(Unit)
            },
        )
    }

    override suspend fun deleteTopic(topic: Topic): Resource<Unit, DomainError> {
        return local.deleteTopic(topic).fold(
            onFailure = {
                Resource.Failure(it.toDomainError())
            },
            onSuccess = {
                Resource.Success(Unit)
            },
        )
    }

    override suspend fun getTopicsByIds(topicsIds: List<Long>): Resource<List<Topic>, DomainError> {
        return local.getTopicsByIds(topicsIds).fold(
            onFailure = {
                Resource.Failure(it.toDomainError())
            },
            onSuccess = {
                Resource.Success(it)
            },
        )
    }
}