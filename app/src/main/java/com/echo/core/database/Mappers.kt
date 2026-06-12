@file:OptIn(ExperimentalTime::class)

package com.echo.core.database

import com.echo.core.database.entity.EchoEntity
import com.echo.core.database.entity.TopicEntity
import com.echo.core.model.Echo
import com.echo.core.model.Topic

import kotlin.time.ExperimentalTime

fun Echo.toEchoEntity(): EchoEntity {
    return EchoEntity(
        id = id,
        title = title,
        mood = mood,
        audioFilePath = audioFilePath,
        audioDuration = audioDuration,
        description = description,
        topics = topics,
        creationTimestamp = creationTimestamp,
    )
}

fun EchoEntity.toModel(): Echo {
    return Echo(
        id = id,
        title = title,
        mood = mood,
        audioFilePath = audioFilePath,
        audioDuration = audioDuration,
        description = description,
        topics = topics,
        creationTimestamp = creationTimestamp,
    )
}

fun List<EchoEntity>.toEchoesList(): List<Echo> = this.map { it.toModel() }


fun Topic.toEntity(): TopicEntity {
    return TopicEntity(
        id = id,
        name = name,
    )
}

fun TopicEntity.toModel(): Topic {
    return Topic(
        id = id,
        name = name,
    )
}

fun List<TopicEntity>.toTopicsList(): List<Topic> = map { it.toModel() }