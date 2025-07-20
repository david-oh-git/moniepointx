package io.davidosemwota.moniepointx.core.designsystem

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import io.davidosemwota.moniepointx.R

private val defaultTypography = Typography(
        displayLarge = TextStyle(
            fontWeight = FontWeight.Normal,
            fontSize = 57.sp,
            lineHeight = 64.sp,
            letterSpacing = 0.sp
        ),
        displayMedium = TextStyle(
            fontWeight = FontWeight.Normal,
            fontSize = 45.sp,
            lineHeight = 52.sp,
            letterSpacing = 0.sp
        ),
        displaySmall = TextStyle(
                fontWeight = FontWeight.Normal,
                fontSize = 36.sp,
                lineHeight = 44.sp,
                letterSpacing = 0.sp
            ),
        headlineLarge = TextStyle(
                fontWeight = FontWeight.Normal,
                fontSize = 32.sp,
                lineHeight = 40.sp,
                letterSpacing = 0.sp
            ),
        headlineMedium = TextStyle(
                fontWeight = FontWeight.Normal,
                fontSize = 28.sp,
                lineHeight = 36.sp,
                letterSpacing = 0.sp
            ),
        headlineSmall = TextStyle(
                fontWeight = FontWeight.Normal,
                fontSize = 24.sp,
                lineHeight = 32.sp,
                letterSpacing = 0.sp
            ),
        titleLarge = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = 22.sp,
                lineHeight = 28.sp,
                letterSpacing = 0.sp
            ),
        titleMedium = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                lineHeight = 24.sp,
                letterSpacing = 0.sp
            ),
        titleSmall = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                lineHeight = 20.sp,
                letterSpacing = 0.sp
            ),
        bodyLarge = TextStyle(
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp,
                lineHeight = 24.sp,
                letterSpacing = 0.5.sp
            ),
        bodyMedium = TextStyle(
                fontWeight = FontWeight.Normal,
                fontSize = 14.sp,
                lineHeight = 20.sp,
                letterSpacing = 0.25.sp
            ),
        bodySmall = TextStyle(
            fontWeight = FontWeight.Normal,
            fontSize = 12.sp,
            lineHeight = 16.sp,
            letterSpacing = 0.4.sp
        ),
        labelLarge = TextStyle(
                fontWeight = FontWeight.Medium,
                fontSize = 14.sp,
                lineHeight = 20.sp,
                letterSpacing = 0.1.sp
            ),
        labelMedium = TextStyle(
                fontWeight = FontWeight.Medium,
                fontSize = 12.sp,
                lineHeight = 16.sp,
                letterSpacing = 0.5.sp
            ),
        labelSmall = TextStyle(
                fontWeight = FontWeight.Medium,
                fontSize = 10.sp,
                lineHeight = 16.sp,
                letterSpacing = 0.sp
            )
    )

private val poppins = FontFamily(
    Font(R.font.poppins_regular),
    Font(R.font.poppins_light),
    Font(R.font.poppins_medium),
    Font(R.font.poppins_bold),
    Font(R.font.poppins_italic),
    Font(R.font.poppins_semi_bold),
)

val poppinsTypography = Typography(
    displayLarge = defaultTypography.displayLarge.copy(fontFamily = poppins),
    displayMedium = defaultTypography.displayMedium.copy(fontFamily = poppins),
    displaySmall = defaultTypography.displaySmall.copy(fontFamily = poppins),
    headlineLarge = defaultTypography.headlineLarge.copy(fontFamily = poppins),
    bodyLarge = defaultTypography.bodyLarge.copy(fontFamily = poppins),
    bodyMedium = defaultTypography.bodyMedium.copy(fontFamily = poppins),
    bodySmall = defaultTypography.bodySmall.copy(fontFamily = poppins),
    headlineMedium = defaultTypography.headlineMedium.copy(fontFamily = poppins),
    headlineSmall = defaultTypography.headlineSmall.copy(fontFamily = poppins),
    labelLarge = defaultTypography.labelLarge.copy(fontFamily = poppins),
    labelMedium = defaultTypography.labelMedium.copy(fontFamily = poppins),
    labelSmall = defaultTypography.labelSmall.copy(fontFamily = poppins),
    titleLarge = defaultTypography.titleLarge.copy(fontFamily = poppins),
    titleMedium = defaultTypography.titleMedium.copy(fontFamily = poppins),
    titleSmall = defaultTypography.titleSmall.copy(fontFamily = poppins),
)