package com.example.s215778_wheel_of_fortune

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.s215778_wheel_of_fortune.ui.*
import com.example.s215778_wheel_of_fortune.ui.components.TopBar
import com.example.s215778_wheel_of_fortune.ui.screens.*
import com.example.s215778_wheel_of_fortune.viewModel.AppViewModel
import kotlin.system.exitProcess

enum class WheelOfFortuneScreen {
    Start,
    GameHelp,
    Game
}

@Composable
fun fortuneAppBar(modifier: Modifier = Modifier){
    TopBar(
        titleText = "Wheel Of Fortune",
        color = R.color.app_background,
        padding = 30.dp)
}

@Composable
fun WheelOfFortuneApp(
    modifier: Modifier = Modifier,
    viewModel: AppViewModel = viewModel()
) {
    val navController = rememberNavController()

    //val backStackEntry by navController.currentBackStackEntryAsState()

    //val currentScreen = backStackEntry?.destination?.route?: WheelOfFortuneScreen.Start.name

    Scaffold(
        topBar = {
            fortuneAppBar()
        }
    ) { innerPadding ->
        //val uiState by viewModel.uiState
        NavHost(
            navController = navController,
            startDestination = WheelOfFortuneScreen.Start.name,
            modifier = modifier.padding(innerPadding)
        ){
            composable(route = WheelOfFortuneScreen.Start.name){
                 StartScreen(
                     onStartClicked = { navController.navigate(WheelOfFortuneScreen.Game.name) },
                     onGameRulesClicked = {navController.navigate(WheelOfFortuneScreen.GameHelp.name) },
                     onExitClicked = { exitProcess(0) }
                 )
            }
            composable(route = WheelOfFortuneScreen.Game.name){
                GameScreen(
                    onSpinClicked = { viewModel.spinTheWheel() },
                    onGameRulesClicked = { navController.navigate(WheelOfFortuneScreen.GameHelp.name)},
                    onExitClicked = { exitProcess(0) }
                )
            }
            composable(route = WheelOfFortuneScreen.GameHelp.name){
                GameHelpScreen()
            }
        }
    }
}

@Preview
@Composable
fun FortuneAppPreview() {
    WheelOfFortuneApp()
}