package com.echo.core.util

import com.echo.core.constant.DEFAULT_FORMATTED_TIME
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class StopWatch {
    private val _formattedTime = MutableStateFlow(DEFAULT_FORMATTED_TIME)
    val formattedTime: StateFlow<String> = _formattedTime

    private var coroutineScope = CoroutineScope(Dispatchers.Main)
    private var isRunning = false

    private var timeMillis = 0L
    private var lastTimestamp = 0L

    fun start() {
        if (isRunning) return

        coroutineScope.launch {
            lastTimestamp = System.currentTimeMillis()
            isRunning = true
            while (isRunning) {
                delay(10L)
                timeMillis += System.currentTimeMillis() - lastTimestamp
                lastTimestamp = System.currentTimeMillis()
                _formattedTime.value = timeMillis.formatMillisToTime()
            }
        }
    }

    fun pause() {
        isRunning = false
    }

    fun reset() {
        coroutineScope.cancel()
        coroutineScope = CoroutineScope(Dispatchers.Main)
        timeMillis = 0L
        lastTimestamp = 0L
        _formattedTime.value = DEFAULT_FORMATTED_TIME
        isRunning = false
    }
}