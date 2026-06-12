package com.echo.feature.aduio.data.di

import com.echo.feature.aduio.data.repository.MediaAudioPlayer
import com.echo.feature.aduio.data.repository.MediaAudioRecorder
import com.echo.feature.aduio.domain.repository.AudioPlayer
import com.echo.feature.aduio.domain.repository.AudioRecorder
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class AudioModule {
    @Binds
    abstract fun bindAudioRecorder(audioRecorder: MediaAudioRecorder): AudioRecorder

    @Binds
    abstract fun bindAudioPlayer(audioPlayer: MediaAudioPlayer): AudioPlayer
}