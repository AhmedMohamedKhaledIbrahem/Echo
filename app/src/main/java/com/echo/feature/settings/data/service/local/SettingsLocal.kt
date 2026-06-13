package com.echo.feature.settings.data.service.local

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.echo.core.constant.DATA_PREFS
import com.echo.core.constant.MOODS_PREF_KEY
import com.echo.core.constant.TOPICS_PREF_KEY
import com.echo.core.error.DataError
import com.echo.core.error.Resource
import com.echo.core.error.mapper.toLocalError
import com.echo.core.json.JsonSerializer
import com.echo.core.logger.ReportLogger
import com.echo.core.model.Mood
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.first
import kotlinx.serialization.KSerializer
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.builtins.serializer
import javax.inject.Inject

interface SettingsLocal {
    suspend fun saveMood(moodTitle: String): Resource<Unit, DataError>
    suspend fun getMood(): Resource<String, DataError>
    suspend fun saveTopics(topicIds: List<Long>): Resource<Unit, DataError>
    suspend fun getTopics(): Resource<List<Long>, DataError>
}

class SettingsLocalImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    private val jsonSerializer: JsonSerializer,
    logger: ReportLogger
) : SettingsLocal, ReportLogger by logger {

    private val Context.dataStory by preferencesDataStore(DATA_PREFS)
    private val dataStore = context.dataStory

    private val topicsIdSerializer: KSerializer<List<Long>> = ListSerializer(Long.serializer())

    override suspend fun saveMood(moodTitle: String): Resource<Unit, DataError> {
        return try {
            dataStore.edit { it[moodKey] = moodTitle }
            Resource.Success(Unit)
        } catch (e: Exception) {
            e("Failed to save mood", e)
            Resource.Failure(e.toLocalError())
        }
    }

    override suspend fun getMood(): Resource<String, DataError> {
        return try {
            val prefs = dataStore.data.first()
            val result = prefs[moodKey] ?: Mood.Undefined.name
            Resource.Success(result)
        } catch (e: Exception) {
            e("Failed to get mood", e)
            Resource.Failure(e.toLocalError())
        }
    }

    override suspend fun saveTopics(topicIds: List<Long>): Resource<Unit, DataError> {
        return try {
            val topicsJson = jsonSerializer.toJson(topicsIdSerializer, topicIds)
            dataStore.edit { it[topicsKey] = topicsJson }
            Resource.Success(Unit)
        } catch (e: Exception) {
            e("Failed to save topics", e)
            Resource.Failure(e.toLocalError())
        }
    }

    override suspend fun getTopics(): Resource<List<Long>, DataError> {
        return try {
            val prefs = dataStore.data.first()
            val topicsJson = prefs[topicsKey] ?: "[]"
            val result = jsonSerializer.fromJson(topicsIdSerializer, topicsJson)
            Resource.Success(result)
        } catch (e: Exception) {
            e("Failed to get topics", e)
            Resource.Failure(e.toLocalError())
        }
    }

    private companion object {
        val moodKey = stringPreferencesKey(MOODS_PREF_KEY)
        val topicsKey = stringPreferencesKey(TOPICS_PREF_KEY)
    }
}