package com.example.s215778_wheel_of_fortune.model

class DisplayMatrix {

    private val matrixHeight = 4
    private val matrixWidth = 14

    var matrix = Array(matrixHeight){Array(matrixWidth){CharacterCard(null, false)}}
}