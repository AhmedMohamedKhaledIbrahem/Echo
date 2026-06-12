package com.echo.core.model

import com.echo.core.constant.INITIAL_DATABASE_ID

data class Topic(
    val id: Long = INITIAL_DATABASE_ID,
    val name: String,
)
