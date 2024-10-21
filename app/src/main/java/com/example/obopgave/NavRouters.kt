package com.example.obopgave



sealed class NavRouters (val route: String) {
    data object LoginScreen : NavRouters("loginScreen")
    data object RegisterScreen : NavRouters("registerScreen")
    data object BeerListScreen : NavRouters("beerListScreen")
    data object UserPageScreen : NavRouters("userPageScreen")
    data object BeerDetails : NavRouters("beerDetails")
    data object BeerAdd : NavRouters("beerAdd")



}