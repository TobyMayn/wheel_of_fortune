package com.example.s215778_wheel_of_fortune.ui.screens

import android.annotation.SuppressLint
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


@Composable
fun GameScreen(
    modifier: Modifier = Modifier,
    onSpinClicked: () -> Unit,
    onExitClicked: () -> Unit,
    vm: AppViewModel) {

    Scaffold(
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

                var matrix: MutableState<DisplayMatrix> = remember {
                    mutableStateOf(vm.newGame())
                }

                LazyVerticalGrid(
                    columns = GridCells.Fixed(14),
                    contentPadding = PaddingValues(10.dp),
                    content = {
                        for (i in 0..3){
                            items(matrix.value.matrix[i]) { item ->
                                Card (
                                    modifier = Modifier
                                        .padding(3.dp)
                                        .height(30.dp),
                                    backgroundColor = colorResource(id = item.cardColor.value),
                                    shape = RoundedCornerShape(0.dp)
                                ) {
                                    if(item.active.value)
                                        Text(text = item.char.value.toString(),
                                            textAlign = TextAlign.Center)
                                }
                            }
                        }
                    }
                )

                Spacer(modifier = Modifier.height(10.dp))

                val category = vm.category

                Card(modifier = Modifier
                    .height(40.dp)
                    .width(201.dp),
                    backgroundColor = colorResource(id = R.color.app_red),
                    shape = RoundedCornerShape(0.dp)) {
                    Box(contentAlignment = Alignment.Center) {
                        if (category.length < 20){
                            Text(text = category,
                                textAlign = TextAlign.Center,
                                color = colorResource(id = R.color.app_cream),
                                fontFamily = halantBold,
                                fontSize = 22.sp
                            )
                        } else {
                            Text(text = category,
                                textAlign = TextAlign.Center,
                                color = colorResource(id = R.color.app_cream),
                                fontFamily = halantBold,
                                fontSize = 12.sp
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(25.dp))

                Column(horizontalAlignment = Alignment.CenterHorizontally) {

                    Text(text = stringResource(id = R.string.lives),
                        textAlign = TextAlign.Center,
                        color = colorResource(id = R.color.app_cream),
                        fontFamily = halantRegular,
                        fontSize = 16.sp)
                    Row(modifier = Modifier.padding(8.dp)) {
                        for (i in 0 until vm.lives) {
                            Spacer(modifier = Modifier.width(4.dp))
                            Canvas(modifier = Modifier.size(18.dp), onDraw = {
                                drawCircle(color = Color.White)
                            })
                            Spacer(modifier = Modifier.width(4.dp))
                        }
                    }

                    Spacer(modifier = Modifier.height(25.dp))
                    Text(text = stringResource(id = R.string.score),
                        textAlign = TextAlign.Center,
                        color = colorResource(id = R.color.app_cream),
                        fontFamily = halantRegular,
                        fontSize = 16.sp)
                    Spacer(modifier = Modifier.height(7.dp))

                    Text(text = vm.score.toString(),
                        textAlign = TextAlign.Center,
                        color = colorResource(id = R.color.app_cream),
                        fontFamily = halantRegular,
                        fontSize = 16.sp)
                }
                Spacer(modifier = Modifier.height(25.dp))


                Box(contentAlignment = Alignment.Center, modifier = Modifier.height(180.dp)){
                    Image(imageVector = ImageVector.vectorResource(id = R.drawable.ellipse_1),
                        contentDescription = "eclipse", modifier = Modifier.align(Alignment.BottomCenter))

                    var spinResult by remember { mutableStateOf("") }
                    val openDialog = remember {mutableStateOf(false)}
                    vm.GameLoop(openDialog = openDialog)
                    Text(
                        text = spinResult,
                        textAlign = TextAlign.Center,
                        color = colorResource(id = R.color.app_cream),
                        fontFamily = halantRegular,
                        fontSize = 16.sp
                    )

                    Row(modifier = Modifier.align(Alignment.CenterStart)) {
                        Spacer(modifier = Modifier.width(30.dp))

                        Button(onClick = { onExitClicked() },
                            shape = RoundedCornerShape(100),
                            modifier = Modifier
                                .size(88.dp),
                            colors = ButtonDefaults.buttonColors(colorResource(id = R.color.app_red))

                        ) {
                            Text(
                                text = stringResource(id = R.string.exit_game),
                                textAlign = TextAlign.Center,
                                color = colorResource(id = R.color.app_cream),
                            )
                        }
                    }
                    Row(modifier = Modifier.align(Alignment.CenterEnd)) {

                        Button(onClick = {
                            onSpinClicked()
                            spinResult = vm.spinResult
                            openDialog.value = true
                        },
                            shape = RoundedCornerShape(100),
                            modifier = Modifier
                                .size(88.dp),
                            colors = ButtonDefaults.buttonColors(colorResource(id = R.color.app_red))

                        ) {
                            Text(
                                text = stringResource(id = R.string.spin_wheel),
                                textAlign = TextAlign.Center,
                                color = colorResource(id = R.color.app_cream),
                            )
                        }
                        Spacer(modifier = Modifier.width(30.dp))
                    }
                }
            }
        }
    )
}

@Preview
@Composable
fun GameScreenPreview(modifier: Modifier = Modifier){
    val vm = AppViewModel()

    vm.selectWordAndCategory()
    vm.fillMatrix()

    GameScreen(
        onSpinClicked = {},
        onExitClicked = {},
        vm = vm
    )
}
