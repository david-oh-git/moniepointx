package io.davidosemwota.moniepointx.features.calculate

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import io.davidosemwota.moniepointx.R
import io.davidosemwota.moniepointx.core.designsystem.components.CalculateDropDown
import io.davidosemwota.moniepointx.core.designsystem.components.CalculateTextField
import io.davidosemwota.moniepointx.core.designsystem.components.GeneralPreview
import io.davidosemwota.moniepointx.core.designsystem.components.MoniePointAppBar
import io.davidosemwota.moniepointx.core.designsystem.components.MoniePointXCard
import io.davidosemwota.moniepointx.core.designsystem.components.PreviewComposable
import io.davidosemwota.moniepointx.core.designsystem.components.Spacer16

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalculateScreen(
    onBackPressed: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: CalculateViewModel = hiltViewModel()
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
            MoniePointAppBar(
                title = "Calculate",
                navigationIcon = {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                        contentDescription = "Navigate back icon",
                        tint = MaterialTheme.colorScheme.onPrimary,
                        modifier = Modifier
                            .size(40.dp)
                            .clickable {
                                viewModel.sendAction(CalculateAction.NavigateBack)
                            }
                    )
                },
                actionIcon = {

                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                ),
                windowInsets = WindowInsets(
                    top = 0.dp,
                    bottom = 0.dp
                )
            )
        },
        containerColor = MaterialTheme.colorScheme.surfaceContainerLow,
    ) { innerPadding ->

        CalculateScreenContent(
            state = state,
            onAction = viewModel::sendAction,
            modifier = Modifier.padding(innerPadding)
        )
    }


}

@Composable
fun CalculateScreenContent(
    state: CalculateState,
    onAction: (CalculateAction) -> Unit,
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
            text = "Destination",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.ExtraBold,
            modifier = Modifier
                .fillMaxWidth(),
        )

        Spacer16()

        MoniePointXCard(
            elevation = 0.dp,
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
            ) {
                CalculateTextField(
                    value = state.senderLocation,
                    hint = "Sender location",
                    onValueChange = {
                        onAction(CalculateAction.SenderLocationChanged(it))
                    },
                    iconId = R.drawable.unarchive,
                    modifier = Modifier
                        .fillMaxWidth(),
                )

                Spacer16()

                CalculateTextField(
                    value = state.receiverLocation,
                    hint = "Receiver location",
                    onValueChange = {
                        onAction(CalculateAction.ReceiverLocationChanged(it))
                    },
                    iconId = R.drawable.archive,
                    modifier = Modifier
                        .fillMaxWidth(),
                )

                Spacer16()

                CalculateTextField(
                    value = state.weight,
                    hint = "Approx weight",
                    onValueChange = {
                        onAction(CalculateAction.WeightChanged(it))
                    },
                    iconId = R.drawable.scale,
                    iconSIze = 28.dp,
                    keyboardType = KeyboardType.Number,
                    modifier = Modifier
                        .fillMaxWidth(),
                )


            }
        }

        Spacer16()

        Text(
            text = "Packaging",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.ExtraBold,
            modifier = Modifier
                .fillMaxWidth(),
        )
        Text(
            text = "What are you sending ?",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.ExtraLight,
            color = MaterialTheme.colorScheme.outline,
            modifier = Modifier
                .fillMaxWidth(),
        )

        Spacer16()

        CalculateDropDown(
            value = state.selectedPackageOption,
            options = state.packageOptions,
            hint = "Select packaging",
            iconId = R.drawable.box,
            onValueChange = {
                onAction(CalculateAction.PackageOptionSelected(it))
            },
        )
    }

}

@GeneralPreview
@Composable
private fun Preview(
    modifier: Modifier = Modifier,
) {
    PreviewComposable {
        CalculateScreenContent(
            state = CalculateState(),
            onAction = {},
            modifier = modifier,
        )
    }
}