package com.echo.feature.settings.domain.usecase

import com.echo.core.error.DomainError
import com.echo.core.error.Resource
import com.echo.feature.settings.domain.repository.SettingsRepository
import javax.inject.Inject

class SaveMoodUseCase @Inject constructor(
    private val repository: SettingsRepository
) {
    suspend operator fun invoke(moodTitle: String): Resource<Unit, DomainError> {
        return repository.saveMood(moodTitle)
    }
}
