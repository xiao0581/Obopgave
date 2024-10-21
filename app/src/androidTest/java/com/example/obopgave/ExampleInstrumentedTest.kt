package com.example.obopgave

import android.util.Log
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasClickAction
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.test.espresso.Espresso.closeSoftKeyboard
import androidx.test.platform.app.InstrumentationRegistry
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
    val composeTestRule = createComposeRule()

    @Test
    fun testRegisterSuccess() {
        // 设置测试内容
        composeTestRule.setContent {
            LoginScreen(
                message = "Registration successful",
                register = { email, password ->
                    if (email == "test@example.com" && password == "password123") {

                        // 更新 UI 显示“Registration successful”消息
                    }
                }
            )
        }


        composeTestRule.onNodeWithText("Email").performTextInput("test@example.com")


        composeTestRule.onNodeWithText("Password").performTextInput("password123")
        closeSoftKeyboard()

        composeTestRule.onNodeWithText("Register").performClick()
        composeTestRule.waitForIdle()

        composeTestRule.onNodeWithText("Registration successful").assertIsDisplayed()
    }

    @Test
    fun testRegisterFailure() {

        composeTestRule.setContent {
            LoginScreen(
                message = "The email address is already in use by another account.",
                register = { email, password ->

                    if (email != "test@example.com" || password != "password123") {

                    }
                }
            )
        }


        composeTestRule.onNodeWithText("Email").performTextInput("test@example.com")


        composeTestRule.onNodeWithText("Password").performTextInput("password123")

        closeSoftKeyboard()


        composeTestRule.onNodeWithText("Register").performClick()
        composeTestRule.waitForIdle()


        composeTestRule.onNodeWithText("The email address is already in use by another account.").assertIsDisplayed()
    }

}