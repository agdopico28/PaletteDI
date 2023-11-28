package com.example.palettedi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.palettedi.ui.theme.PaletteDITheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PaletteDITheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = "Sitios") {
                        composable("Sitios") { Sitios(navController) }
                        composable(
                            route = "Ampliacion/{textoContacto}/{imagenContacto}",
                            arguments = listOf(
                                navArgument("textoContacto") { type = NavType.StringType },
                                navArgument("imagenContacto") { type = NavType.IntType }
                            )
                        ) { backStackEntry ->
                            Ammpliacion(
                                backStackEntry.arguments?.getString("textoContacto") ?: "",
                                backStackEntry.arguments?.getInt("imagenContacto") ?: 0,
                                navController
                            )
                        }
                    }
                }
            }
        }
    }
}
