package com.echo.feature.settings.domain.usecase

import com.echo.core.error.DomainError
import com.echo.core.error.Resource
import com.echo.feature.settings.domain.repository.SettingsRepository
import javax.inject.Inject

class SaveTopicsUseCase @Inject constructor(
    private val repository: SettingsRepository
) {
    suspend operator fun invoke(topicIds: List<Long>): Resource<Unit, DomainError> {
        return repository.saveTopics(topicIds)
    }
}
