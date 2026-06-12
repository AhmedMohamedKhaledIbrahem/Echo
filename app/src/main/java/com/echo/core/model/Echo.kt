package com.echo.core.model

import com.echo.core.constant.INITIAL_DATABASE_ID
import kotlinx.datetime.Instant
import kotlin.time.Clock
import kotlin.time.ExperimentalTime
@OptIn(ExperimentalTime::class)
data class Echo (
    val id: Long = INITIAL_DATABASE_ID,
    val title: String,
    val mood: Mood,
    val audioFilePath: String,
    val audioDuration: Int,
    val description: String = "",
    val topics: List<String> = emptyList(),
    val creationTimestamp: Instant = Clock.System.now(),
)
