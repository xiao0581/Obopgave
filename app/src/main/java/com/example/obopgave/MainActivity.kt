package com.example.obopgave

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
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Settings
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
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.AlignmentLine
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.obopgave.Screen.BeerAdd
import com.example.obopgave.Screen.BeerDetails
import com.example.obopgave.Screen.BeerListScreen
import com.example.obopgave.Screen.LoginScreen

import com.example.obopgave.ViewModel.AuthenticationViewModel
import com.example.obopgave.ViewModel.Beer
import com.example.obopgave.ViewModel.BeerViewModel
import com.example.obopgave.ui.theme.ObopgaveTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ObopgaveTheme {
                MainScreen()
            }
        }
    }
}
    @Composable
    fun MainScreen(modifier: Modifier = Modifier) {
        val navController = rememberNavController()
        val viewModel: BeerViewModel = viewModel() // persistence
        val beers = viewModel.BeersFlow.value
        val errorMessage = viewModel.errorMessageFlow.value
        val authenticationViewModel: AuthenticationViewModel = viewModel()


        NavHost(navController = navController, startDestination = NavRouters.LoginScreen.route) {
            composable(NavRouters.LoginScreen.route) {
                LoginScreen(
                    modifier,
                    user=authenticationViewModel.user,
                    message = authenticationViewModel.message,
                    signIn = { email, password -> authenticationViewModel.signIn(email, password) },
                    register = { email, password -> authenticationViewModel.register(email, password) },
                    navigateToBeerlist = { navController.navigate(NavRouters.BeerListScreen.route) },
                    Beerlist = { viewModel.GetBeerByUser(authenticationViewModel.user?.email.toString()) }

                )
            }
            composable(NavRouters.BeerListScreen.route) {
                BeerListScreen(
                    beers = beers,
                    errorMessage = errorMessage,
                    modifier = modifier,

                    onBeerSelected =
                    { Beer -> navController.navigate(NavRouters.BeerDetails.route + "/${Beer.id}") },
                    onBeerDeleted =  { beer ->
                        viewModel.remove(beer)
                        viewModel.GetBeerByUser(authenticationViewModel.user?.email.toString()) // 删除后立即刷新列表
                    },
                    Beerlist = { viewModel.GetBeerByUser(authenticationViewModel.user?.email.toString()) },
                    onAdd = { navController.navigate(NavRouters.BeerAdd.route) },
                    sortByName = { viewModel.sortBooksByName(ascending = it) },
                    sortByAbv = { viewModel.sortBooksByAbv(ascending = it) },
                    filterByName = { viewModel.filterByName(it) }
                    ,
                    filterByAbv = { viewModel.filterByAbv(it) },
                    filterByNameAndAbv = { name, abv -> viewModel.filterByNameAndAbv(name, abv) },
                    onRefreshBeerList = {viewModel.GetBeerByUser(authenticationViewModel.user?.email.toString())} ,
                    user = authenticationViewModel.user,
                    signOut = { authenticationViewModel.signOut() },
                    navigateToAuthentication = {
                        navController.popBackStack(NavRouters.LoginScreen.route, inclusive = false)
                    }

                )
            }
            composable(
                NavRouters.BeerDetails.route + "/{BeerId}",
                arguments = listOf(navArgument("BeerId") { type = NavType.IntType })
            ) { backstackEntry ->
                val BeerId = backstackEntry.arguments?.getInt("BeerId")
                val Beer = beers.find { it.id == BeerId } ?: Beer( "", 0.0, "", "", "", 0.0, "", 0)
                BeerDetails(modifier = modifier,
                    Beer = Beer,
                    onUpdate = { id: Int, Beer: Beer -> viewModel.update(id, Beer) },
                    onNavigateBack = { navController.popBackStack() })
            }
            composable(NavRouters.BeerAdd.route) {
                BeerAdd(modifier = modifier,
                    addBeer = { Beer -> viewModel.add(Beer) },
                    navigateBack = { navController.popBackStack() })
            }

        }
    }
@Preview
@Composable
fun DefaultPreview() {
    ObopgaveTheme {
        MainScreen()
    }
}





