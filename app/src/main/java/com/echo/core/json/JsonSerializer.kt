package com.echo.core.json

import kotlinx.serialization.KSerializer
import kotlinx.serialization.json.Json
import javax.inject.Inject

interface JsonSerializer {
    fun <T> toJson(
        serializer: KSerializer<T>,
        data: T,
    ): String

    fun <T> fromJson(
        serializer: KSerializer<T>,
        json: String,
    ): T
}

class JsonSerializerImpl @Inject constructor() : JsonSerializer {
    override fun <T> toJson(
        serializer: KSerializer<T>,
        data: T,
    ): String {
        return Json.encodeToString(serializer, data)
    }

    override fun <T> fromJson(
        serializer: KSerializer<T>,
        json: String,
    ): T {
        return Json.decodeFromString(serializer, json)
    }
}