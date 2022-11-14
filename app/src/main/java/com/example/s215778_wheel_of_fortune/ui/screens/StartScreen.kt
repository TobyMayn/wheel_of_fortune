package com.example.s215778_wheel_of_fortune.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Modifier
import com.example.s215778_wheel_of_fortune.R
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import com.example.s215778_wheel_of_fortune.ui.components.StartScreenButton
import com.example.s215778_wheel_of_fortune.ui.components.TopBar
import com.example.s215778_wheel_of_fortune.ui.theme.halantBold
import com.example.s215778_wheel_of_fortune.ui.theme.halantRegular


@Composable
fun StartScreen (modifier: Modifier = Modifier) {
    Scaffold(
        topBar = {
            TopBar(
                titleText = "Wheel Of Fortune",
                color = R.color.app_background,
                padding = 30.dp )
        },
        backgroundColor = colorResource(id = R.color.app_background),
        content = {padding ->
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(colorResource(id = R.color.app_background))
                    .padding(padding),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ){
                Spacer(modifier = Modifier.height(260.dp))

                StartScreenButton(
                    text = "Start Game1",
                    onClick = {/*TODO*/})

                Spacer(modifier = Modifier.height(50.dp))

                StartScreenButton(
                    text = "Game Rules",
                    onClick = {/*TODO*/})

                Spacer(modifier = Modifier.height(50.dp))

                StartScreenButton(
                    text = "Exit Game",
                    onClick = {/*TODO*/})
            }
        }
    )

}



@Preview
@Composable
fun StartScreenPreview () {
    StartScreen()
}