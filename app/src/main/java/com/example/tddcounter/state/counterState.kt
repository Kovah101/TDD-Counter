package com.example.tddcounter.state

data class CounterState (
    val counterValue : Int
        ){
    companion object {
        val default = CounterState(
            counterValue = 0
        )
    }
}