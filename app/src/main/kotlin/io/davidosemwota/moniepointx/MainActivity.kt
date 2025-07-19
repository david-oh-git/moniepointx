package io.davidosemwota.moniepointx

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.rememberNavBackStack
import dagger.hilt.android.AndroidEntryPoint
import io.davidosemwota.moniepointx.core.designsystem.MoniepointXTheme
import io.davidosemwota.moniepointx.navigation.BottomNavigationScreen

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MoniepointXTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    MoniePointXApp(
                        backStack = rememberNavBackStack(
                            BottomNavigationScreen.Home()
                        ),
                        modifier = Modifier.fillMaxSize(),
                    )
                }
            }
        }
    }
}
