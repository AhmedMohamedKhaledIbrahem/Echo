package com.echo.feature.echo.domain.usecase

import com.echo.core.error.DomainError
import com.echo.core.error.Resource
import com.echo.core.model.Echo
import com.echo.feature.echo.domain.repository.EchoRepository
import javax.inject.Inject

class UpsertEchoUseCase @Inject constructor(
    private val repository: EchoRepository
) {
    suspend operator fun invoke(echo: Echo): Resource<Unit, DomainError> {
        return repository.upsertEcho(echo)
    }
}
