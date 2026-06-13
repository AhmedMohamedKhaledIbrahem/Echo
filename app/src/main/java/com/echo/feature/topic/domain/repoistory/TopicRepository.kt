package com.echo.feature.topic.domain.repoistory

import com.echo.core.error.DomainError
import com.echo.core.error.Resource
import com.echo.core.model.Topic
import kotlinx.coroutines.flow.Flow

interface TopicRepository {
    fun getTopics(): Resource<Flow<List<Topic>>, DomainError>

    suspend fun matchTopics(query: String): Resource<List<Topic>, DomainError>

    suspend fun insertTopic(topic: Topic): Resource<Unit, DomainError>

    suspend fun deleteTopic(topic: Topic): Resource<Unit, DomainError>

    suspend fun getTopicsByIds(topicsIds: List<Long>): Resource<List<Topic>, DomainError>
}