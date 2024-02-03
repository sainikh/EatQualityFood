package com.example.mainactivity.Constants

sealed class Screen(val route: String){
    object Home : Screen(route = "Home")
    object Detail : Screen(route = "detail/{id}")
}