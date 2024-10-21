package com.example.obopgave.Screen

import android.widget.Toast
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
import androidx.compose.ui.platform.LocalContext
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
    var name by rememberSaveable { mutableStateOf(Beer.name ?: "No Name") }
    var abv by rememberSaveable { mutableStateOf(Beer.abv?.toString() ?: "0.0") }
    var user by rememberSaveable { mutableStateOf(Beer.user ?: "Unknown User") }
    var brewery by rememberSaveable { mutableStateOf(Beer.brewery ?: "Unknown Brewery") }
    var style by rememberSaveable { mutableStateOf(Beer.style ?: "Unknown Style") }
    var volume by rememberSaveable { mutableStateOf(Beer.volume?.toString() ?: "0.0") }
    var pictureUrl by rememberSaveable { mutableStateOf(Beer.pictureUrl ?: "Nothing") }
    var howMany by rememberSaveable { mutableStateOf(Beer.howMany?.toString() ?: "0") }
    var nameIsError by rememberSaveable { mutableStateOf(false) }
    var abvIsError by rememberSaveable { mutableStateOf(false) }
    var userIsError by rememberSaveable { mutableStateOf(false) }
    var breweryIsError by rememberSaveable { mutableStateOf(false) }
    var styleIsError by rememberSaveable { mutableStateOf(false) }
    var volumeIsError by rememberSaveable { mutableStateOf(false) }
    var pictureUrlIsError by rememberSaveable { mutableStateOf(false) }
    var howManyIsError by rememberSaveable { mutableStateOf(false) }
    val context = LocalContext.current
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
                isError = nameIsError,

                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                modifier = Modifier.fillMaxWidth(),
                label = { Text(text = "Name") })
            OutlinedTextField(
                onValueChange = { abv = it },
                value = abv,
                isError = abvIsError,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth(),
                label = { Text(text = "Abv") })
            OutlinedTextField(
                onValueChange = { user = it },
                value = user,
                isError = userIsError,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                modifier = Modifier.fillMaxWidth(),
                label = { Text(text = "User") })
            OutlinedTextField(
                onValueChange = { brewery = it },
                value = brewery,
                isError = breweryIsError,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                modifier = Modifier.fillMaxWidth(),
                label = { Text(text = "Brewery") })
            OutlinedTextField(
                onValueChange = { style = it },
                value = style,
                isError = styleIsError,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                modifier = Modifier.fillMaxWidth(),
                label = { Text(text = "Style") })
            OutlinedTextField(
                onValueChange = { volume = it },
                value = volume,
                isError = volumeIsError,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth(),
                label = { Text(text = "Volume") })
            OutlinedTextField(
                onValueChange = { pictureUrl = it },
                value = pictureUrl,
                isError = pictureUrlIsError,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                modifier = Modifier.fillMaxWidth(),
                label = { Text(text = "Picture URL") })
            OutlinedTextField(
                onValueChange = { howMany = it },
                value = howMany.toString(),
                isError = howManyIsError,
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
                    if (name.isEmpty()) {
                        nameIsError = true
                        Toast.makeText(context, "Name cannot be empty", Toast.LENGTH_SHORT).show()
                        return@Button
                    }

                    // 验证 ABV 是否为空
                    if (abv.isEmpty()) {
                        abvIsError = true
                        Toast.makeText(context, "ABV cannot be empty", Toast.LENGTH_SHORT).show()
                        return@Button
                    }

                    // 验证 ABV 是否为有效数字
                    val abvValue = abv.toDoubleOrNull()
                    if (abvValue == null) {
                        abvIsError = true
                        Toast.makeText(context, "ABV must be a valid number", Toast.LENGTH_SHORT)
                            .show()
                        return@Button
                    }

                    if (user.isEmpty()) {
                        userIsError = true
                        Toast.makeText(context, "User cannot be empty", Toast.LENGTH_SHORT).show()
                        return@Button
                    }
                    if (brewery.isEmpty()) {
                        breweryIsError = true
                        Toast.makeText(context, "Brewery cannot be empty", Toast.LENGTH_SHORT).show()
                        return@Button
                    }
                    if (style.isEmpty()) {
                        styleIsError = true
                        Toast.makeText(context, "Style cannot be empty", Toast.LENGTH_SHORT).show()
                        return@Button
                    }
                    if (volume.isEmpty()) {
                        volumeIsError = true
                        Toast.makeText(context, "Volume cannot be empty", Toast.LENGTH_SHORT).show()
                        return@Button
                    }
                    val volumeValue = volume.toDoubleOrNull()
                    if (volumeValue == null) {
                        volumeIsError = true
                        Toast.makeText(context, "Volume must be a valid number", Toast.LENGTH_SHORT)
                            .show()
                        return@Button
                    }

                    if (pictureUrl.isEmpty()) {
                        pictureUrlIsError = true
                        Toast.makeText(context, "Picture URL cannot be empty", Toast.LENGTH_SHORT).show()
                        return@Button
                    }
                    if (howMany.isEmpty()) {
                        howManyIsError = true
                        Toast.makeText(context, "How many cannot be empty", Toast.LENGTH_SHORT).show()
                        return@Button
                    }


                    val data = Beer(
                        name = name,
                        abv = abv.toDoubleOrNull() ?: Beer.abv,
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