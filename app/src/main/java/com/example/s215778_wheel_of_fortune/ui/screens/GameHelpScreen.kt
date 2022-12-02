package com.example.s215778_wheel_of_fortune.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.s215778_wheel_of_fortune.R
import com.example.s215778_wheel_of_fortune.ui.theme.halantBold
import com.example.s215778_wheel_of_fortune.ui.theme.halantRegular

@Composable
fun GameHelpScreen(modifier: Modifier = Modifier){
    Scaffold(
        backgroundColor = colorResource(id = R.color.app_background),
        content = {padding ->
            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .background(colorResource(id = R.color.app_background))
                    .padding(padding),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp)){
                val stringArr = stringArrayResource(id = R.array.game_rules_array)
                Text(
                    text = stringArr[0],
                    fontFamily = halantBold,
                    fontSize = 36.sp,
                    color = colorResource(id = R.color.app_cream)
                )
                stringArr.forEach {
                    if (it != stringArr[0]) {
                        Text(text = it,
                            color = colorResource(id = R.color.app_cream),
                            fontFamily = halantRegular,
                            fontSize = 16.sp,
                            modifier = Modifier.width(350.dp))
                    }
                }
            }
        }
    )
}

@Preview
@Composable
fun GameHelpScreenPreview(){
    GameHelpScreen()
}