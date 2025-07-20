package io.davidosemwota.moniepointx.core.designsystem.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import io.davidosemwota.moniepointx.core.designsystem.MoniePointOrange

@Composable
fun CircularImage(
    @DrawableRes resId: Int,
    modifier: Modifier = Modifier,
    size: Dp = 48.dp,
) {
    Box(
        modifier = modifier
            .size(size)
            .clip(CircleShape)
            .border(2.dp, Color.Transparent, CircleShape)
    ) {
        Image(
            painter = painterResource(id = resId),
            contentDescription = "Circular Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier.matchParentSize()
        )
    }
}

@Composable
fun CircularImageTint(
    @DrawableRes resId: Int,
    modifier: Modifier = Modifier,
    size: Dp = 48.dp,
    tint: Color = MoniePointOrange,
) {
    Box(
        modifier = modifier
            .size(size)
            .clip(CircleShape)
            .border(2.dp, Color.Transparent, CircleShape)
            .background(tint)
    ) {
        Image(
            painter = painterResource(id = resId),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(24.dp)
                .align(Alignment.Center),
        )
    }
}

@Composable
fun CircularIcon(
    @DrawableRes imageId: Int,
    modifier: Modifier = Modifier,
    size: Dp = 48.dp,
    iconSize: Dp = 32.dp,
    backgroundColor: Color = MaterialTheme.colorScheme.background,
    tint: Color = LocalContentColor.current,
) {
    Box(
        modifier = modifier
            .size(size)
            .clip(CircleShape)
            .border(1.dp, Color.Transparent, CircleShape)
            .background(backgroundColor)
    ) {
        Icon(
            painter = painterResource(id = imageId),
            contentDescription = null,
            tint = tint,
            modifier = Modifier
                .size(iconSize)
                .align(Alignment.Center)
        )

    }
}