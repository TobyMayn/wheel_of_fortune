package com.example.s215778_wheel_of_fortune.model

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf


class CharacterCard constructor(character: Char?, active: Boolean, cardColor: Int){
    var active:  MutableState<Boolean> = mutableStateOf(active)
    var char: MutableState<Char?> = mutableStateOf(character)
    var cardColor: MutableState<Int> = mutableStateOf(cardColor)
}