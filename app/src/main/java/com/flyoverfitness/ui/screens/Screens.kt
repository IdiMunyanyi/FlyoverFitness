package com.flyoverfitness.ui.screens

import com.flyoverfitness.R

sealed class Screens (val screen: String){
    data object SignInScreen: Screens("SignInScreem")
    data object Home: Screens("Home")
    data object Sessions: Screens("Sessions")
    data object Profile: Screens("Profile")
    data object Champions: Screens("Champions")

}
