package com.echo.feature.settings.domain.repository

import com.echo.core.error.DomainError
import com.echo.core.error.Resource

interface SettingsRepository {
    suspend fun saveMood(moodTitle: String): Resource<Unit, DomainError>
    suspend fun getMood(): Resource<String, DomainError>
    suspend fun saveTopics(topicIds: List<Long>): Resource<Unit, DomainError>
    suspend fun getTopics(): Resource<List<Long>, DomainError>
}