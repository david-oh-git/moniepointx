package io.davidosemwota.moniepointx.features.home

import android.app.Activity
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import io.davidosemwota.moniepointx.R
import io.davidosemwota.moniepointx.core.designsystem.MoniePointOrange
import io.davidosemwota.moniepointx.core.designsystem.components.CircularIcon
import io.davidosemwota.moniepointx.core.designsystem.components.CircularImage
import io.davidosemwota.moniepointx.core.designsystem.components.CircularImageTint
import io.davidosemwota.moniepointx.core.designsystem.components.CustomSpacer
import io.davidosemwota.moniepointx.core.designsystem.components.Dot
import io.davidosemwota.moniepointx.core.designsystem.components.GeneralPreview
import io.davidosemwota.moniepointx.core.designsystem.components.MoniePointXCard
import io.davidosemwota.moniepointx.core.designsystem.components.PreviewComposable
import io.davidosemwota.moniepointx.core.designsystem.components.SearchTextField
import io.davidosemwota.moniepointx.core.designsystem.components.Spacer16
import io.davidosemwota.moniepointx.core.designsystem.components.Spacer4
import io.davidosemwota.moniepointx.core.designsystem.components.Spacer8

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onBackPressed: () -> Unit,
    navigateToReceiptSearch: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.action.collect { action ->
            onAction(
                action = action,
                onNavigateBack = onBackPressed,
                navigateToReceiptSearch = navigateToReceiptSearch,
                viewModel = viewModel,
            )
        }
    }

    val view = LocalView.current
    val window = (view.context as Activity).window
    val color = MaterialTheme.colorScheme.primary

    SideEffect {
        WindowCompat.getInsetsController(window, view).apply {
            isAppearanceLightStatusBars = false
        }

        window.statusBarColor = color.toArgb()
    }

    HomeScreenContent(
        state = state,
        onAction = viewModel::sendAction,
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surfaceVariant)
    )

}

@Composable
fun HomeScreenContent(
    state: HomeState,
    onAction: (HomeAction) -> Unit,
    modifier: Modifier = Modifier,
) {
    val scrollState = rememberScrollState()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
    ) {
        HomeHeader(
            location = state.location,
            receiptNumber = state.receiptNumber,
            onAction = onAction,
        )

        CustomSpacer(32.dp)

        TrackingInfo(
            shipmentNumber = state.shipmentNumber,
            sender = "Sender",
            senderCity = state.senderCity,
            receiver = "Receiver",
            receiverCity = state.receiverCity,
            status = state.status,
            modifier = Modifier
                .fillMaxWidth()
        )

        CustomSpacer(32.dp)

        AvailableVehicles(
            vehicles = state.vehicles,
        )

    }

}

@Composable
fun HomeHeader(
    location: String,
    receiptNumber: String,
    onAction: (HomeAction) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.primary)
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            CircularImage(
                resId = R.drawable.face,
            )

            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 16.dp),
            ) {
                Row(
                    modifier = Modifier,
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                ) {
                    Icon(
                        painter = painterResource(R.drawable.near_me),
                        contentDescription = "location icon",
                        tint = MaterialTheme.colorScheme.onPrimary,
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "Your location",
                        color = MaterialTheme.colorScheme.onPrimary,
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier
                            .fillMaxWidth(),
                    )
                }
                Row(
                    modifier = Modifier,
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                ) {
                    Text(
                        text = location,
                        color = MaterialTheme.colorScheme.onPrimary,
                        style = MaterialTheme.typography.titleLarge,
                        modifier = Modifier
                            .fillMaxWidth(),
                    )
                }

            }

            CircularIcon(
                imageId = R.drawable.notifications,
                modifier = Modifier
            )
        }

        Spacer16()

        SearchTextField(
            value = receiptNumber,
            hint = "Enter the receipt number...",
            onValueChange = {
                onAction(HomeAction.OnReceiptNumberChange(it))
            },
            readOnly = true,
            onClick = {
                Log.d("xxx","CLICKED")
                onAction(HomeAction.NavigateToReceiptSearch)
            },
        )

        Spacer16()
    }
}

