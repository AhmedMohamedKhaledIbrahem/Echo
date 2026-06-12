package com.echo.core.util

import com.echo.core.model.Echo
import com.echo.core.model.Mood
import kotlin.random.Random
import kotlin.random.nextInt

fun generateRandomEchoItem(): Echo {
    val randomInt = Random.nextInt(0..100)

    return Echo(
        id = 0L,
        title = "Day of The Jackal",
        description = generateLoremIpsum(wordCount = 89),
        audioDuration = randomInt,
        mood = Mood.Sad,
        topics = listOf("Topic 1", "Topic 2", "Topic 3"),
        audioFilePath = "",
    )
}

fun generateRandomEchoItems(count: Int = 13): List<Echo> {
    return buildList {
        repeat(count) {
            add(generateRandomEchoItem())
        }
    }
}