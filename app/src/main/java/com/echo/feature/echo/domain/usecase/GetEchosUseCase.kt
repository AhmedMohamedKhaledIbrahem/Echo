package com.echo.feature.echo.domain.usecase

import com.echo.core.error.DomainError
import com.echo.core.error.Resource
import com.echo.core.model.Echo
import com.echo.feature.echo.domain.repository.EchoRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetEchosUseCase @Inject constructor(
    private val repository: EchoRepository
) {
    operator fun invoke(): Resource<Flow<List<Echo>>, DomainError> {
        return repository.getEchos()
    }
}
