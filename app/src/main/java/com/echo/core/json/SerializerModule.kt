package com.echo.core.json

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class SerializerModule {
    @Binds
    abstract fun bindJsonSerializer(jsonSerializerImpl: JsonSerializerImpl): JsonSerializer
}