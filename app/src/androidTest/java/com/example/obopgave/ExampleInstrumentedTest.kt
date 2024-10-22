package com.example.obopgave


import androidx.compose.ui.test.assertIsDisplayed

import androidx.compose.ui.test.junit4.createComposeRule

import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.test.espresso.Espresso.closeSoftKeyboard

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.obopgave.Screen.LoginScreen


import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Rule

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */




@RunWith(AndroidJUnit4::class)
class LoginScreenTest {

    @get:Rule
    val Rule = createComposeRule()

    @Test
    fun testRegisterSuccess() {

        Rule.setContent {
            LoginScreen(
                message = "Registration successful",
                register = { email, password ->
                    if (email == "test@example.com" && password == "password123") {
                    }
                }
            )
        }


        Rule.onNodeWithText("Email").performTextInput("test@example.com")


        Rule.onNodeWithText("Password").performTextInput("password123")
        closeSoftKeyboard()

        Rule.onNodeWithText("Register").performClick()
        Rule.waitForIdle()

        Rule.onNodeWithText("Registration successful").assertIsDisplayed()
    }

    @Test
    fun testRegisterFailure() {

        Rule.setContent {
            LoginScreen(
                message = "The email address is already in use by another account.",
                register = { email, password ->

                    if (email != "test@example.com" || password != "password123") {

                    }
                }
            )
        }


        Rule.onNodeWithText("Email").performTextInput("test@example.com")


        Rule.onNodeWithText("Password").performTextInput("password123")

        closeSoftKeyboard()


        Rule.onNodeWithText("Register").performClick()
        Rule.waitForIdle()


        Rule.onNodeWithText("The email address is already in use by another account.").assertIsDisplayed()
    }

}