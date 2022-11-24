package com.example.s215778_wheel_of_fortune.ui.screens

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.s215778_wheel_of_fortune.R
import com.example.s215778_wheel_of_fortune.model.CharacterCard
import com.example.s215778_wheel_of_fortune.model.DisplayMatrix
import com.example.s215778_wheel_of_fortune.ui.components.TopBar
import com.example.s215778_wheel_of_fortune.ui.theme.halantBold
import com.example.s215778_wheel_of_fortune.ui.theme.halantRegular
import com.example.s215778_wheel_of_fortune.viewModel.AppViewModel
import kotlin.system.exitProcess

//Matrix
var m = DisplayMatrix()

@Composable
fun GameScreen(modifier: Modifier = Modifier){
    val vm by remember { mutableStateOf(AppViewModel()) }

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
                modifier = modifier
                    .fillMaxWidth()
                    .background(colorResource(id = R.color.app_background))
                    .padding(padding),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp))
            {
                Spacer(modifier = Modifier.height(0.dp))

                LazyVerticalGrid(
                    columns = GridCells.Fixed(14),
                    contentPadding = PaddingValues(10.dp),
                    content = {
                        for (i in 0..3){
                            items(m.matrix[i]) { item ->
                                Card (
                                    modifier = Modifier
                                        .padding(3.dp)
                                        .height(30.dp),
                                    backgroundColor = colorResource(id = item.cardColor),
                                    shape = RoundedCornerShape(0.dp)
                                ) {
                                    if(item.active)
                                        Text(text = item.char.toString(),
                                            textAlign = TextAlign.Center)
                                    else
                                        if (item.char == null) {
                                            Spacer(modifier = Modifier)
                                        }else{
                                            Text(text = item.char.toString())
                                        }
                                }
                            }
                        }
                    }
                )
                Spacer(modifier = Modifier.height(10.dp))

                Card(modifier = Modifier
                    .height(40.dp)
                    .width(201.dp),
                    backgroundColor = colorResource(id = R.color.app_red),
                    shape = RoundedCornerShape(0.dp)) {
                    Box(contentAlignment = Alignment.Center) {
                        Text(text = "Category",
                            textAlign = TextAlign.Center,
                            color = colorResource(id = R.color.app_cream),
                            fontFamily = halantBold,
                            fontSize = 22.sp
                        )
                    }
                }

                Spacer(modifier = Modifier.height(25.dp))

                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = "Lives",
                        textAlign = TextAlign.Center,
                        color = colorResource(id = R.color.app_cream),
                        fontFamily = halantRegular,
                        fontSize = 16.sp)
                    Row(modifier = Modifier.padding(8.dp)) {
                        for (i in 0..4) {
                            Spacer(modifier = Modifier.width(4.dp))
                            Canvas(modifier = Modifier.size(18.dp), onDraw = {
                                drawCircle(color = Color.White)
                            })
                            Spacer(modifier = Modifier.width(4.dp))
                        }
                    }

                    Spacer(modifier = Modifier.height(25.dp))
                    Text(text = "Score",
                        textAlign = TextAlign.Center,
                        color = colorResource(id = R.color.app_cream),
                        fontFamily = halantRegular,
                        fontSize = 16.sp)
                    Spacer(modifier = Modifier.height(7.dp))

                    Text(text = "1000",
                        textAlign = TextAlign.Center,
                        color = colorResource(id = R.color.app_cream),
                        fontFamily = halantRegular,
                        fontSize = 16.sp)
                }
                Spacer(modifier = Modifier.height(25.dp))

                var spinResult by remember { mutableStateOf("") }
                Text(
                    text = spinResult,
                    textAlign = TextAlign.Center,
                    color = colorResource(id = R.color.app_cream),
                    fontFamily = halantRegular,
                    fontSize = 16.sp
                )

                Box(contentAlignment = Alignment.Center, modifier = Modifier.height(180.dp)){
                    Image(imageVector = ImageVector.vectorResource(id = R.drawable.ellipse_1),
                        contentDescription = "eclipse", modifier = Modifier.align(Alignment.BottomCenter))
                    Button(onClick = {
                        vm.spinTheWheel()
                        spinResult = vm.spinResult
                                     },
                        shape = RoundedCornerShape(100),
                        modifier = Modifier
                            .size(88.dp)
                            .align(Alignment.TopCenter),
                        colors = ButtonDefaults.buttonColors(colorResource(id = R.color.app_red))

                    ) {
                        Text(text = "Spin the wheel", 
                            textAlign = TextAlign.Center,
                            color = colorResource(id = R.color.app_cream),

                        )
                    }
                    Row(modifier = Modifier.align(Alignment.CenterStart)) {
                        Spacer(modifier = Modifier.width(10.dp))
                        Button(onClick = { /*TODO*/ },
                            shape = RoundedCornerShape(100),
                            modifier = Modifier
                                .size(88.dp),
                            colors = ButtonDefaults.buttonColors(colorResource(id = R.color.app_red))

                        ) {
                            Text(text = "Game Rules",
                                textAlign = TextAlign.Center,
                                color = colorResource(id = R.color.app_cream),

                                )
                        }
                    }
                    Row(modifier = Modifier.align(Alignment.CenterEnd)) {
                        /*TODO: Create prompt to make sure user wants to exit*/
                        Button(onClick = { exitProcess(0) },
                            shape = RoundedCornerShape(100),
                            modifier = Modifier
                                .size(88.dp),
                            colors = ButtonDefaults.buttonColors(colorResource(id = R.color.app_red))

                        ) {
                            Text(text = "Exit Game",
                                textAlign = TextAlign.Center,
                                color = colorResource(id = R.color.app_cream),
                            )
                        }
                        Spacer(modifier = Modifier.width(10.dp))
                    }
                }
            }
        }
    )
}

@Preview
@Composable
fun GameScreenPreview(modifier: Modifier = Modifier){
    FillMatrix()
    GameScreen()
}

fun FillMatrix() {
    for (i in 0..3){
        for (j in 0..13){
            if (i == 1 && (j > 2 && j < 12) ){
                m.matrix[i][j] = CharacterCard('t', true, R.color.card_text_color)
            } else {
                m.matrix[i][j] = CharacterCard(null, false, R.color.app_red)
            }
        }
    }
}