package io.davidosemwota.moniepointx.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.navigation3.runtime.NavKey
import io.davidosemwota.moniepointx.R
import kotlinx.serialization.Serializable

@Serializable
sealed interface BottomNavigationScreen : NavKey {

    @get:StringRes
    val title: Int

    @Serializable
    data class Home(
        @StringRes override val title: Int = R.string.home,
    ) : BottomNavigationScreen

    @Serializable
    data class Calculate(
        @StringRes override val title: Int = R.string.calculate,
    ) : BottomNavigationScreen

    @Serializable
    data class ShipmentHistory(
        @StringRes override val title: Int = R.string.shipment,
    ) : BottomNavigationScreen

    @Serializable
    data class Profile(
        @StringRes override val title: Int = R.string.profile,
    ) : BottomNavigationScreen


}

@Serializable
data object ReceiptSearch : NavKey

@get:DrawableRes
val BottomNavigationScreen.iconId: Int
    get() = when (this) {
        BottomNavigationScreen.Home() -> R.drawable.ic_home
        BottomNavigationScreen.Calculate() -> R.drawable.calculate
        BottomNavigationScreen.ShipmentHistory() -> R.drawable.history
        BottomNavigationScreen.Profile() -> R.drawable.person
        else -> R.drawable.ic_home
    }
