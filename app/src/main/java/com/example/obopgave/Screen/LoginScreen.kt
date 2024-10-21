package com.example.obopgave.Screen



import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.AlertDialogDefaults.shape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.AlignmentLine
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.Visibility
import com.example.obopgave.NavRouters
import com.example.obopgave.ui.theme.ObopgaveTheme
import com.google.firebase.auth.FirebaseUser

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    user: FirebaseUser? = null,
    message: String = "",
    signIn: (email: String, password: String) -> Unit = { _, _ -> },
    register: (email: String, password: String) -> Unit = { _, _ -> },
    navigateToWelcome: () -> Unit = {}
) {
    if (user != null) {
        LaunchedEffect(key1 = true) {
            navigateToWelcome()
        }
    }
    val emailStart = "xiao0581@edu.zealand.dk" // TODO remove starting email
    val passwordStart = "123456" // TODO remove starting password
    var email by remember { mutableStateOf(emailStart) }
    var password by remember { mutableStateOf(passwordStart) }
    var emailIsError by remember { mutableStateOf(false) }
    var passwordIsError by remember { mutableStateOf(false) }
    var showPassword by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = { Text("Beer App") }
            )
        }
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding).padding(16.dp).fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {
            // TODO layout for landscape: side by side
            Text( text = "Login" ,
                fontSize = 24.sp,
                color = Color.Gray,
                fontWeight = FontWeight.Bold
            )
            if (user!=null) {
                Text("Welcome ${user.email ?: "unknown"}")
            }
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = email,
                onValueChange = { email = it },
                label = { Text("Email") },
                isError = emailIsError,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                shape = RoundedCornerShape(10.dp),

            )
            if (emailIsError) {
                Text("Invalid email", color = MaterialTheme.colorScheme.error)
            }
            OutlinedTextField(

                modifier = Modifier.fillMaxWidth(),
                value = password,
                onValueChange = { password = it },
                label = { Text("Password") },
                shape = RoundedCornerShape(10.dp),

                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                visualTransformation =
                if (showPassword) VisualTransformation.None else PasswordVisualTransformation(),
                isError = passwordIsError,
                trailingIcon = {
                    IconButton(onClick = { showPassword = !showPassword }) {
                        if (showPassword) {
                            Icon(Icons.Filled.Visibility, contentDescription = "Hide password")

                        } else {
                            Icon(Icons.Filled.VisibilityOff, contentDescription = "Show password")
                        }
                    }
                }
            )
            if (passwordIsError) {
                Text("Invalid password", color = MaterialTheme.colorScheme.error)
            }
            if (message.isNotEmpty()) {
                Text(message, color = MaterialTheme.colorScheme.error)
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(onClick = {
                    // TODO same validation for register and sign in
                    register(email, password)
                }) {
                    Text("Register")
                }
                Button(onClick = {
                    email = email.trim()
                    if (email.isEmpty() || !validateEmail(email)) {
                        emailIsError = true
                        return@Button
                    } else {
                        emailIsError = false
                    }
                    password = password.trim()
                    if (password.isEmpty()) {
                        passwordIsError = true
                        return@Button
                    } else {
                        passwordIsError = false
                    }
                    signIn(email, password)
                }) {
                    Text("Sign in")
                }
            }
        }
    }
}

private fun validateEmail(email: String): Boolean {
    return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
}
@Preview
@Composable
fun PreviewAuthentication() {
    ObopgaveTheme {
        LoginScreen()
    }
}
