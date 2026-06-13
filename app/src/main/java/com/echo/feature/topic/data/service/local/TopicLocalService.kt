package com.echo.feature.topic.data.service.local

import com.echo.core.database.dao.TopicsDao
import com.echo.core.database.toEntity
import com.echo.core.database.toModel
import com.echo.core.database.toTopicsList
import com.echo.core.error.DataError
import com.echo.core.error.Resource
import com.echo.core.error.mapper.toLocalError
import com.echo.core.logger.ReportLogger
import com.echo.core.model.Topic
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

interface TopicLocalService {
    fun getTopics(): Resource<Flow<List<Topic>>, DataError>
    suspend fun getTopicsByIds(topicsIds: List<Long>): Resource<List<Topic>, DataError>
    suspend fun matchTopics(query: String): Resource<List<Topic>, DataError>
    suspend fun insertTopic(topic: Topic): Resource<Unit, DataError>
    suspend fun deleteTopic(topic: Topic): Resource<Unit, DataError>
}

class TopicLocalServiceImpl @Inject constructor(
    private val dao: TopicsDao,
    logger: ReportLogger
) : TopicLocalService, ReportLogger by logger {
    override fun getTopics(): Resource<Flow<List<Topic>>, DataError> {
        return try {
            val result = dao.getTopics().map { it.toTopicsList() }
            Resource.Success(result)
        } catch (e: Exception) {
            e("Failed to get topics from database", e)
            Resource.Failure(e.toLocalError())
        }
    }

    override suspend fun getTopicsByIds(topicsIds: List<Long>): Resource<List<Topic>, DataError> {
        return try {
            val result = dao.getTopicsByIds(topicsIds).map { it.toModel() }
            Resource.Success(result)
        } catch (e: Exception) {
            e("Failed to get topics from database", e)
            Resource.Failure(e.toLocalError())
        }
    }

    override suspend fun matchTopics(query: String): Resource<List<Topic>, DataError> {
        return try {
            val result = dao.matchTopics(query).map { it.toModel() }
            Resource.Success(result)
        } catch (e: Exception) {
            e("Failed to get topics from database", e)
            Resource.Failure(e.toLocalError())
        }
    }

    override suspend fun insertTopic(topic: Topic): Resource<Unit, DataError> {
        return try {
            val result = dao.upsert(topic.toEntity())
            Resource.Success(result)
        } catch (e: Exception) {
            e("Failed to get topics from database", e)
            Resource.Failure(e.toLocalError())
        }
    }

    override suspend fun deleteTopic(topic: Topic): Resource<Unit, DataError> {
        return try {
            val result = dao.delete(topic.toEntity())
            Resource.Success(result)
        } catch (e: Exception) {
            e("Failed to get topics from database", e)
            Resource.Failure(e.toLocalError())
        }
    }

}