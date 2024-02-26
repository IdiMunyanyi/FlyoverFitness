package com.flyoverfitness.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.flyoverfitness.R
import com.flyoverfitness.ui.ContactUs
import com.flyoverfitness.ui.Intro
import com.flyoverfitness.ui.TextCarousel
import com.flyoverfitness.ui.TopBar
import com.flyoverfitness.ui.theme.Purple40

@Composable
fun Home(navHostController: NavHostController) {
    val showDialog = remember { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(0.dp)
            .background(color = Color.Black, shape = RoundedCornerShape(10.dp))
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TopBar()
        Intro()
        Row(
            modifier = Modifier
                .padding(10.dp)
                .height(140.dp)
                .background(color = Color.Black)
        )
        {
            TextCarousel()
        }
        if (showDialog.value) {

            VideoPlayer(
                videoUrl = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/TearsOfSteel.mp4",
                onBackClicked = { // This function is passed as an argument
                    showDialog.value = false
                    navHostController.popBackStack()  // Assuming VideoPlayer is a composable for playing videos
                }
            )

        } else {
            Box(
                modifier = Modifier
                    .padding(6.dp)
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.liz),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .clip(RoundedCornerShape(20.dp))
                        //.blur(3.dp, 3.dp)
                )
                Icon(
                    Icons.Outlined.PlayArrow,
                    contentDescription = null,
                    tint = Color.Gray,
                    modifier = Modifier.size(50.dp)
                        .clickable {
                            showDialog.value = true
                        }
                )
            }
        }

        Row(
            modifier = Modifier
                .padding(top = 10.dp)
                .background(color = Purple40, RoundedCornerShape(20.dp)),
            verticalAlignment = Alignment.Bottom
        ) {
            ContactUs()
        }
    }
}



