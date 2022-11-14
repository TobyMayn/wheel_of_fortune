package com.example.s215778_wheel_of_fortune.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.s215778_wheel_of_fortune.R
import com.example.s215778_wheel_of_fortune.ui.components.TopBar


@Composable
fun GameScreen(modifier: Modifier = Modifier){
    Scaffold(
        topBar = {
            TopBar(
                titleText = "Wheel Of Fortune",
                color = R.color.app_background,
                padding = 30.dp)
            },
        backgroundColor = colorResource(id = R.color.app_background),
        content = {padding ->
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(colorResource(id = R.color.app_background))
                    .padding(padding),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp))
            {

            }
        }
    )
}

@Preview
@Composable
fun GameScreenPreview(modifier: Modifier = Modifier){
    GameScreen()
}