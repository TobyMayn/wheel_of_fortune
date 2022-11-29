package com.example.s215778_wheel_of_fortune.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
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
                Text(
                    text = "Rules of the Game",
                    fontFamily = halantBold,
                    fontSize = 36.sp,
                    color = colorResource(id = R.color.app_cream)
                )
                Text(text = "1. The game is for one player.",
                    color = colorResource(id = R.color.app_cream),
                    fontFamily = halantRegular,
                    fontSize = 16.sp,
                    modifier = Modifier.width(350.dp))

                Text(text = "2. A word is randomly chosen from predefined categories and" +
                        " displayed along with the category.",
                    color = colorResource(id = R.color.app_cream),
                    fontFamily = halantRegular,
                    fontSize = 16.sp,
                    modifier = Modifier.width(350.dp))

                Text(text = "3. The word is displayed with the letters hidden.",
                    color = colorResource(id = R.color.app_cream),
                    fontFamily = halantRegular,
                    fontSize = 16.sp,
                    modifier = Modifier.width(350.dp))

                Text(text = "4. The player “spins the wheel”.",
                    color = colorResource(id = R.color.app_cream),
                    fontFamily = halantRegular,
                    fontSize = 16.sp,
                    modifier = Modifier.width(350.dp))

                Text(text = "5. The possible results of the “spinning the wheel” are: "+
                        "a number of points e.g 1000 or" +
                        " “bankrupt”.",
                    color = colorResource(id = R.color.app_cream),
                    fontFamily = halantRegular,
                    fontSize = 16.sp,
                    modifier = Modifier.width(350.dp))

                Text(text = "6. In the event of a value being shown, a letter (consonant or vowel) is chosen by the user" +
                        "(from a keyboard or otherwise). If the letter is present, the user’s points total is " +
                        "incremented by the value shown times the number of occurrences of the letter. The " +
                        "occurrences of the letter are revealed in the word.",
                    color = colorResource(id = R.color.app_cream),
                    fontFamily = halantRegular,
                    fontSize = 16.sp,
                    modifier = Modifier.width(350.dp))

                Text(text = "7. If the letter is not present the user loses a “life”.",
                    color = colorResource(id = R.color.app_cream),
                    fontFamily = halantRegular,
                    fontSize = 16.sp,
                    modifier = Modifier.width(350.dp))

                Text(text = "8. In the event of “bankrupt” being shown, the user loses all their points.",
                    color = colorResource(id = R.color.app_cream),
                    fontFamily = halantRegular,
                    fontSize = 16.sp,
                    modifier = Modifier.width(350.dp))

                Text(text = "9. The “wheel is spun” until the game is won or lost.",
                    color = colorResource(id = R.color.app_cream),
                    fontFamily = halantRegular,
                    fontSize = 16.sp,
                    modifier = Modifier.width(350.dp))

                Text(text = "10. The game is won when all the letters have been found and the user still has a life.",
                    color = colorResource(id = R.color.app_cream),
                    fontFamily = halantRegular,
                    fontSize = 16.sp,
                    modifier = Modifier.width(350.dp))

                Text(text = "11. A user starts with 5 “lives”.",
                    color = colorResource(id = R.color.app_cream),
                    fontFamily = halantRegular,
                    fontSize = 16.sp,
                    modifier = Modifier.width(350.dp))
            }
        }
    )
}

@Preview
@Composable
fun GameHelpScreenPreview(){
    GameHelpScreen()
}