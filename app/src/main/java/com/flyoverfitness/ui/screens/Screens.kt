package com.flyoverfitness.ui.screens

import com.flyoverfitness.R

sealed class Screens (val screen: String){
    data object Home: Screens("Home")
    data object Sessions: Screens("Sessions")
    data object Profile: Screens("Profile")
    data object YoMedals: Screens("YoMedals")
    data object Infor: Screens("Infor")
}