@Composable
fun AvailableVehicles(
    modifier: Modifier = Modifier,
    vehicles: List<Vehicle>,
) {
    val scrollState = rememberScrollState()

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
    ) {
        Text(
            text = "Available Vehicles",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth(),
        )

        Spacer16()

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(scrollState),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            for (vehicle in vehicles) {
                VehicleInfo(vehicle = vehicle)
            }
        }

        Spacer16()
    }

}


@Composable
internal fun VehicleInfo(
    vehicle: Vehicle,
    modifier: Modifier = Modifier,
) {

    MoniePointXCard {
        Column(
            modifier = modifier,
        ) {
            Spacer16()
            Text(
                text = vehicle.type,
                style = MaterialTheme.typography.titleLarge,
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
            )

            Spacer4()

            Text(
                text = vehicle.comment,
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
            )

            Spacer8()

            Image(
                painter = painterResource(id = vehicle.iconId),
                contentDescription = "Vehicle image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .width(200.dp)
                    .aspectRatio(1f)
            )
        }
    }

}

@Composable
fun TrackingInfo(
    status: String,
    sender: String,
    senderCity: String,
    receiver: String,
    receiverCity: String,
    shipmentNumber: String,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
    ) {


        Text(
            text = "Tracking",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth(),
        )

        Spacer16()

        MoniePointXCard(
            elevation = 4.dp,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Column(
                        modifier = Modifier
                            .weight(1f),
                    ) {
                        Text(
                            text = "Shipment Number",
                            style = MaterialTheme.typography.bodySmall,
                        )
                        Text(
                            text = shipmentNumber,
                            style = MaterialTheme.typography.titleLarge,
                        )
                    }


                    Image(
                        painter = painterResource(id = R.drawable.forklift),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(56.dp)
                    )
                }

                Spacer16()

                HorizontalDivider(thickness = 1.dp)

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    CustomerItem(
                        customer = sender,
                        city = senderCity,
                        iconTint = MoniePointOrange.copy(alpha = 0.4f),
                        modifier = Modifier
                            .weight(1f),
                    )

                    Column(
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth(),
                    ) {
                        Text(
                            text = "Time",
                            style = MaterialTheme.typography.bodySmall,
                            modifier = Modifier
                                .fillMaxWidth()
                        )
                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Dot(
                                color = Color.Green,
                                modifier = Modifier
                                    .padding(end = 4.dp)
                            )

                            Text(
                                text = "2 day - 3 days",
                                style = MaterialTheme.typography.labelMedium,
                                modifier = Modifier
                                    .weight(1f)
                                    .fillMaxWidth()
                                    .padding(horizontal = 4.dp)

                            )
                        }
                    }
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    CustomerItem(
                        customer = receiver,
                        city = receiverCity,
                        iconTint = Color.Green.copy(alpha = 0.2f),
                        modifier = Modifier
                            .weight(1f),
                    )

                    Column(
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .weight(1f),
                    ) {
                        Text(
                            text = "Status",
                            style = MaterialTheme.typography.bodySmall,
                        )
                        Text(
                            text = status,
                            style = MaterialTheme.typography.labelMedium,
                            modifier = Modifier
                                .fillMaxWidth()

                        )
                    }
                }

                Spacer16()

                HorizontalDivider(thickness = 1.dp)

                TextButton(
                    onClick = { /* Handle button click */ },
                    modifier = Modifier
                        .fillMaxWidth(),
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.add),
                        contentDescription = "Add icon",
                        tint = MoniePointOrange,
                    )

                    Spacer4()

                    Text(
                        text = "Add Stop",
                        color = MoniePointOrange,
                    )
                }
            }
        }

    }
}

@Composable
fun CustomerItem(
    customer: String,
    city: String,
    iconTint: Color,
    modifier: Modifier = Modifier,
) {

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        CircularImageTint(
            resId = R.drawable.forklift,
            tint = iconTint,
        )

        Column(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 4.dp),
            verticalArrangement = Arrangement.Center,
        ) {
            Text(
                text = customer,
                style = MaterialTheme.typography.bodySmall,
            )

            Text(
                text = city,
                style = MaterialTheme.typography.labelMedium,
            )
        }
    }
}

@GeneralPreview
@Composable
private fun Preview(
    modifier: Modifier = Modifier,
) {
    PreviewComposable {
        HomeScreenContent(
            state = HomeState(),
            onAction = {},
            modifier = modifier,
        )
    }
}