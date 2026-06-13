package com.echo.feature.settings.data.repository

import com.echo.core.error.DomainError
import com.echo.core.error.Resource
import com.echo.core.error.mapper.toDomainError
import com.echo.core.util.fold
import com.echo.feature.settings.data.service.local.SettingsLocal
import com.echo.feature.settings.domain.repository.SettingsRepository
import javax.inject.Inject

class SettingsRepositoryImpl @Inject constructor(
    private val local: SettingsLocal,
) : SettingsRepository {
    override suspend fun saveMood(moodTitle: String): Resource<Unit, DomainError> {
        return local.saveMood(moodTitle).fold(
            onFailure = {
                Resource.Failure(it.toDomainError())
            },
            onSuccess = {
                Resource.Success(Unit)
            }
        )
    }

    override suspend fun getMood(): Resource<String, DomainError> {
        return local.getMood().fold(
            onFailure = {
                Resource.Failure(it.toDomainError())
            },
            onSuccess = {
                Resource.Success(it)
            }
        )
    }

    override suspend fun saveTopics(topicIds: List<Long>): Resource<Unit, DomainError> {
        return local.saveTopics(topicIds).fold(
            onFailure = {
                Resource.Failure(it.toDomainError())
            },
            onSuccess = {
                Resource.Success(Unit)
            }
        )
    }

    override suspend fun getTopics(): Resource<List<Long>, DomainError> {
        return local.getTopics().fold(
            onFailure = {
                Resource.Failure(it.toDomainError())
            },
            onSuccess = {
                Resource.Success(it)
            }
        )
    }

}