package com.example.s215778_wheel_of_fortune.viewModel

import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import com.example.s215778_wheel_of_fortune.R
import com.example.s215778_wheel_of_fortune.model.*

class AppViewModel : ViewModel(){
    private lateinit var _category: String
    private lateinit var _word : String

    private var _matrix = DisplayMatrix()
    private val _user = User()
    private val spin = SpinWheelData()

    private var guesses = ""
    private var _spinResult: String = ""
    private var _categories = listOf(
        Categories.fictionalCharacters,
        Categories.occupation,
        Categories.onTheMap,
        Categories.person,
        Categories.thing
    )
    var gameRunning = true

    val spinResult: String
        get() = _spinResult

    // User score
    val score: Int
        get() = _user.score.value

    // User lives
    val lives: Int
        get() = _user.lives.value

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
                        _matrix.matrix[i][j] = CharacterCard(_word[j-2], false, R.color.app_cream)
                    }
                } else if (i == 2 && (j > 1 && j < 12) && !(j+8 >= _word.length)) {
                    if(_word[j+8] == ' ') {
                        _matrix.matrix[i][j] = CharacterCard(_word[j+8], false, R.color.app_red)
                    } else {
                        _matrix.matrix[i][j] = CharacterCard(_word[j+8], false, R.color.app_cream)
                    }
                }
                else {
                    _matrix.matrix[i][j] = CharacterCard(null, false, R.color.app_red)
                }
            }
        }
    }
    /*TODO: Maybe use built-in search function to search chars, maybe faster?*/
    // Helper function for fillMatrix
    private fun insertLettersTop(){
        for (i in 0..3){
            for (j in 0..13){
                // Insert first ten letters in top row
                if (i == 0 && (j > 1 && j < 12) && !(j-2 >= _word.length)){
                    if(_word[j-2] == ' ') {
                        _matrix.matrix[i][j] = CharacterCard(_word[j-2], false, R.color.app_red)
                    } else {
                        _matrix.matrix[i][j] = CharacterCard(_word[j-2], false, R.color.app_cream)
                    }
                }
                // Insert next ten letters in second row
                else if (i == 1 && (j > 1 && j < 12) && !(j+8 >= _word.length)) {
                    if(_word[j+8] == ' ') {
                        _matrix.matrix[i][j] = CharacterCard(_word[j+8], false, R.color.app_red)
                    } else {
                        _matrix.matrix[i][j] = CharacterCard(_word[j+8], false, R.color.app_cream)
                    }
                }
                // Insert next ten letters in third row
                else if (i == 2 && (j > 1 && j < 12) && !(j+18 >= _word.length)) {
                    if(_word[j+18] == ' ') {
                        _matrix.matrix[i][j] = CharacterCard(_word[j+18], false, R.color.app_red)
                    } else {
                        _matrix.matrix[i][j] = CharacterCard(_word[j+18], false, R.color.app_cream)
                    }
                }
                // Insert last ten letters in last row
                else if (i == 3 && (j > 1 && j < 12) && !(j+28 >= _word.length)){
                    if(_word[j+28] == ' ') {
                        _matrix.matrix[i][j] = CharacterCard(_word[j+28], false, R.color.app_red)
                    } else {
                        _matrix.matrix[i][j] = CharacterCard(_word[j+28], false, R.color.app_cream)
                    }
                }
                else {
                    _matrix.matrix[i][j] = CharacterCard(null, false, R.color.app_red)
                }
            }
        }
    }

    @Composable
    fun makeGuess(openDialog: MutableState<Boolean>) {
        var guess by remember { mutableStateOf(TextFieldValue("")) }
        if(openDialog.value){
            AlertDialog(
                onDismissRequest ={},
                confirmButton = {
                    Button(onClick = {
                        if(guess.text != "") {
                            openDialog.value = false
                            checkGuessAndUpdatePlayer()
                        }
                    }) {
                        Text(text = "Confirm guess")
                    }
                    guesses = guess.text},
                title = {
                    Text(text = "Make a guess")
                },
                text = {
                    TextField(
                        value = guess,
                        onValueChange = {guess = it},
                        label = { Text(text = "Guess")},
                    )
                }
            )
        }
    }

    fun checkGuessAndUpdatePlayer(){
        var count = 0
        if(guesses.length == 1){
            for (i in 0..3){
                for (j in 0..13) {
                    if((guesses[0].lowercaseChar() == _matrix.matrix[i][j].char?.lowercaseChar()) && (!_matrix.matrix[i][j].active.value)){
                        count++
                        updateCards(_matrix.matrix[i][j])
                    }
                }
            }
        } else if (guesses.length > 1){
            if (guesses == _word){
                gameRunning = false
            } else {
                updatePlayer(_spinResult.toInt(), count)
            }
        } else if(_spinResult == "Bankrupt"){
            resetScore()
        } else {
            updatePlayer(_spinResult.toInt(), count)
        }
    }

    private fun updateCards(card: CharacterCard){
         card.active.value = true
    }

    private fun updatePlayer(score: Int, count: Int) {
        var calcAmount = 0
        if(count != 0) {
            calcAmount = score*count
            updateScore(calcAmount)
        } else{
            updateLives()
        }
    }

    private fun updateScore(amount: Int){
        if (amount != 0) {
            _user.score.value += amount
        }
    }

     private fun updateLives() {
        _user.lives.value -= 1
    }

    fun spinTheWheel(){
        _spinResult = spin.points[(0..9).random()]
    }

    private fun resetScore() {
        _user.score.value = 0
    }

}