package io.davidosemwota.moniepointx

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.VisibilityThreshold
import androidx.compose.animation.core.spring
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.IntOffset
import androidx.navigation3.runtime.NavBackStack
import io.davidosemwota.moniepointx.navigation.MoniePointNav
import io.davidosemwota.moniepointx.navigation.NavigationBottomNavigation

@Composable
fun MoniePointXApp(
    backStack: NavBackStack,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        bottomBar = {
            AnimatedVisibility(
                visible = true,
                enter = slideInVertically(
                    initialOffsetY = { it },
                ),
                exit = slideOutVertically(
                    animationSpec = spring(
                        stiffness = Spring.StiffnessMediumLow,
                        visibilityThreshold = IntOffset.VisibilityThreshold,
                    ),
                    targetOffsetY = { it },
                ),
            ) {
                NavigationBottomNavigation(
                    backStack = backStack,
                )
            }
        },
    ) { padding ->
        MoniePointNav(
            backStack = backStack,
            modifier = Modifier.padding(padding),
        )
    }
}