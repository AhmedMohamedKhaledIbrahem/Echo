package com.echo.feature.aduio.data.repository

import android.content.Context
import android.media.MediaPlayer
import android.net.Uri
import androidx.core.net.toUri
import com.echo.core.coroutine.ApplicationScopeMainThread
import com.echo.feature.aduio.domain.repository.AudioPlayer
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import javax.inject.Inject

class MediaAudioPlayer @Inject constructor(
    @ApplicationContext private val context: Context,
    @ApplicationScopeMainThread private val mainScope: CoroutineScope
) : AudioPlayer {
    private var filePath: String = ""
    private var player: MediaPlayer? = null
    private var onCompleteListener: (() -> Unit)? = null
    private var job: Job? = null
    private val _currentPosition = MutableStateFlow(0)
    override val currentPosition: StateFlow<Int>
        get() = _currentPosition.asStateFlow()

    override fun initializeFile(filePath: String) {
        releaseResources()
        this.filePath = filePath
        createPlayer()
    }

    override fun play() {
        player ?: createPlayer()
        player?.start()
        startUpdateCurrentPosition()
    }

    override fun pause() {
        playerIsInitialized()
        player?.pause()
        stopUpdateCurrentPosition()
    }

    override fun resume() {
        playerIsInitialized()
        player?.start()
        startUpdateCurrentPosition()
    }

    override fun stop() {
       releaseResources()
    }

    override fun setOnCompletionListener(listener: () -> Unit) {
        onCompleteListener = listener
    }

    override fun getDuration(): Int {
        playerIsInitialized()
        return player?.duration ?: 0
    }

    override fun isPlaying(): Boolean {
        return player?.isPlaying ?: false
    }

    private fun createPlayer() {
        MediaPlayer.create(context, Uri.decode(filePath).toUri()).apply {
            player = this
            setOnCompletionListener {
                stopUpdateCurrentPosition()
                _currentPosition.update { duration }
                onCompleteListener?.invoke()
            }
        }
            ?: throw IllegalStateException("Failed to create MediaPlayer, Invalid file path $filePath")
    }

    private fun playerIsInitialized() {
        player ?: throw IllegalStateException(
            "Media Player is not initialized, Call 'initializeFile()' first."
        )
    }

    private fun startUpdateCurrentPosition() {
        playerIsInitialized()
        stopUpdateCurrentPosition()
        job = mainScope.launch {
            while (isActive && player?.isPlaying == true) {
                _currentPosition.update { player?.currentPosition ?: 0 }
                delay(250L)
            }
        }

    }

    private fun stopUpdateCurrentPosition() {
        job?.cancel()
        job = null
    }

    private fun releaseResources() {
        stopUpdateCurrentPosition()
        _currentPosition.update { 0 }
        player?.stop()
        player?.release()
        player = null
    }
}