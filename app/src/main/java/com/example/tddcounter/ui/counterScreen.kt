package com.example.tddcounter.ui

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.ExposureNeg1
import androidx.compose.material.icons.sharp.ExposurePlus1
import androidx.compose.material.icons.sharp.RestartAlt
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.tddcounter.R
import com.example.tddcounter.viewmodel.CounterViewModel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun CounterScreen(
    counterViewModel: CounterViewModel = viewModel()
) {
    val state = counterViewModel.state.collectAsState().value


    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { counterViewModel.restartClicked() },
                backgroundColor = colorResource(id = R.color.purple_500),
                content = {
                    Icon(
                        imageVector = Icons.Sharp.RestartAlt,
                        contentDescription = "Reset button"
                    )
                })
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = colorResource(id = R.color.black)),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Log.d("Counter Screen", "Recomposition to: ${state.counterValue}")
            Text(
                text = state.counterValue.toString(),
                Modifier.testTag("Counter value"),
                fontSize = 100.sp,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {

                IconButton(
                    onClick = { counterViewModel.incrementClicked() },
                    Modifier.background(
                        color = colorResource(id = R.color.purple_500),
                        shape = CircleShape
                    ),
                    content = {
                        Icon(
                            imageVector = Icons.Sharp.ExposurePlus1,
                            contentDescription = "Plus button",
                            tint = Color.White
                        )
                    })

                IconButton(
                    onClick = { counterViewModel.decrementClicked() },
                    Modifier
                        .background(
                            color = colorResource(id = R.color.purple_500),
                            shape = CircleShape
                        )
                        .testTag("Minus button"),
                    content = {
                        Icon(
                            imageVector = Icons.Sharp.ExposureNeg1,
                            contentDescription = "",
                            tint = Color.White

                        )
                    })

            }


        }

    }
}