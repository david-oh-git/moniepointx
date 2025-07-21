package io.davidosemwota.moniepointx.features.shipmenthistory

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import io.davidosemwota.moniepointx.R
import io.davidosemwota.moniepointx.core.designsystem.MoniePointOrange
import io.davidosemwota.moniepointx.core.designsystem.components.Dot
import io.davidosemwota.moniepointx.core.designsystem.components.GeneralPreview
import io.davidosemwota.moniepointx.core.designsystem.components.MoniePointPill
import io.davidosemwota.moniepointx.core.designsystem.components.MoniePointXCard
import io.davidosemwota.moniepointx.core.designsystem.components.PreviewComposable
import io.davidosemwota.moniepointx.core.designsystem.components.Spacer16
import io.davidosemwota.moniepointx.core.designsystem.components.Spacer8

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShipmentHistoryScreen(
    onBackPressed: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: ShipmentHistoryViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    val snackBarHostState = remember { SnackbarHostState() }
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        viewModel.action.collect { action ->
            onAction(
                action = action,
                onNavigateBack = onBackPressed,
                viewModel = viewModel,
            )
        }
    }

    Scaffold(
        modifier = modifier.fillMaxSize(),
        snackbarHost = { SnackbarHost(snackBarHostState) },
        topBar = {
            Header(
                state = state,
                onAction = viewModel::sendAction,
            )
        },
        containerColor = MaterialTheme.colorScheme.surfaceContainerLow
    ) { innerPadding ->

        ShipmentHistoryScreenContent(
            state = state,
            onAction = viewModel::sendAction,
            modifier = Modifier.padding(innerPadding)
        )
    }


}

@Composable
fun ShipmentHistoryScreenContent(
    state: ShipmentHistoryState,
    onAction: (ShipmentHistoryAction) -> Unit,
    modifier: Modifier = Modifier,
) {
    val scrollState = rememberScrollState()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(horizontal = 16.dp)
    ) {
        Spacer16()

        Text(
            text = "Shipments",
            style = MaterialTheme.typography.titleLarge,
        )

        Spacer16()

        for (shipment in state.tabShipment) {
            ShipmentItem(
                shipment = shipment,
                modifier = Modifier
                    .fillMaxWidth(),
            )
            Spacer16()
        }
    }

}

@Composable
internal fun ShipmentItem(
    shipment: Shipment,
    modifier: Modifier = Modifier,
) {
    MoniePointXCard(
        elevation = 4.dp,
        modifier = modifier,
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Column(
                modifier = Modifier
                    .weight(1f),
            ) {
                MoniePointPill {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .padding(
                                horizontal = 8.dp,
                                vertical = 4.dp,
                            )
                    ) {
                        Icon(
                            painter = painterResource(shipment.status.iconId),
                            contentDescription = "Shipment status icon",
                            tint = shipment.status.colour,
                            modifier = Modifier
                                .size(16.dp)
                        )

                        Spacer(modifier = Modifier.width(4.dp))

                        Text(
                            text = stringResource(shipment.status.textId),
                            color = shipment.status.colour,
                            style = MaterialTheme.typography.bodySmall,
                        )
                    }
                }

                Spacer8()

                Text(
                    text = shipment.title,
                    style = MaterialTheme.typography.titleSmall,
                )

                Text(
                    text = shipment.description,
                    style = MaterialTheme.typography.bodyMedium,
                )

                Spacer16()

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        text = "$${shipment.amount} ${shipment.currency}",
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.primary,
                    )

                    Dot(
                        color = Color.Gray,
                        size = 4.dp,
                        modifier = Modifier
                            .padding(horizontal = 4.dp)
                    )

                    Text(
                        text = shipment.date,
                        style = MaterialTheme.typography.bodyMedium,
                    )
                }

            }

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                Image(
                    painter = painterResource(R.drawable.box),
                    contentDescription = "Shipment box image",
                    modifier = Modifier
                        .size(60.dp)
                )
            }
        }
    }
}

@Composable
internal fun Header(
    state: ShipmentHistoryState,
    onAction: (ShipmentHistoryAction) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.primary),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth(),
        ) {
            Spacer(
                modifier = Modifier
                    .width(4.dp)
            )
            Icon(
                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                contentDescription = "Navigate back icon",
                tint = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier
                    .size(40.dp)
                    .clickable {
                        onAction(
                            ShipmentHistoryAction.NavigateBack
                        )
                    }
            )

            Text(
                text = "Shipment History",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onPrimary,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .weight(1f)
                    .padding(16.dp)
            )

            Spacer16()

        }

        Column(
            modifier = Modifier
                .fillMaxWidth(),
        ) {

            ScrollableTabRow(
                selectedTabIndex = state.selectedTabIndex,
                containerColor = MaterialTheme.colorScheme.primary,
                edgePadding = 20.dp,
                indicator =  @Composable { tabPositions ->
                    if (state.selectedTabIndex < tabPositions.size) {
                        TabRowDefaults.SecondaryIndicator(
                            Modifier.tabIndicatorOffset(
                                tabPositions[state.selectedTabIndex]
                            ),
                            color = MoniePointOrange,
                        )
                    }
                },
                modifier = Modifier
                    .fillMaxWidth(),
            ) {
                state.tabs.forEachIndexed { index, tab ->
                    Tab(
                        text = {

                            Row(
                                horizontalArrangement = Arrangement.Center,
                            ) {
                                Text(
                                    text = stringResource(tab.nameId),
                                    style = MaterialTheme.typography.titleMedium
                                )
                                Spacer(modifier = Modifier.width(2.dp))
                            }

                        },
                        selectedContentColor = MaterialTheme.colorScheme.onPrimary,
                        unselectedContentColor = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.5f),
                        selected = state.selectedTabIndex == index,
                        onClick = { onAction(ShipmentHistoryAction.SelectTab(index)) }
                    )
                }

            }

        }


    }

}

@GeneralPreview
@Composable
private fun Preview(
    modifier: Modifier = Modifier,
) {
    PreviewComposable {
        ShipmentHistoryScreenContent(
            state = ShipmentHistoryState(),
            onAction = {},
            modifier = modifier,
        )
    }
}