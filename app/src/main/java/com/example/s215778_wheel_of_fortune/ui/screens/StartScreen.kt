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
import com.example.s215778_wheel_of_fortune.ui.theme.halantBold
import com.example.s215778_wheel_of_fortune.ui.theme.halantRegular


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StartScreen (modifier: Modifier = Modifier) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Wheel Of Fortune",
                        textAlign = TextAlign.Center,
                        color = colorResource(id = R.color.app_cream),
                        fontFamily = halantBold,
                        fontSize = 32.sp
                    )
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(colorResource(id = R.color.app_background)),
                modifier = Modifier.padding(top = 30.dp)
            )},
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
                Spacer(modifier = Modifier.height(262.dp))
                Button(onClick = { /*TODO*/ },
                    colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(id = R.color.app_red)),
                    shape = RoundedCornerShape(50),
                    modifier = Modifier
                        .width(235.dp)
                        .height(58.dp)
                ) {
                    Text(
                        text = "Start Game",
                        textAlign = TextAlign.Center,
                        color = colorResource(id = R.color.app_cream),
                        fontFamily = halantRegular,
                        fontSize = 32.sp
                    )
                }
                Spacer(modifier = Modifier.height(57.dp))
                Button(onClick = { /*TODO*/ },
                    colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(id = R.color.app_red)),
                    shape = RoundedCornerShape(50),
                    modifier = Modifier
                        .width(235.dp)
                        .height(58.dp)
                ) {
                    Text(
                        text = "Game Rules",
                        textAlign = TextAlign.Center,
                        color = colorResource(id = R.color.app_cream),
                        fontFamily = halantRegular,
                        fontSize = 32.sp
                    )
                }
                Spacer(modifier = Modifier.height(57.dp))
                Button(onClick = { /*TODO*/ },
                    colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(id = R.color.app_red)),
                    shape = RoundedCornerShape(50),
                    modifier = Modifier
                        .width(235.dp)
                        .height(58.dp)
                ) {
                    Text(
                        text = "Exit Game",
                        textAlign = TextAlign.Center,
                        color = colorResource(id = R.color.app_cream),
                        fontFamily = halantRegular,
                        fontSize = 32.sp
                    )
                }
            }
        }
    )

}



@Preview
@Composable
fun StartScreenPreview () {
    StartScreen()
}