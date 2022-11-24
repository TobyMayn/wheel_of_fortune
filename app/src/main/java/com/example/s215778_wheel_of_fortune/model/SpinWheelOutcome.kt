package com.example.s215778_wheel_of_fortune.model

class SpinWheelOutcome {
    // Assigning values to points array. Index 0-8 = index times 1000
    // last entry in array = bankrupt
    val points = Array(10){i ->
        if(i == 9){
            "Bankrupt"
        } else {
            i * 1000
        }
    }

}