package com.echo.feature.echo.domain.repository

import com.echo.core.error.DomainError
import com.echo.core.error.Resource
import com.echo.core.model.Echo
import kotlinx.coroutines.flow.Flow

interface EchoRepository {
    fun getEchos(): Resource<Flow<List<Echo>>, DomainError>

    suspend fun upsertEcho(echo: Echo): Resource<Unit, DomainError>

    suspend fun deleteEcho(echo: Echo): Resource<Unit, DomainError>
}