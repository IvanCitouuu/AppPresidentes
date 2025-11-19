package com.example.presidente

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.presidente.models.PresidentesData
import com.example.presidente.screens.PresidentDetailScreen
import com.example.presidente.screens.TimelineScreen
import com.example.presidente.ui.theme.PresidenteTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PresidenteTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = "timeline"
                ) {
                    composable("timeline") {
                        TimelineScreen(PresidentesData.presidents, navController)
                    }
                    composable("detail/{index}") { backStackEntry ->
                        val index = backStackEntry.arguments?.getString("index")?.toIntOrNull()
                        val president = index?.let { PresidentesData.presidents.getOrNull(it) }
                        if (president != null) {
                            PresidentDetailScreen(president, navController)
                        } else {
                            Text("Presidente no encontrado")
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TimelinePreview() {
    val fakeNavController = rememberNavController()
    PresidenteTheme {
        TimelineScreen(PresidentesData.presidents, navController = fakeNavController)
    }
}