package io.davidosemwota.moniepointx.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.Alignment
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
import io.davidosemwota.moniepointx.features.estimatedamount.EstimatedAmountScreen
import io.davidosemwota.moniepointx.features.home.HomeScreen
import io.davidosemwota.moniepointx.features.profile.ProfileScreen
import io.davidosemwota.moniepointx.features.receiptsearch.ReceiptSearchScreen
import io.davidosemwota.moniepointx.features.shipmenthistory.ShipmentHistoryScreen

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun MoniePointNav(
    backStack: NavBackStack,
    modifier: Modifier = Modifier,
) {

    SharedTransitionLayout {
        NavDisplay(
            modifier = modifier.fillMaxSize(),
            backStack = backStack,
            onBack = { backStack.removeLastOrNull() },
            entryProvider = entryProvider {

                entry<BottomNavigationScreen.Home> {
                    HomeScreen(
                        onBackPressed = { backStack.removeLastOrNull() },
                        navigateToReceiptSearch = { backStack.add(ReceiptSearch) },
                        sharedTransitionScope = this@SharedTransitionLayout,
                        modifier = Modifier.fillMaxSize(),
                    )
                }

                entry<BottomNavigationScreen.Calculate> {
                    CalculateScreen(
                        onBackPressed = { backStack.removeLastOrNull() },
                        navigateToEstimatedAmount = { backStack.add(EstimatedAmount) },
                        modifier = Modifier.fillMaxSize(),
                    )
                }

                entry<BottomNavigationScreen.ShipmentHistory> {
                    ShipmentHistoryScreen(
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

                entry<ReceiptSearch> {
                    ReceiptSearchScreen(
                        onBackPressed = { backStack.removeLastOrNull() },
                        sharedTransitionScope = this@SharedTransitionLayout,
                    )
                }

                entry<EstimatedAmount>(
                    metadata = NavDisplay.transitionSpec {
                        slideInVertically(
                            initialOffsetY = { it },
                            animationSpec = tween(200)
                        ) togetherWith ExitTransition.KeepUntilTransitionsFinished
                    } + NavDisplay.popTransitionSpec {
                        EnterTransition.None togetherWith
                                slideOutVertically(
                                    targetOffsetY = { it },
                                    animationSpec = tween(1000)
                                )
                    } + NavDisplay.predictivePopTransitionSpec {
                        EnterTransition.None togetherWith
                                slideOutVertically(
                                    targetOffsetY = { it },
                                    animationSpec = tween(1000)
                                )
                    }
                ) {

                    EstimatedAmountScreen(
                        onBackPressed = { backStack.removeLastOrNull() },
                        navigateToHome = { backStack.add(BottomNavigationScreen.Home())}
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


}


@Composable
fun NavigationBottomNavigation(
    backStack: NavBackStack,
    screens: List<BottomNavigationScreen>,
    modifier: Modifier = Modifier,
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

                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        if (selected) {
                            Spacer(
                                modifier = Modifier
                                    .height(4.dp)
                                    .background(MaterialTheme.colorScheme.primary)
                                    .fillMaxWidth()
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                        }

                        Icon(
                            painter = painterResource(id = screen.iconId),
                            contentDescription = stringResource(
                                R.string.main_screen_bottom_nav_icon_desc
                            ),
                            tint = if (selected) MaterialTheme.colorScheme.primary
                            else LocalContentColor.current,
                        )
                    }

                },
                label = { Text(text = stringResource(id = screen.title)) },
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = Color.Transparent,
                ),
            )
        }
    }

}