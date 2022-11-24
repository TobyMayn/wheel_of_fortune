package com.example.s215778_wheel_of_fortune.model

import androidx.compose.material3.CardColors

class CharacterCard constructor(character: Char?, active: Boolean, cardColor: Int){
    var active:  Boolean = active
    var char: Char? = character
    var cardColor: Int = cardColor
}