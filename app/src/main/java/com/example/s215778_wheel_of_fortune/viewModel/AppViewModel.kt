package com.example.s215778_wheel_of_fortune.viewModel

import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.s215778_wheel_of_fortune.R
import com.example.s215778_wheel_of_fortune.model.*
import kotlin.random.Random
import kotlin.system.exitProcess

class AppViewModel : ViewModel(){

    /*
    * All variables and values used
    *
     */

    private var _category: String = ""
    private var _word : String = ""

    private var _matrix = DisplayMatrix()
    private val _user = User()
    private val spin = SpinWheelData()

    private var guesses = ""
    private var _spinResult: String = ""
    private val _categories = listOf(
        Categories.fictionalCharacters,
        Categories.occupation,
        Categories.onTheMap,
        Categories.person,
        Categories.thing
    )
    private var gameRunning = true

    val spinResult: String
        get() = _spinResult

    // User score
    val score: Int
        get() = _user.score.value

    // User lives
    val lives: Int
        get() = _user.lives.value

    val category: String
        get() = _category

    // Selects a random word and category from the categories table
    fun selectWordAndCategory() {
        val random = (0..4).random()
        //Select Category
        _category = _categories[random][0]

        //Select Word
        _word = _categories[random][(1..9).random()]
    }

    // Fills matrix depending on length of word to be guessed
    // if word is longer than 20 characters, it is inserted from the top
    // if word is <= 20 characters it is inserted in the middle of the matrix
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
                        _matrix.matrix[i][j] = CharacterCard(_word[j-2], true, R.color.app_red)
                    } else {
                        _matrix.matrix[i][j] = CharacterCard(_word[j-2], false, R.color.app_cream)
                    }
                } else if (i == 2 && (j > 1 && j < 12) && !(j+8 >= _word.length)) {
                    if(_word[j+8] == ' ') {
                        _matrix.matrix[i][j] = CharacterCard(_word[j+8], true, R.color.app_red)
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

    // Helper function for fillMatrix
    private fun insertLettersTop(){
        for (i in 0..3){
            for (j in 0..13){
                // Insert first ten letters in top row
                if (i == 0 && (j > 1 && j < 12) && !(j-2 >= _word.length)){
                    if(_word[j-2] == ' ') {
                        _matrix.matrix[i][j] = CharacterCard(_word[j-2], true, R.color.app_red)
                    } else {
                        _matrix.matrix[i][j] = CharacterCard(_word[j-2], false, R.color.app_cream)
                    }
                }
                // Insert next ten letters in second row
                else if (i == 1 && (j > 1 && j < 12) && !(j+8 >= _word.length)) {
                    if(_word[j+8] == ' ') {
                        _matrix.matrix[i][j] = CharacterCard(_word[j+8], true, R.color.app_red)
                    } else {
                        _matrix.matrix[i][j] = CharacterCard(_word[j+8], false, R.color.app_cream)
                    }
                }
                // Insert next ten letters in third row
                else if (i == 2 && (j > 1 && j < 12) && !(j+18 >= _word.length)) {
                    if(_word[j+18] == ' ') {
                        _matrix.matrix[i][j] = CharacterCard(_word[j+18], true, R.color.app_red)
                    } else {
                        _matrix.matrix[i][j] = CharacterCard(_word[j+18], false, R.color.app_cream)
                    }
                }
                // Insert last ten letters in last row
                else if (i == 3 && (j > 1 && j < 12) && !(j+28 >= _word.length)){
                    if(_word[j+28] == ' ') {
                        _matrix.matrix[i][j] = CharacterCard(_word[j+28], true, R.color.app_red)
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

    // Prompts the user make a guess
    // Only accepts one character at a time. Meaning a guess can't be
    // empty or more than 1 character in length
    @Composable
    fun makeGuess(openDialog: MutableState<Boolean>) {
        if(_spinResult == "Bankrupt"){
            resetScore()
        } else {
            var guess by remember { mutableStateOf(TextFieldValue("")) }
            if(openDialog.value){
                AlertDialog(
                    onDismissRequest ={},
                    confirmButton = {
                        Button(onClick = {
                            if(guess.text != "" && guess.text.length < 2) {
                                openDialog.value = false
                                checkGuessAndUpdatePlayer()
                                checkGameWonOrLost()
                            }
                        },
                        colors = ButtonDefaults.buttonColors(colorResource(id = R.color.app_red))) {
                            Text(text = stringResource(id = R.string.confirm_guess))
                        }
                        guesses = guess.text},
                    title = {
                        Text(text = stringResource(id = R.string.make_guess_title))
                    },
                    text = {
                        TextField(
                            value = guess,
                            onValueChange = {guess = it},
                            label = { Text(text = "Guess", color = colorResource(id = R.color.app_background))},
                            colors = TextFieldDefaults.textFieldColors(
                                focusedIndicatorColor = colorResource(id = R.color.app_red),
                                cursorColor = colorResource(id = R.color.app_red)
                            )
                        )
                    },
                    backgroundColor = colorResource(id = R.color.app_cream)
                )
            }
        }
    }

    // Checks if a player has made a correct guess
    // if so how many characters and has the character already been guessed before
    private fun checkGuessAndUpdatePlayer(){
        var count = 0
        for (i in 0..3){
            for (j in 0..13) {
                val guessedChar = _matrix.matrix[i][j].char.value?.lowercaseChar()
                val isGuessedChar = _matrix.matrix[i][j].active.value

                if((guesses[0].lowercaseChar() == guessedChar) && (!isGuessedChar)){
                    count++
                    updateCards(_matrix.matrix[i][j])
                }
            }
        }
        updatePlayer(_spinResult.toInt(), count)
    }

    // Sets active value of characterCard to true if character is correctly guessed
    private fun updateCards(card: CharacterCard){
         card.active.value = true
    }

    // Updates the player, either by updating the score or lives
    // If multiple chars has been correctly guessed the total value is calculated
    // and added to the score
    private fun updatePlayer(score: Int, count: Int) {
        var calcAmount = 0
        if(count != 0) {
            calcAmount = score*count
            updateScore(calcAmount)
        } else{
            if(gameRunning){
                updateLives()
            }
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

    // Spins the wheel, by picking a random number from the spin list
    fun spinTheWheel(){
        _spinResult = spin.points[(0..9).random()]
    }

    private fun resetScore() {
        _user.score.value = 0
    }

    private fun resetUser(){
        _user.lives.value = 5
        resetScore()
    }

    // Checks wether a player has won or lost the game
    private fun checkGameWonOrLost(){
        if(_user.lives.value == 0){
            gameRunning = false
        } else {
            var count = 0
            for (i in 0..3){
                for (j in 0..13) {
                    if(_matrix.matrix[i][j].active.value){
                        count++
                    }
                }
            }
            if(count == _word.length) {
                gameRunning = false
            }
        }

    }


    // If the game is over
    // asks the player if a new game should be started or the game should be closed
    @Composable
    private fun gameWonOrLost(){
        if(!gameRunning){
            AlertDialog(
                onDismissRequest ={},
                confirmButton = {
                    Button(onClick = {  newGame() },
                    colors = ButtonDefaults.buttonColors(colorResource(id = R.color.app_red))
                    ) {
                        Text(text = stringResource(id = R.string.start_new_game))
                    }
                },
                dismissButton = {
                    Button(onClick = { exitProcess(0) },
                    colors = ButtonDefaults.buttonColors(colorResource(id = R.color.app_red))
                    )
                    {
                        Text(text = stringResource(id = R.string.exit_game))
                    }
                },
                title = {
                    if(_user.lives.value == 0 ){
                        Text(text = stringResource(id = R.string.game_lost_title))
                    } else {
                        Text(text = stringResource(id = R.string.game_won_title))
                    }
                },
                text = {
                    if(_user.lives.value == 0 ){
                        Text(text = stringResource(id = R.string.game_lost_text))
                    } else {
                        Text(text = stringResource(id = R.string.game_won_text))
                    }
                },
                backgroundColor = colorResource(id = R.color.app_cream)
            )
        }
    }

    // Function that runs all the checks, to keep the game running
    @Composable
    fun GameLoop (openDialog: MutableState<Boolean>){
        if (openDialog.value) {
            makeGuess(openDialog = openDialog)
        }

        gameWonOrLost()
    }
    // Helper function for resetGame()
    // Sets all relevant values to their initial value
    private fun resetValues(){
        _word = ""
        _category = ""
        _spinResult = ""
        guesses = ""

    }

    // Helper function for newGame()
    private fun resetGame(){
        resetUser()
        resetValues()

        selectWordAndCategory()
        fillMatrix()
    }

    // Creates new game, by resetting all used values to their initial state
    // and returns a matrix used in the GameScreen
    fun newGame(): DisplayMatrix {
        resetGame()
        gameRunning = true
        return _matrix
    }

}