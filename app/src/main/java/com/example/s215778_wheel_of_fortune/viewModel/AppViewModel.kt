package com.example.s215778_wheel_of_fortune.viewModel

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import com.example.s215778_wheel_of_fortune.model.SpinWheelData
import com.example.s215778_wheel_of_fortune.model.User

class AppViewModel : ViewModel(){
    private val _user = User()
    private val spin = SpinWheelData()
    private var _spinResult: String = ""

    val spinResult: String
        get() = _spinResult

    // User score
    val score: Int
        get() = _user.score

    // User lives
    val lives: Int
        get() = _user.lives

    fun updateScore(amount: Int){
        _user.score += amount
    }

    fun updateLives() {
        _user.lives -= 1
    }

    fun spinTheWheel(){
        _spinResult = spin.points[(0..9).random()]
    }


}