package com.example.tddcounter.test

import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.tddcounter.viewmodel.CounterViewModel
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CounterViewModelTest {

    @Test
    fun checkForStateFlow(){
        val counterViewModel = CounterViewModel()
        assertEquals(counterViewModel.state.value.counterValue, 0)
    }

    @Test
    fun checkIncrementZeroDecrementFunctions(){
        val counterViewModel = CounterViewModel()

        counterViewModel.incrementClicked()
        assertEquals(1,counterViewModel.state.value.counterValue)

        counterViewModel.restartClicked()
        assertEquals(0, counterViewModel.state.value.counterValue)

        counterViewModel.decrementClicked()
        assertEquals(-1, counterViewModel.state.value.counterValue)
    }

    @Test
    fun increaseOneHundred(){
        val counterViewModel = CounterViewModel()

        for (i in 1..100){
            counterViewModel.incrementClicked()
        }
        assertEquals(100, counterViewModel.state.value.counterValue)

        counterViewModel.restartClicked()
        assertEquals(0, counterViewModel.state.value.counterValue)

        for (i in 1..100){
            counterViewModel.decrementClicked()
        }
        assertEquals(-100, counterViewModel.state.value.counterValue)
    }
}