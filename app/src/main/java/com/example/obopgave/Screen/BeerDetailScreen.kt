package com.example.obopgave.Screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import com.example.obopgave.ViewModel.Beer


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BeerDetails(
    Beer: Beer,
    modifier: Modifier = Modifier,
    onUpdate: (Int, Beer) -> Unit = { id: Int, data: Beer -> },
    onNavigateBack: () -> Unit = {}
) {
    var name by rememberSaveable  { mutableStateOf(Beer.name) }
    var abv by rememberSaveable  { mutableStateOf(Beer.abv.toString()) }
    var user by rememberSaveable  { mutableStateOf(Beer.user) }
    var brewery by rememberSaveable  { mutableStateOf(Beer.brewery) }
    var style by rememberSaveable  { mutableStateOf(Beer.style) }
    var volume by rememberSaveable  { mutableStateOf(Beer.volume.toString()) }
    var pictureUrl by rememberSaveable  { mutableStateOf(if (Beer.pictureUrl == null) "Noting " else Beer.pictureUrl) }
    var howMany by rememberSaveable  { mutableStateOf(Beer.howMany.toString()) }

    Scaffold(modifier = modifier.fillMaxSize(), topBar = {
        TopAppBar(colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary,
        ), title = { Text("Beer details") })
    }) { innerPadding ->

        Column(modifier = modifier.padding(innerPadding)) {

            OutlinedTextField(
                onValueChange = { name = it },
                value = name,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                modifier = Modifier.fillMaxWidth(),
                label = { Text(text = "Name") })
            OutlinedTextField(
                onValueChange = { abv = it },
                value = abv,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth(),
                label = { Text(text = "Abv") })
            OutlinedTextField(
                onValueChange = { user = it },
                value = user,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                modifier = Modifier.fillMaxWidth(),
                label = { Text(text = "User") })
            OutlinedTextField(
                onValueChange = { brewery = it },
                value = brewery,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                modifier = Modifier.fillMaxWidth(),
                label = { Text(text = "Brewery") })
            OutlinedTextField(
                onValueChange = { style = it },
                value = style,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                modifier = Modifier.fillMaxWidth(),
                label = { Text(text = "Style") })
            OutlinedTextField(
                onValueChange = { volume = it },
                value = volume,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth(),
                label = { Text(text = "Volume") })
            OutlinedTextField(
                onValueChange = { pictureUrl = it },
                value = pictureUrl,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                modifier = Modifier.fillMaxWidth(),
                label = { Text(text = "Picture URL") })
            OutlinedTextField(
                onValueChange = { howMany = it },
                value = howMany.toString(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth(),
                label = { Text(text = "How many") })

            Row(
                modifier = modifier.fillMaxSize(), horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(onClick = { onNavigateBack() }) {
                    Text("Back")
                }
                Button(onClick = {
                    // TODO validation
                    val data = Beer(
                        name = name,
                        abv = abv.toDouble(),
                        user = user,
                        brewery = brewery,
                        style = style,
                        volume = volume.toDoubleOrNull() ?: Beer.volume,
                        pictureUrl = pictureUrl,
                        howMany = howMany.toIntOrNull() ?: Beer.howMany
                    )
                    onUpdate(Beer.id, data)
                    onNavigateBack()
                }) {
                    Text("Update")
                }
            }

        }
    }
}

@Preview
@Composable
fun BeerDetailsPreview() {
    BeerDetails(
        Beer = Beer("name", 1.2, "user", "brewery", "style", 0.5, "pictureUrl", 1)
    )
}