package com.echo.feature.aduio.domain.repository

import kotlinx.coroutines.flow.StateFlow

interface AudioPlayer {
    val currentPosition: StateFlow<Int>

    fun initializeFile(filePath: String)

    fun play()

    fun pause()

    fun resume()

    fun stop()

    fun setOnCompletionListener(listener: () -> Unit)

    fun getDuration(): Int

    fun isPlaying(): Boolean

}