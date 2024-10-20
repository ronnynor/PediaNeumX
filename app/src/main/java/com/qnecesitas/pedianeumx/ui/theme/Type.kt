package com.qnecesitas.pedianeumx.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.qnecesitas.pedianeumx.R

val actorFontFamily = FontFamily(
    Font(R.font.actor_regular, weight = FontWeight.Normal),
)

val stardosFontFamily = FontFamily(
    Font(R.font.stardos_stencil_regular, weight = FontWeight.Normal),
    Font(R.font.stardos_stencil_bold, weight = FontWeight.Bold),
)


val baseline = Typography()

val AppTypography = Typography(
    displayLarge = baseline.displayLarge.copy(fontFamily = stardosFontFamily),
    displayMedium = baseline.displayMedium.copy(fontFamily = stardosFontFamily),
    displaySmall = baseline.displaySmall.copy(fontFamily = stardosFontFamily),
    headlineLarge = baseline.headlineLarge.copy(fontFamily = stardosFontFamily),
    headlineMedium = baseline.headlineMedium.copy(fontFamily = stardosFontFamily),
    headlineSmall = baseline.headlineSmall.copy(fontFamily = stardosFontFamily),
    titleLarge = baseline.titleLarge.copy(fontFamily = stardosFontFamily),
    titleMedium = baseline.titleMedium.copy(fontFamily = stardosFontFamily),
    titleSmall = baseline.titleSmall.copy(fontFamily = stardosFontFamily),
    bodyLarge = baseline.bodyLarge.copy(fontFamily = actorFontFamily),
    bodyMedium = baseline.bodyMedium.copy(fontFamily = actorFontFamily),
    bodySmall = baseline.bodySmall.copy(fontFamily = actorFontFamily),
    labelLarge = baseline.labelLarge.copy(fontFamily = actorFontFamily),
    labelMedium = baseline.labelMedium.copy(fontFamily = actorFontFamily),
    labelSmall = baseline.labelSmall.copy(fontFamily = actorFontFamily),
)