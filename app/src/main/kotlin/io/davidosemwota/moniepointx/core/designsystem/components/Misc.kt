package io.davidosemwota.moniepointx.core.designsystem.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun Dot(
    modifier: Modifier = Modifier,
    size: Dp = 8.dp,
    color: Color = MaterialTheme.colorScheme.primary,
) {
    Box(
        modifier = modifier
            .size(size)
            .background(
                color = color,
                shape = CircleShape
            )
    )
}


@Composable
internal fun MoniePointHorizontalDivider(
    modifier: Modifier = Modifier,
    colour: Color = MaterialTheme.colorScheme.surfaceContainerLow,
    thickness: Dp = 1.dp,
) {
    HorizontalDivider(
        thickness = thickness,
        color = colour,
        modifier = modifier,
    )
}