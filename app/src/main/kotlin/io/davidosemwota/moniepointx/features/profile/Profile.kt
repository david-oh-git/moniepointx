package io.davidosemwota.moniepointx.features.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import io.davidosemwota.moniepointx.core.designsystem.components.GeneralPreview
import io.davidosemwota.moniepointx.core.designsystem.components.MoniePointAppBar
import io.davidosemwota.moniepointx.core.designsystem.components.PreviewComposable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    onBackPressed: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: ProfileViewModel = hiltViewModel()
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

        ProfileScreenContent(
            state = state,
            onAction = viewModel::sendAction,
            modifier = Modifier.padding(innerPadding)
        )
    }


}

@Composable
fun ProfileScreenContent(
    state: ProfileState,
    onAction: (ProfileAction) -> Unit,
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
        Text(
            text = "Profile screen"
        )
    }

}

@GeneralPreview
@Composable
private fun Preview(
    modifier: Modifier = Modifier,
) {
    PreviewComposable {
        ProfileScreenContent(
            state = ProfileState(),
            onAction = {},
            modifier = modifier,
        )
    }
}