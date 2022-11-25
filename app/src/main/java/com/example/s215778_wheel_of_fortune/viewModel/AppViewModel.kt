package com.example.s215778_wheel_of_fortune.viewModel

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import com.example.s215778_wheel_of_fortune.model.SpinWheelData
import com.example.s215778_wheel_of_fortune.model.User

class AppViewModel : ViewModel(){
    val user = User()

    private val spin = SpinWheelData()
    private var _spinResult: String = ""

    val spinResult: String
        get() = _spinResult

    fun spinTheWheel(){
        _spinResult = spin.points[(0..9).random()]
    }


}