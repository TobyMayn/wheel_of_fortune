package com.example.s215778_wheel_of_fortune.ui.components

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.s215778_wheel_of_fortune.R
import com.example.s215778_wheel_of_fortune.ui.theme.halantRegular

@Composable
fun StartScreenButton(
    text: String,
    onClick: () -> Unit
){
    Button(onClick = onClick,
        colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(id = R.color.app_red)),
        shape = RoundedCornerShape(50),
        modifier = Modifier
            .width(235.dp)
            .height(58.dp)
    ) {
        Text(
            text = text,
            textAlign = TextAlign.Center,
            color = colorResource(id = R.color.app_cream),
            fontFamily = halantRegular,
            fontSize = 32.sp
        )
    }
}