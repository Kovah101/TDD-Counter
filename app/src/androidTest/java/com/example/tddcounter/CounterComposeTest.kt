package com.example.tddcounter

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import com.example.tddcounter.ui.CounterScreen
import org.junit.Rule
import org.junit.Test

class CounterComposeTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun uiExistsTest(){

        composeTestRule.setContent {
            CounterScreen()
        }

        composeTestRule.onNodeWithTag("Counter value").assertIsDisplayed()

        composeTestRule.onNodeWithContentDescription("Reset button").assertHasClickAction()

        composeTestRule.onNodeWithContentDescription("Plus button").assertIsEnabled()

        composeTestRule.onNodeWithTag("Minus button").assertExists()

        composeTestRule.onRoot().printToLog("TAG")
    }

    @Test
    fun uiFunctionsTest(){

        composeTestRule.setContent {
            CounterScreen()
        }

        composeTestRule.onNodeWithTag("Counter value").assertTextEquals("0")

        composeTestRule.onNodeWithContentDescription("Plus button").performClick()
        composeTestRule.onNodeWithContentDescription("Plus button").performClick()
        composeTestRule.onNodeWithTag("Counter value").assertTextEquals("2")

        composeTestRule.onNodeWithContentDescription("Reset button").performClick()
        composeTestRule.onNodeWithTag("Counter value").assertTextEquals("0")

        for (i in 1..100){
            composeTestRule.onNodeWithTag("Minus button").performClick()
        }
        composeTestRule.onNodeWithTag("Counter value").assertTextEquals("-100")


    }
}