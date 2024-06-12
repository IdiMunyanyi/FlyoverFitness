package com.flyoverfitness.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.flyoverfitness.ui.WorkoutButtons



@Composable
fun YoMedals(navController: NavController) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 0.dp)
               .background(color = MaterialTheme.colorScheme.surfaceContainerLowest),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Reigning Champions",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 10.dp, bottom = 20.dp)
            )
            WorkoutButtons()
        }
    }

@Preview(showBackground = true)
@Composable
fun YoMedalsPreview() {
    YoMedals(navController = rememberNavController())
}

