package io.davidosemwota.moniepointx.core.designsystem.components

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
internal fun MoniePointPill(
    modifier: Modifier = Modifier,
    colour: Color = MaterialTheme.colorScheme.surfaceVariant,
    content: @Composable (ColumnScope.() -> Unit),
) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = colour,
        ),
        shape = RoundedCornerShape(percent = 50),
        content = content,
    )
}