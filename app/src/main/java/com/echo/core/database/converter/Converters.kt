package com.echo.core.database.converter

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.echo.core.json.JsonSerializer
import com.echo.core.model.Mood
import kotlinx.serialization.KSerializer
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.serializer
import javax.inject.Inject
import kotlin.time.Instant

@ProvidedTypeConverter
class Converters @Inject constructor(
    private val jsonSerializer: JsonSerializer
) {

    private val instantSerializer: KSerializer<Instant> = serializer()

    private val topicsSerializer = ListSerializer(String.serializer())

    private val moodSerializer: KSerializer<Mood> = serializer()

    @TypeConverter
    fun writeInstant(value: Instant?): String? =
        value?.let {
            jsonSerializer.toJson(instantSerializer, it)
        }

    @TypeConverter
    fun readInstant(value: String?): Instant? =
        value?.let {
            jsonSerializer.fromJson(instantSerializer, it)
        }

    @TypeConverter
    fun writeMood(value: Mood?): String? =
        value?.let {
            jsonSerializer.toJson(moodSerializer, it)
        }

    @TypeConverter
    fun readMood(value: String?): Mood? =
        value?.let {
            jsonSerializer.fromJson(moodSerializer, it)
        }

    @TypeConverter
    fun writeTopics(value: List<String>?): String? =
        value?.let {
            jsonSerializer.toJson(topicsSerializer, it)
        }

    @TypeConverter
    fun readTopics(value: String?): List<String>? =
        value?.let {
            jsonSerializer.fromJson(topicsSerializer, it)
        }
}