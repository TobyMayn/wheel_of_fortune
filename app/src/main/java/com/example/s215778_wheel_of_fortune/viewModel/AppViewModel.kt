package com.example.s215778_wheel_of_fortune.viewModel

import androidx.compose.animation.scaleOut
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import com.example.s215778_wheel_of_fortune.R
import com.example.s215778_wheel_of_fortune.model.*

class AppViewModel : ViewModel(){
    private lateinit var _category: String
    private lateinit var _word : String

    private var _matrix = DisplayMatrix()
    private val _user = User()
    private val spin = SpinWheelData()

    private var _spinResult: String = ""
    private var _categories = listOf(
        Categories.fictionalCharacters,
        Categories.occupation,
        Categories.onTheMap,
        Categories.person,
        Categories.thing
    )

    val spinResult: String
        get() = _spinResult

    // User score
    val score: Int
        get() = _user.score

    // User lives
    val lives: Int
        get() = _user.lives

    val matrix: DisplayMatrix
        get() = _matrix

    val category: String
        get() = _category
    fun selectWordAndCategory() {
        val random = (0..4).random()
        //Select Category
        _category = _categories[random][0]

        //Select Word
        _word = _categories[random][(1..9).random()]
    }

    fun fillMatrix() {
        when(_word.length) {
            in 0 ..20 -> {insertLettersMid()}
            in 21 until 40 -> {insertLettersTop()}
        }
    }
    // Helper function for fillMatrix
    private fun insertLettersMid(){
        for (i in 0..3){
            for (j in 0..13){
                if (i == 1 && (j > 1 && j < 12) && !(j-2 >= _word.length) ){
                    if(_word[j-2] == ' ') {
                        _matrix.matrix[i][j] = CharacterCard(_word[j-2], false, R.color.app_red)
                    } else {
                        _matrix.matrix[i][j] = CharacterCard(_word[j-2], false, R.color.card_text_color)
                    }
                } else if (i == 2 && (j > 1 && j < 12) && !(j+8 >= _word.length)) {
                    if(_word[j+8] == ' ') {
                        _matrix.matrix[i][j] = CharacterCard(_word[j+8], false, R.color.app_red)
                    } else {
                        _matrix.matrix[i][j] = CharacterCard(_word[j+8], false, R.color.card_text_color)
                    }
                }
                else {
                    _matrix.matrix[i][j] = CharacterCard(null, false, R.color.app_red)
                }
            }
        }
    }
    // Helper function for fillMatrix
    private fun insertLettersTop(){
        for (i in 0..3){
            for (j in 0..13){
                // Insert first ten letters in top row
                if (i == 0 && (j > 1 && j < 12) && !(j-2 >= _word.length)){
                    if(_word[j-2] == ' ') {
                        _matrix.matrix[i][j] = CharacterCard(_word[j-2], false, R.color.app_red)
                    } else {
                        _matrix.matrix[i][j] = CharacterCard(_word[j-2], false, R.color.card_text_color)
                    }
                }
                // Insert next ten letters in second row
                else if (i == 1 && (j > 1 && j < 12) && !(j+8 >= _word.length)) {
                    if(_word[j+8] == ' ') {
                        _matrix.matrix[i][j] = CharacterCard(_word[j+8], false, R.color.app_red)
                    } else {
                        _matrix.matrix[i][j] = CharacterCard(_word[j+8], false, R.color.card_text_color)
                    }
                }
                // Insert next ten letters in third row
                else if (i == 2 && (j > 1 && j < 12) && !(j+18 >= _word.length)) {
                    if(_word[j+18] == ' ') {
                        _matrix.matrix[i][j] = CharacterCard(_word[j+18], false, R.color.app_red)
                    } else {
                        _matrix.matrix[i][j] = CharacterCard(_word[j+18], false, R.color.card_text_color)
                    }
                }
                // Insert last ten letters in last row
                else if (i == 3 && (j > 1 && j < 12) && !(j+28 >= _word.length)){
                    if(_word[j+28] == ' ') {
                        _matrix.matrix[i][j] = CharacterCard(_word[j+28], false, R.color.app_red)
                    } else {
                        _matrix.matrix[i][j] = CharacterCard(_word[j+28], false, R.color.card_text_color)
                    }
                }
                else {
                    _matrix.matrix[i][j] = CharacterCard(null, false, R.color.app_red)
                }
            }
        }
    }


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