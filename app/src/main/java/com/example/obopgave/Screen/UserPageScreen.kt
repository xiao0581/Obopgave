package com.example.obopgave.Screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserPageScreen(modifier: Modifier = Modifier) {
    Scaffold(

        topBar = {
            TopAppBar(
                colors = topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Row { Text("User´s")
                        Text("Beer")


                    }
                },



                )
        },


        ) { innerPadding ->
        UserbeerScreen(modifier = Modifier.padding(innerPadding))

    }
}

@Composable
fun UserbeerScreen(modifier: Modifier = Modifier) {
    Column ( modifier = modifier
        .fillMaxSize()
        .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Button( onClick = { }) { Text("MY Beerlist") }
        Row( ){
            OutlinedTextField(value = "", onValueChange = {  }, label = { Text("Search") })
            Button( onClick = { }) { Text("search") }
        }

    }
}
