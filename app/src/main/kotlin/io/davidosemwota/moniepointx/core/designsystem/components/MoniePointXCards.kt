package io.davidosemwota.moniepointx.core.designsystem.components

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp


@Composable
internal fun MoniePointXCard(
    modifier: Modifier = Modifier,
    elevation: Dp = 8.dp,
    cardColour: Color = MaterialTheme.colorScheme.background,
    shape: Shape = CardDefaults.shape,
    content: @Composable (ColumnScope.() -> Unit),
) {
    Card(
        elevation = CardDefaults.cardElevation(
            defaultElevation = elevation,
        ),
        colors = CardDefaults.cardColors(
            containerColor = cardColour,
        ),
        modifier = modifier
            .fillMaxWidth(),
        shape = shape,
        content = content,
    )
}