package com.echo.feature.echo.data.soruce.local

import com.echo.core.database.dao.EchoDao
import com.echo.core.database.toEchoEntity
import com.echo.core.database.toEchoesList
import com.echo.core.error.DataError
import com.echo.core.error.Resource
import com.echo.core.error.mapper.toLocalError
import com.echo.core.logger.ReportLogger
import com.echo.core.model.Echo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject


interface EchoLocal {
    fun getEchos(): Resource<Flow<List<Echo>>, DataError>
    suspend fun upsertEcho(echo: Echo): Resource<Unit, DataError>
    suspend fun deleteEcho(echo: Echo): Resource<Unit, DataError>
}

class EchoLocalImpl @Inject constructor(
    private val dao: EchoDao,
    logger: ReportLogger
) : EchoLocal, ReportLogger by logger {
    override fun getEchos(): Resource<Flow<List<Echo>>, DataError> {
        return try {
            val result = dao.getEchoes().map { it.toEchoesList() }
            Resource.Success(result)
        } catch (e: Exception) {
            e("Failed to get echoes from database", e)
            Resource.Failure(e.toLocalError())
        }
    }

    override suspend fun upsertEcho(echo: Echo): Resource<Unit, DataError> {
        return try {
            val result = dao.upsert(echo.toEchoEntity())
            Resource.Success(result)
        } catch (e: Exception) {
            e("Failed to upsert echo to database", e)
            Resource.Failure(e.toLocalError())
        }
    }

    override suspend fun deleteEcho(echo: Echo): Resource<Unit, DataError> {
        return try {
            val result = dao.delete(echo.toEchoEntity())
            Resource.Success(result)
        } catch (e: Exception) {
            e("Failed to delete echo from database", e)
            Resource.Failure(e.toLocalError())
        }
    }
}