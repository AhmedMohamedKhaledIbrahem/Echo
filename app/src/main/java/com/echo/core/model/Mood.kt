package com.echo.core.model

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color
import com.echo.R
import com.echo.core.json.ColorSerializer
import com.echo.ui.theme.Excited25
import com.echo.ui.theme.Excited35
import com.echo.ui.theme.Excited80
import com.echo.ui.theme.Neutral25
import com.echo.ui.theme.Neutral35
import com.echo.ui.theme.Neutral80
import com.echo.ui.theme.Peaceful25
import com.echo.ui.theme.Peaceful35
import com.echo.ui.theme.Peaceful80
import com.echo.ui.theme.Primary30
import com.echo.ui.theme.Primary95
import com.echo.ui.theme.Sad25
import com.echo.ui.theme.Sad35
import com.echo.ui.theme.Sad80
import com.echo.ui.theme.Secondary80
import com.echo.ui.theme.Stressed25
import com.echo.ui.theme.Stressed35
import com.echo.ui.theme.Stressed80
import kotlinx.serialization.Serializable
@Serializable
sealed class Mood(
    @DrawableRes
    val icon: Int,
    val name: String,
    @DrawableRes
    val outlinedIcon: Int,
    @Serializable(with = ColorSerializer::class)
    val moodButtonColor: Color,
    @Serializable(with = ColorSerializer::class)
    val moodTrackColor: Color,
    @Serializable(with = ColorSerializer::class)
    val moodBackgroundColor: Color,
) {
    @Serializable
    data object Stressed : Mood(
        icon = R.drawable.mood_stressed,
        outlinedIcon = R.drawable.mood_stressed_outline,
        name = "Stressed",
        moodButtonColor = Stressed80,
        moodTrackColor = Stressed35,
        moodBackgroundColor = Stressed25,
    )

    @Serializable
    data object Sad : Mood(
        icon = R.drawable.mood_sad,
        outlinedIcon = R.drawable.mood_sad_outline,
        name = "Sad",
        moodButtonColor = Sad80,
        moodTrackColor = Sad35,
        moodBackgroundColor = Sad25,
    )

    @Serializable
    data object Neutral : Mood(
        icon = R.drawable.mood_neutral,
        outlinedIcon = R.drawable.mood_neutral_outline,
        name = "Neutral",
        moodButtonColor = Neutral80,
        moodTrackColor = Neutral35,
        moodBackgroundColor = Neutral25,
    )

    @Serializable
    data object Peaceful : Mood(
        icon = R.drawable.mood_peaceful,
        outlinedIcon = R.drawable.mood_peaceful_outline,
        name = "Peaceful",
        moodButtonColor = Peaceful80,
        moodTrackColor = Peaceful35,
        moodBackgroundColor = Peaceful25,
    )

    @Serializable
    data object Excited : Mood(
        icon = R.drawable.mood_excited,
        outlinedIcon = R.drawable.mood_excited_outline,
        name = "Excited",
        moodButtonColor = Excited80,
        moodTrackColor = Excited35,
        moodBackgroundColor = Excited25,
    )

    @Serializable
    data object Undefined : Mood(
        icon = R.drawable.mood_excited,
        outlinedIcon = R.drawable.mood_excited_outline,
        name = "Undefined",
        moodButtonColor = Primary30,
        moodTrackColor = Secondary80,
        moodBackgroundColor = Primary95,
    )

    companion object {
        fun allMoods(): List<Mood> = listOf(Stressed, Sad, Neutral, Peaceful, Excited)
    }
}

fun String.toMood(): Mood  {
    return when (this) {
        "Stressed" -> Mood.Stressed
        "Sad" -> Mood.Sad
        "Neutral" -> Mood.Neutral
        "Peaceful" -> Mood.Peaceful
        "Excited" -> Mood.Excited
        else -> Mood.Undefined
    }
}