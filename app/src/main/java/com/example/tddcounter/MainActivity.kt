package com.example.tddcounter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.runtime.collectAsState
import com.example.tddcounter.ui.CounterScreen
import com.example.tddcounter.viewmodel.CounterViewModel

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent{

            CounterScreen(
//                state = state.value,
//                events = viewModel
            )
        }
    }
}