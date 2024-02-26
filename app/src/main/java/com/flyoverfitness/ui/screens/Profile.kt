package com.flyoverfitness.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.flyoverfitness.R
import com.flyoverfitness.ui.ContactUs
import com.flyoverfitness.ui.MainOptions
import com.flyoverfitness.ui.TopBar
import com.flyoverfitness.ui.theme.GreenBob

@Composable
fun Profile(navController: NavController) {
    Image(
        painter = painterResource(id = R.drawable.tile),
        modifier = Modifier
            .fillMaxSize()
            .blur(radiusX = 5.dp, radiusY = 5.dp),
        contentDescription = "Background Image",
        contentScale = ContentScale.Crop
    )
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(0.dp),
        horizontalAlignment = Alignment.CenterHorizontally
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

        Image(
            painter = painterResource(id = R.drawable.idi),
            contentDescription = null,
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .requiredSize(150.dp)
                .padding(8.dp)
                .clip(RoundedCornerShape(20.dp))
                .clickable { }
        )
        Column(
            modifier = Modifier
                .padding(top = 5.dp, bottom = 5.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Idi",
                color = Color.Black,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold
            )
        }
        Row(modifier = Modifier
            .padding(5.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Height: 1.83m",
                color = Color.DarkGray,
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                text = "Weight: 97.5kg",
                color = Color.DarkGray,
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold
            )

            Spacer(modifier = Modifier.width(10.dp))
            Text(
                text = "BMI: 29.1 [Overweight]",
                color = Color.DarkGray,
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold
            )
        }
        Row(modifier = Modifier
            .padding(5.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Target: 92kg",
                color = Color.Red,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}

@Preview
@Composable
fun ProfilePreview(){
    Profile(navController = rememberNavController())
}