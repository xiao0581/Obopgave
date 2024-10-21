package com.example.obopgave.Screen

import android.content.res.Configuration
import android.util.Log
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview

import com.example.obopgave.ViewModel.Beer

@OptIn(ExperimentalMaterial3Api::class)
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
    Scaffold(modifier = modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = { Text("Add a Beer") })
        }) { innerPadding ->
        Column(modifier = modifier.padding(innerPadding)) {
            // TODO show error message
            val orientation = LocalConfiguration.current.orientation
            val isPortrait = orientation == Configuration.ORIENTATION_PORTRAIT
            // TODO refactor duplicated code: component MyTextField?
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
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text(text = "User") })
                OutlinedTextField(onValueChange = { brewery = it },
                    value = brewery,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text(text = "Brewery") })
                OutlinedTextField(onValueChange = { style = it },
                    value = style,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text(text = "Style") })
                OutlinedTextField(onValueChange = { volume = it },
                    value = volume,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text(text = "Volume") })
                OutlinedTextField(onValueChange = { pictureUrl = it },
                    value = pictureUrl,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text(text = "Picture URL") })
                OutlinedTextField(onValueChange = { howMany = it.toInt() },
                    value = howMany.toString(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text(text = "How many") })
            } else {
                Row(
                    modifier = modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
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
                        modifier = Modifier.weight(1f),
                        label = { Text(text = "User") })
                    OutlinedTextField(onValueChange = { brewery = it },
                        value = brewery,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                        modifier = Modifier.weight(1f),
                        label = { Text(text = "Brewery") })
                    OutlinedTextField(onValueChange = { style = it },
                        value = style,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                        modifier = Modifier.weight(1f),
                        label = { Text(text = "Style") })
                    OutlinedTextField(onValueChange = { volume = it },
                        value = volume,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        modifier = Modifier.weight(1f),
                        label = { Text(text = "Volume") })
                    OutlinedTextField(onValueChange = { pictureUrl = it },
                        value = pictureUrl,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                        modifier = Modifier.weight(1f),
                        label = { Text(text = "Picture URL") })
                    OutlinedTextField(onValueChange = {   howMany = it.toIntOrNull() ?: 0  },
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
                    // 验证名字是否为空

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

                    // 验证其他字段是否为空
                    if (user.isEmpty() || brewery.isEmpty() || style.isEmpty() || volume.isEmpty() || pictureUrl.isEmpty() || howMany == 0) {
                        Toast.makeText(context, "Please fill in all the fields", Toast.LENGTH_SHORT)
                            .show()
                        return@Button
                    }

                    try {
                        val Beer = Beer(
                            name = name,
                            abv = abvValue,
                            user = user,
                            brewery = brewery,
                            style = style,
                            volume = volume.toDouble(),
                            pictureUrl = pictureUrl,
                            howMany = howMany
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