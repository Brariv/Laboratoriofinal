package com.uvg23088.laboratoriofinal

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.uvg23088.laboratoriofinal.Presentation.CurrencyNavHost
import com.uvg23088.laboratoriofinal.ui.theme.LaboratorioFinalTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        enableEdgeToEdge()
        setContent {
            LaboratorioFinalTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    Scaffold { paddingValues ->
                        CurrencyNavHost(modifier = Modifier.padding(paddingValues))
                    }
                }
            }
        }
    }
}