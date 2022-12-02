package com.example.s215778_wheel_of_fortune.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.sp
import com.example.s215778_wheel_of_fortune.R
import com.example.s215778_wheel_of_fortune.ui.theme.halantBold

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    titleText: String,
    color: Int,
    padding: Dp
) {
    CenterAlignedTopAppBar (
        title = {
            Text(
            text = titleText,
            textAlign = TextAlign.Center,
            color = colorResource(id = R.color.app_cream),
            fontFamily = halantBold,
            fontSize = 32.sp
        )
        },
        colors = TopAppBarDefaults.topAppBarColors( colorResource(id = color)),
        modifier = Modifier
            .background(color = colorResource(id = color))
            .padding(top = padding),

    )
}