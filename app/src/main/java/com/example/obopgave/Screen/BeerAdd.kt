package com.example.obopgave.Screen

import android.content.res.Configuration
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

import com.example.obopgave.ViewModel.Beer
import com.google.accompanist.flowlayout.FlowMainAxisAlignment

@OptIn(ExperimentalLayoutApi::class,ExperimentalMaterial3Api::class)
@Composable
fun BeerAdd(
    modifier: Modifier = Modifier,
    addBeer: (Beer) -> Unit = {},
    navigateBack: () -> Unit = {}
) {
    val context = LocalContext.current
    var name by rememberSaveable { mutableStateOf("") }
    var abv by rememberSaveable { mutableStateOf("") }
    var user by rememberSaveable { mutableStateOf("") }
    var brewery by rememberSaveable { mutableStateOf("") }
    var style by rememberSaveable { mutableStateOf("") }
    var volume by rememberSaveable { mutableStateOf("") }
    var pictureUrl by rememberSaveable { mutableStateOf("") }
    var howMany by rememberSaveable { mutableStateOf(0) }
    var nameIsError by rememberSaveable { mutableStateOf(false) }
    var abvIsError by rememberSaveable { mutableStateOf(false) }
    var userIsError by rememberSaveable { mutableStateOf(false) }
    var breweryIsError by rememberSaveable { mutableStateOf(false) }
    var styleIsError by rememberSaveable { mutableStateOf(false) }
    var volumeIsError by rememberSaveable { mutableStateOf(false) }
    var pictureUrlIsError by rememberSaveable { mutableStateOf(false) }
    var howManyIsError by rememberSaveable { mutableStateOf(false) }

    Scaffold(modifier = modifier.fillMaxSize(),
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF8B8D7B),
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = { Text("Add Beer") })
        }) { innerPadding ->
        Column(modifier = modifier.padding(innerPadding)) {

            val orientation = LocalConfiguration.current.orientation
            val isPortrait = orientation == Configuration.ORIENTATION_PORTRAIT
            if (isPortrait) {
                OutlinedTextField(onValueChange = { name = it },
                    value = name,
                    isError = nameIsError,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text(text = "Name") })
                OutlinedTextField(onValueChange = { abv = it },
                    value = abv,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    isError = abvIsError,
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text(text = "Abv") })
                OutlinedTextField(onValueChange = { user = it },
                    value = user,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                    isError = userIsError,
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text(text = "User") })
                OutlinedTextField(onValueChange = { brewery = it },
                    value = brewery,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                    isError = breweryIsError,
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text(text = "Brewery") })
                OutlinedTextField(onValueChange = { style = it },
                    value = style,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                    isError = styleIsError,
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text(text = "Style") })
                OutlinedTextField(onValueChange = { volume = it },
                    value = volume,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    isError = volumeIsError,
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text(text = "Volume") })
                OutlinedTextField(onValueChange = { pictureUrl = it },
                    value = pictureUrl,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                    isError = pictureUrlIsError,
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text(text = "Picture URL") })
                OutlinedTextField(onValueChange = { howMany = it.toInt() },
                    value = howMany.toString(),
                    isError = howManyIsError,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text(text = "How many") })
            } else {
                FlowRow(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    maxItemsInEachRow = 2
                ){
                    OutlinedTextField(onValueChange = { name = it },
                        value = name,
                        isError = nameIsError,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                        modifier = Modifier.weight(1f),
                        label = { Text(text = "Name") })
                    OutlinedTextField(onValueChange = { abv = it },
                        value = abv,

                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        isError = abvIsError,
                        modifier = Modifier.weight(1f),
                        label = { Text(text = "Abv") })
                    OutlinedTextField(onValueChange = { user = it },
                        value = user,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                        isError = userIsError,

                        modifier = Modifier.weight(1f),
                        label = { Text(text = "User") })
                    OutlinedTextField(onValueChange = { brewery = it },
                        value = brewery,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                        isError = breweryIsError,
                        modifier = Modifier.weight(1f),
                        label = { Text(text = "Brewery") })
                    OutlinedTextField(onValueChange = { style = it },
                        value = style,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                        isError = styleIsError,
                        modifier = Modifier.weight(1f),
                        label = { Text(text = "Style") })
                    OutlinedTextField(onValueChange = { volume = it },
                        value = volume,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        isError = volumeIsError,
                        modifier = Modifier.weight(1f),
                        label = { Text(text = "Volume") })
                    OutlinedTextField(onValueChange = { pictureUrl = it },
                        value = pictureUrl,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                        isError = pictureUrlIsError,
                        modifier = Modifier.weight(1f),
                        label = { Text(text = "Picture URL") })
                    OutlinedTextField(onValueChange = {   howMany = it.toIntOrNull() ?: 0  },
                        isError = howManyIsError,

                        value = howMany.toString(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        modifier = Modifier.weight(1f),
                        label = { Text(text = "How many") })

                }
            }
            Row(
                modifier = modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(onClick = { navigateBack() }) {
                    Text("Back")
                }
                Button(onClick = {


                    if (name.isEmpty()) {
                        nameIsError = true
                        Toast.makeText(context, "Name cannot be empty", Toast.LENGTH_SHORT).show()
                        return@Button
                    }


                    if (abv.isEmpty()) {
                        abvIsError = true
                        Toast.makeText(context, "ABV cannot be empty", Toast.LENGTH_SHORT).show()
                        return@Button
                    }


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
                    if (howMany == 0) {
                        howManyIsError = true
                        Toast.makeText(context, "How many cannot be empty", Toast.LENGTH_SHORT).show()
                        return@Button
                    }





                    try {
                       val Beer = Beer(
                            name = name.ifEmpty { "Unknown Name" },
                            abv = abvValue ?: 0.0,
                            user = user.ifEmpty { "Unknown User" },
                            brewery = brewery.ifEmpty { "Unknown Brewery" },
                            style = style.ifEmpty { "Unknown Style" },
                            volume = volume.toDoubleOrNull() ?: 0.0,
                            pictureUrl = pictureUrl.ifEmpty { "No URL" },
                            howMany = howMany.toString().toIntOrNull() ?: 1
                        )
                        addBeer(Beer)
                        navigateBack()
                    } catch (e: Exception) {
                        Log.e("BeerAdd", "Failed to add beer: ${e.message}")
                        Toast.makeText(
                            context,
                            "Failed to add beer: ${e.message}",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }) {
                    Text("Add")
                }
            }
        }
    }
}

@Preview
@Composable
fun BeerAddPreview() {
    BeerAdd()
}