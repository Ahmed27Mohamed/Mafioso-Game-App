package com.a2004256_ahmedmohamed.mafiosogame

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.a2004256_ahmedmohamed.mafiosogame.model.Player
import com.a2004256_ahmedmohamed.mafiosogame.ui.theme.MafiosoGameTheme
import com.a2004256_ahmedmohamed.mafiosogame.ux_ui.GameFlowScreen
import com.a2004256_ahmedmohamed.mafiosogame.ux_ui.MainScreen
import com.a2004256_ahmedmohamed.mafiosogame.ux_ui.SplashScreen
import com.a2004256_ahmedmohamed.mafiosogame.viewmodel.GameViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MafiosoGameTheme {
                AppNavigation()
            }
        }
    }
}

@Composable
fun AppNavigation(viewModel: GameViewModel = viewModel()) {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.Splash.route) {

        composable(Screen.Splash.route) {
            SplashScreen(navController)
        }

        composable(Screen.Setup.route) {
            MainScreen(navController, viewModel)
        }

        composable(Screen.Game.route) {
            GameFlowScreen(viewModel) {
                navController.popBackStack()
            }
        }
    }
}