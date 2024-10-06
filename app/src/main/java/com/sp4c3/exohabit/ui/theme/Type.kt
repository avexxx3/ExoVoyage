package com.sp4c3.exohabit.ui.theme
import androidx.compose.material3.Typography
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.sp4c3.exohabit.R


val barlowFamily = FontFamily(
    Font(R.font.barlow_regular, FontWeight.Normal),
    Font(R.font.barlow_light, FontWeight.Light),
    Font(R.font.barlow_medium, FontWeight.Medium),
    Font(R.font.barlow_semibold, FontWeight.SemiBold),
    Font(R.font.barlow_bold, FontWeight.Bold),
    Font(R.font.barlow_thin, FontWeight.Thin),
    Font(R.font.barlow_extralight, FontWeight.ExtraLight),
    Font(R.font.barlow_extrabold, FontWeight.ExtraBold))

val openSansFamily = FontFamily(
    Font(R.font.opensans, FontWeight.Normal)
)

val montserratFamily = FontFamily(
    Font(R.font.montserrat, FontWeight.Normal)
)

val ralewayFamily = FontFamily(
    Font(R.font.raleway, FontWeight.Normal),
)

val baseline = Typography()

val AppTypography = Typography(
    displayLarge = baseline.displayLarge.copy(fontFamily = barlowFamily),
    displayMedium = baseline.displayMedium.copy(fontFamily = barlowFamily),
    displaySmall = baseline.displaySmall.copy(fontFamily = barlowFamily),
    headlineLarge = baseline.headlineLarge.copy(fontFamily = barlowFamily),
    headlineMedium = baseline.headlineMedium.copy(fontFamily = barlowFamily),
    headlineSmall = baseline.headlineSmall.copy(fontFamily = barlowFamily),
    titleLarge = baseline.titleLarge.copy(fontFamily = barlowFamily),
    titleMedium = baseline.titleMedium.copy(fontFamily = barlowFamily),
    titleSmall = baseline.titleSmall.copy(fontFamily = barlowFamily),
    bodyLarge = baseline.bodyLarge.copy(fontFamily = openSansFamily),
    bodyMedium = baseline.bodyMedium.copy(fontFamily = openSansFamily),
    bodySmall = baseline.bodySmall.copy(fontFamily = openSansFamily),
    labelLarge = baseline.labelLarge.copy(fontFamily = openSansFamily),
    labelMedium = baseline.labelMedium.copy(fontFamily = openSansFamily),
    labelSmall = baseline.labelSmall.copy(fontFamily = openSansFamily),
)

