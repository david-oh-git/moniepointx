package io.davidosemwota.moniepointx.features.receiptsearch

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
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
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
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import io.davidosemwota.moniepointx.R
import io.davidosemwota.moniepointx.core.designsystem.components.CircularImageTint
import io.davidosemwota.moniepointx.core.designsystem.components.Dot
import io.davidosemwota.moniepointx.core.designsystem.components.GeneralPreview
import io.davidosemwota.moniepointx.core.designsystem.components.MoniePointHorizontalDivider
import io.davidosemwota.moniepointx.core.designsystem.components.MoniePointXCard
import io.davidosemwota.moniepointx.core.designsystem.components.PreviewComposable
import io.davidosemwota.moniepointx.core.designsystem.components.SearchTextField
import io.davidosemwota.moniepointx.core.designsystem.components.Spacer16

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReceiptSearchScreen(
    onBackPressed: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: ReceiptSearchViewModel = hiltViewModel()
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
                onAction = viewModel::sendAction,
                searchQuery = state.searchQuery
            )
        },
        containerColor = MaterialTheme.colorScheme.surfaceContainerLow
    ) { innerPadding ->

        ReceiptSearchScreenContent(
            state = state,
            onAction = viewModel::sendAction,
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
        )
    }

}

@Composable
fun ReceiptSearchScreenContent(
    state: ReceiptSearchState,
    onAction: (ReceiptSearchAction) -> Unit,
    modifier: Modifier = Modifier,
) {
    val scrollState = rememberScrollState()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
    ) {
        Receipts(
            receipts = state.filteredReceipts,
        )
    }

}


@Composable
internal fun Header(
    searchQuery: String,
    onAction: (ReceiptSearchAction) -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.primary),
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
                .clickable{
                    onAction(
                        ReceiptSearchAction.NavigateBack
                    )
                }
        )

        SearchTextField(
            value = searchQuery,
            hint = "Enter the receipt number...",
            onValueChange = {
                onAction(
                    ReceiptSearchAction.SearchQueryChanged(it)
                )
            },
            onClick = {

            },
            modifier = Modifier
                .weight(1f)
                .padding(vertical = 16.dp)
        )

        Spacer16()

    }
}


@Composable
internal fun Receipts(
    receipts: List<Receipt>,
    modifier: Modifier = Modifier,
) {
    MoniePointXCard(
        elevation = 0.dp,
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
        ) {
            receipts.forEachIndexed { index,receipt ->
                ReceiptItem(
                    receipt = receipt,
                    modifier = Modifier
                        .fillMaxWidth()
                )
                if (index != receipts.lastIndex) {
                    MoniePointHorizontalDivider()
                }
            }

        }
    }
}


@Composable
internal fun ReceiptItem(
    receipt: Receipt,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        CircularImageTint(
            resId = R.drawable.package_shipment,
            tint = MaterialTheme.colorScheme.primary,
        )

        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .weight(1f)
                .padding(start = 8.dp),
        ) {
            Text(
                text = receipt.itemName,
                style = MaterialTheme.typography.titleMedium,
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = "#${receipt.receiptNumber} ",
                    style = MaterialTheme.typography.bodySmall,
                )
                Dot(
                    color = Color.Gray,
                    size = 4.dp,
                )
                Text(
                    text = " ${receipt.route}",
                    style = MaterialTheme.typography.bodySmall,
                )
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
        ReceiptSearchScreenContent(
            state = ReceiptSearchState(),
            onAction = {},
            modifier = modifier,
        )
    }
}