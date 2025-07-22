package io.davidosemwota.moniepointx.core.designsystem.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
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


@Composable
internal fun MoniePointVerticalDivider(
    modifier: Modifier = Modifier,
    colour: Color = MaterialTheme.colorScheme.surfaceContainerLow,
    thickness: Dp = 1.dp,
) {
    VerticalDivider(
        thickness = thickness,
        color = colour,
        modifier = modifier,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun CalculateDropDown(
    value: String,
    hint: String,
    options: List<String>,
    onValueChange: (String) -> Unit,
    @DrawableRes iconId: Int,
    modifier: Modifier = Modifier,
    textStyle: TextStyle = MaterialTheme.typography.bodyLarge,
) {
    var expanded by remember { mutableStateOf(false) }

    MoniePointXCard(
        shape = RoundedCornerShape(16.dp),
        elevation = 0.dp,
        modifier = modifier,
    ) {
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded },
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background),
        ) {
            Row(
                verticalAlignment = Alignment.Companion.CenterVertically,
                modifier = Modifier.Companion
                    .fillMaxWidth()
                    .padding(8.dp)
                    .height(IntrinsicSize.Min)
                    .background(MaterialTheme.colorScheme.background)
            ) {
                Image(
                    painter = painterResource(iconId),
                    contentDescription = "Icon",
                    modifier = Modifier
                        .size(32.dp)
                )

                MoniePointVerticalDivider(
                    modifier = Modifier
                        .padding(start = 8.dp),
                )

                TextField(
                    value = value,
                    onValueChange = { },
                    singleLine = true,
                    readOnly = true,
                    colors = TextFieldDefaults.colors(
                        unfocusedIndicatorColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent,
                        focusedContainerColor = MaterialTheme.colorScheme.background,
                        unfocusedContainerColor = MaterialTheme.colorScheme.background,
                    ),
                    textStyle = textStyle,
                    shape = androidx.compose.foundation.shape.RoundedCornerShape(8.dp),
                    trailingIcon = {
                        Icon(
                            imageVector = Icons.Filled.KeyboardArrowDown,
                            contentDescription = "Dropdown Icon",
                            modifier = Modifier
                                .rotate(if(expanded) 180f else 0f)
                                .padding(2.dp)
                        )
                    },
                    placeholder = {
                        Text(
                            text = hint,
                            style = textStyle,
                        )
                    },
                    modifier = Modifier
                        .menuAnchor(
                            type = MenuAnchorType.PrimaryNotEditable,
                            enabled = true,
                        )
                        .weight(1f),
                )
            }

            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                options.forEach { selectionOption ->
                    DropdownMenuItem(
                        text = { Text(selectionOption) },
                        onClick = {
                            onValueChange(selectionOption)
                            expanded = false
                        }
                    )
                }
            }
        }
    }


}