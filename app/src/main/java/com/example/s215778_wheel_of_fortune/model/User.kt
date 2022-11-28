package com.example.s215778_wheel_of_fortune.model

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

class User {
     var lives: MutableState<Int> = mutableStateOf(5)
     var score: MutableState<Int> = mutableStateOf(0)
}