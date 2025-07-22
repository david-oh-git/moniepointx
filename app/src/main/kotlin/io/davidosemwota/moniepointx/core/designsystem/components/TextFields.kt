package io.davidosemwota.moniepointx.core.designsystem.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import io.davidosemwota.moniepointx.R
import io.davidosemwota.moniepointx.core.designsystem.MoniePointOrange


@Composable
internal fun SearchTextField(
    value: String,
    hint: String,
    onValueChange: (String) -> Unit,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    leadingIcon: ImageVector = Icons.Default.Search,
    readOnly: Boolean = false,
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        readOnly = readOnly,
        singleLine = true,
        placeholder = {
            Text(
                text = hint,
                style = MaterialTheme.typography.bodyMedium,
            )
        },
        leadingIcon = {
            Icon(
                imageVector = leadingIcon,
                contentDescription = null,
            )
        },
        trailingIcon = {
            CircularIcon(
                imageId = R.drawable.flip,
                backgroundColor = MoniePointOrange,
                tint = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier
                    .rotate(90f)
                    .padding(8.dp)
            )
        },
        shape = RoundedCornerShape(percent = 50),
        colors = OutlinedTextFieldDefaults.colors(
            unfocusedContainerColor = MaterialTheme.colorScheme.background,
            focusedContainerColor = MaterialTheme.colorScheme.background,
        ),
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp)
            .pointerInteropFilter {
                if (readOnly) {
                    onClick()
                    true
                }
                false
            }
    )

}

@Composable
internal fun CalculateTextField(
    value: String,
    hint: String,
    onValueChange: (String) -> Unit,
    @DrawableRes iconId: Int,
    modifier: Modifier = Modifier,
    textStyle: TextStyle = MaterialTheme.typography.titleLarge,
    iconSIze: Dp = 32.dp,
    keyboardType: KeyboardType = KeyboardType.Text,
) {
    MoniePointXCard(
        shape = RoundedCornerShape(8.dp),
        elevation = 0.dp,
        cardColour = MaterialTheme.colorScheme.surfaceVariant,
        modifier = modifier,
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .height(IntrinsicSize.Min)
                .background(MaterialTheme.colorScheme.surfaceVariant)
        ) {
            Icon(
                painter = painterResource(iconId),
                contentDescription = "Icon",
                modifier = Modifier
                    .size(iconSIze)
            )

            MoniePointVerticalDivider(
                modifier = Modifier
                    .padding(start = 8.dp),
            )

            TextField(
                value = value,
                onValueChange = onValueChange,
                singleLine = true,
                placeholder = {
                    Text(
                        text = hint,
                        style = textStyle,
                    )
                },
                colors = TextFieldDefaults.colors(
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                    focusedContainerColor = MaterialTheme.colorScheme.surfaceVariant,
                    unfocusedContainerColor = MaterialTheme.colorScheme.surfaceVariant,
                ),
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = keyboardType,
                ),
                textStyle = textStyle.copy(fontWeight = FontWeight.ExtraBold),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .weight(1f),
            )
        }
    }
}

@Preview
@Composable
private fun PreviewCalculateTextField(modifier: Modifier = Modifier) {
    PreviewComposable {
        CalculateTextField(
            value = "",
            hint = "Sender location",
            onValueChange = {},
            iconId = R.drawable.archive,
            modifier = modifier,
        )
    }
}
