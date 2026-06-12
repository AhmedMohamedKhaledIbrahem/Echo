package com.echo.feature.aduio.domain.repository

interface AudioRecorder {
     fun start()

    fun pause()

    fun resume()

    fun stop(saveFile: Boolean): String
}