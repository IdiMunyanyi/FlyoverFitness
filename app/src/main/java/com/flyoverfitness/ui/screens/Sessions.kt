package com.flyoverfitness.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.flyoverfitness.R
import com.flyoverfitness.ui.ImageSliderWindicator
import com.flyoverfitness.ui.TopBar
import com.flyoverfitness.ui.theme.GreenBob

@Composable
fun Sessions(navController: NavController) {
    val images = listOf(
        R.drawable.idi,
        R.drawable.logo,
        R.drawable.running
    )
    Surface (
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 0.dp)
                .background(
                    color = GreenBob,
                    shape = RectangleShape
                ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Flyover Fitness",
                color = Color.White,
                fontSize = 34.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 10.dp)
            )
            Column(
                modifier = Modifier
                    .padding(top = 5.dp, bottom = 5.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Support, Sweat and Stronger Together!",
                    color = Color.Black,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
        ImageSliderWindicator(images = images)
    }
}

@Preview
@Composable
fun SessionsPreview(){
    Sessions(navController = rememberNavController())
}