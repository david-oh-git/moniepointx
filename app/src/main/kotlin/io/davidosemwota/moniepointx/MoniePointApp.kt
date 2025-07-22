package io.davidosemwota.moniepointx

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.NavBackStack
import io.davidosemwota.moniepointx.navigation.BottomNavigationScreen
import io.davidosemwota.moniepointx.navigation.MoniePointNav
import io.davidosemwota.moniepointx.navigation.NavigationBottomNavigation

@Composable
fun MoniePointXApp(
    backStack: NavBackStack,
    modifier: Modifier = Modifier,
) {
    val bottomNavScreens = listOf(
        BottomNavigationScreen.Home(),
        BottomNavigationScreen.Calculate(),
        BottomNavigationScreen.ShipmentHistory(),
        BottomNavigationScreen.Profile(),
    )
    val showBottomBar = bottomNavScreens.filter {
        it == BottomNavigationScreen.Home() || it == BottomNavigationScreen.Profile()
    }.contains(backStack.lastOrNull())

    Scaffold(
        modifier = modifier.fillMaxSize(),
        bottomBar = {
            if (showBottomBar) {
                NavigationBottomNavigation(
                    backStack = backStack,
                    screens = bottomNavScreens,
                )
            }
        },
    ) { padding ->
        MoniePointNav(
            backStack = backStack,
            modifier = Modifier
                .padding(padding)
                .fillMaxSize(),
        )
    }
}