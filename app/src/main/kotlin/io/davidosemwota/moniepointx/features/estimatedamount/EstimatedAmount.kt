package io.davidosemwota.moniepointx.features.estimatedamount

import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import io.davidosemwota.moniepointx.R
import io.davidosemwota.moniepointx.core.designsystem.MoniePointGreen
import io.davidosemwota.moniepointx.core.designsystem.components.CustomSpacer
import io.davidosemwota.moniepointx.core.designsystem.components.GeneralPreview
import io.davidosemwota.moniepointx.core.designsystem.components.MoniePointAppBar
import io.davidosemwota.moniepointx.core.designsystem.components.MoniePointButton
import io.davidosemwota.moniepointx.core.designsystem.components.PreviewComposable
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EstimatedAmountScreen(
    onBackPressed: () -> Unit,
    navigateToHome: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: EstimatedAmountViewModel = hiltViewModel()
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
                navigateToHome = navigateToHome,
                viewModel = viewModel,
            )
        }
    }

    // Start animation on first composition
    LaunchedEffect(Unit) {
        for (i in 1000..1400) {
            viewModel.onAmountChanged(i)
            delay(2L) // adjust speed here (lower = faster)
        }
    }

    Scaffold(
        modifier = modifier.fillMaxSize(),
        snackbarHost = { SnackbarHost(snackBarHostState) },
        topBar = {
            MoniePointAppBar(
                title = "",
                navigationIcon = {

                },
                actionIcon = {

                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color.Transparent
                ),
                windowInsets = WindowInsets(
                    top = 0.dp,
                    bottom = 0.dp
                )
            )
        },
        containerColor = MaterialTheme.colorScheme.surfaceContainerLow
    ) { innerPadding ->

        EstimatedAmountScreenContent(
            state = state,
            onAction = viewModel::sendAction,
            modifier = Modifier.padding(innerPadding)
        )
    }


}

@Composable
fun EstimatedAmountScreenContent(
    state: EstimatedAmountState,
    onAction: (EstimatedAmountAction) -> Unit,
    modifier: Modifier = Modifier,
) {
    val scrollState = rememberScrollState()

    val animatedValue by animateIntAsState(
        targetValue = state.amount,
        animationSpec = tween(durationMillis = 1500), // quick animation
        label = "Animated Counter"
    )

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(horizontal = 16.dp)
    ) {

        Image(
            painter = painterResource(R.drawable.movemate),
            contentDescription = "",
        )

        CustomSpacer(64.dp)

        Image(
            painter = painterResource(R.drawable.box),
            contentDescription = "",
            modifier = Modifier
                .fillMaxWidth(0.65f)
                .aspectRatio(1f)
        )

        CustomSpacer(32.dp)

        Text(
            text = "Total Estimated Amount",
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier
                .fillMaxWidth()
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
        ) {

            Text(
                text = "$ $animatedValue",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.displaySmall,
                color = MoniePointGreen,
                modifier = Modifier
                    .padding(
                        end = 8.dp,
                        bottom = 0.dp
                    ).alignByBaseline()
            )

            Text(
                text = state.currency,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleMedium,
                color = MoniePointGreen,
                modifier = Modifier
                    .alignByBaseline(),
            )

        }


        Text(
            text = stringResource(R.string.estimated_amount_msg),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
        )

        CustomSpacer(32.dp)

        MoniePointButton(
            onClick = {
                onAction(
                    EstimatedAmountAction.NavigateToHome
                )
            },
            text = "Back to home",
            modifier = Modifier
                .fillMaxWidth()
        )

    }

}

@GeneralPreview
@Composable
private fun Preview(
    modifier: Modifier = Modifier,
) {
    PreviewComposable {
        EstimatedAmountScreenContent(
            state = EstimatedAmountState(),
            onAction = {},
            modifier = modifier,
        )
    }
}