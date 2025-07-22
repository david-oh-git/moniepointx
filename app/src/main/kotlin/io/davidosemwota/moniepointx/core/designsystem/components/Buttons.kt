package io.davidosemwota.moniepointx.core.designsystem.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import io.davidosemwota.moniepointx.R
import io.davidosemwota.moniepointx.core.designsystem.MoniePointOrange


@Composable
internal fun MoniePointButton(
    onClick: () -> Unit,
    text: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    colour: Color = MoniePointOrange,
) {
    val animationSpec = remember { tween<Color>(durationMillis = 600) }
    val buttonColour by animateColorAsState(
        animationSpec = animationSpec,
        targetValue = if (enabled) colour else MaterialTheme.colorScheme.surfaceVariant,
        label = "buttonColourAnimation"
    )

    Button(
        enabled = enabled,
        onClick = onClick,
        colors = ButtonDefaults.buttonColors().copy(
            containerColor = buttonColour
        ),
        modifier = modifier,
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier
                .padding(8.dp)
        )
    }
}


@Composable
internal fun MoniePointSelectButton(
    onClick: () -> Unit,
    text: String,
    modifier: Modifier = Modifier,
) {
    var isSelected by remember { mutableStateOf(false) }
    val animationSpec = remember { tween<Color>(durationMillis = 600) }
    val buttonColour by animateColorAsState(
        animationSpec = animationSpec,
        targetValue = if (isSelected) Color.Black else Color.White,
        label = "buttonColourAnimation"
    )

    val contentColour by animateColorAsState(
        animationSpec = animationSpec,
        targetValue = if (isSelected) Color.White else Color.Black,
        label = "buttonColourAnimation"
    )
    Button(
        onClick = {
            isSelected = !isSelected
            onClick()
        },
        colors = ButtonDefaults.buttonColors().copy(
            containerColor = buttonColour,
            contentColor = contentColour,
        ),
        shape = RoundedCornerShape(12.dp),
        modifier = modifier,
    ) {

        if (isSelected) {
            Icon(
                painter = painterResource(R.drawable.check),
                contentDescription = "Check Icon",
                tint = contentColour,
            )
        }

        Text(
            text = text,
            style = MaterialTheme.typography.titleSmall,
            modifier = Modifier
                .padding(
                    vertical = 8.dp,
                    horizontal = 2.dp,
                )
        )
    }
}