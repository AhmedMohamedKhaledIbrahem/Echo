package com.echo.feature.echo.data.repository

import com.echo.core.error.DomainError
import com.echo.core.error.Resource
import com.echo.core.error.Resource.Failure
import com.echo.core.error.Resource.Success
import com.echo.core.error.mapper.toDomainError
import com.echo.core.model.Echo
import com.echo.core.util.fold
import com.echo.feature.echo.data.soruce.local.EchoLocal
import com.echo.feature.echo.domain.repository.EchoRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class EchoRepositoryImpl @Inject constructor(
    private val local: EchoLocal,
) : EchoRepository {

    override fun getEchos(): Resource<Flow<List<Echo>>, DomainError> {
        return local.getEchos().fold(
            onFailure = {
                Failure(it.toDomainError())
            },
            onSuccess = {
                Success(it)
            }
        )
    }

    override suspend fun upsertEcho(echo: Echo): Resource<Unit, DomainError> {
        return local.upsertEcho(echo).fold(
            onFailure = {
                Failure(it.toDomainError())
            },
            onSuccess = {
                Success(Unit)
            }
        )
    }

    override suspend fun deleteEcho(echo: Echo): Resource<Unit, DomainError> {
        return local.deleteEcho(echo).fold(
            onFailure = {
                Failure(it.toDomainError())
            },
            onSuccess = {
                Success(Unit)
            }
        )
    }
}