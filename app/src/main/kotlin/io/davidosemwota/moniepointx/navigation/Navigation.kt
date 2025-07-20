package io.davidosemwota.moniepointx.navigation

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.entry
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberSavedStateNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import androidx.navigation3.ui.rememberSceneSetupNavEntryDecorator
import io.davidosemwota.moniepointx.R
import io.davidosemwota.moniepointx.features.calculate.CalculateScreen
import io.davidosemwota.moniepointx.features.home.HomeScreen
import io.davidosemwota.moniepointx.features.profile.ProfileScreen
import io.davidosemwota.moniepointx.features.shipment.ShipmentScreen

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun MoniePointNav(
    backStack: NavBackStack,
    modifier: Modifier = Modifier,
) {

    NavDisplay(
        modifier = modifier.fillMaxSize(),
        backStack = backStack,
        onBack = { backStack.removeLastOrNull() },
        entryProvider = entryProvider {

            entry<BottomNavigationScreen.Home> {
                HomeScreen(
                    onBackPressed = { backStack.removeLastOrNull() },
                    navigateToReceiptSearch = { },
                    modifier = Modifier.fillMaxSize(),
                )
            }

            entry<BottomNavigationScreen.Calculate> {
                CalculateScreen(
                    onBackPressed = { backStack.removeLastOrNull() },
                    modifier = Modifier.fillMaxSize(),
                )
            }

            entry<BottomNavigationScreen.Shipment> {
                ShipmentScreen(
                    onBackPressed = { backStack.removeLastOrNull() },
                    modifier = Modifier.fillMaxSize(),
                )
            }

            entry<BottomNavigationScreen.Profile> {
                ProfileScreen(
                    onBackPressed = { backStack.removeLastOrNull() },
                    modifier = Modifier.fillMaxSize(),
                )
            }
        },
        entryDecorators = listOf(
            rememberSceneSetupNavEntryDecorator(),
            rememberSavedStateNavEntryDecorator(),
//            rememberViewModelStoreNavEntryDecorator(),
        ),
    )
}


@Composable
fun NavigationBottomNavigation(
    backStack: NavBackStack,
    modifier: Modifier = Modifier,
    screens: List<BottomNavigationScreen> = listOf(
        BottomNavigationScreen.Home(),
        BottomNavigationScreen.Calculate(),
        BottomNavigationScreen.Shipment(),
        BottomNavigationScreen.Profile(),
    ),
) {
    val currentScreen = backStack.lastOrNull()

    NavigationBar(
        modifier = modifier
            .padding(8.dp)
            .clip(shape = RoundedCornerShape(8.dp)),
        containerColor = MaterialTheme.colorScheme.background,
    ) {
        screens.forEach { screen ->
            val selected = currentScreen == screen

            NavigationBarItem(
                selected = selected,
                onClick = {
                    backStack.add(screen)
                },
                icon = {

                    Icon(
                        painter = painterResource(id = screen.iconId),
                        contentDescription = stringResource(
                            R.string.main_screen_bottom_nav_icon_desc
                        ),
                        tint = if (selected) MaterialTheme.colorScheme.primary
                        else LocalContentColor.current,
                    )
                },
                label = { Text(text = stringResource(id = screen.title)) },
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = Color.Transparent,
                )
            )
        }
    }

}