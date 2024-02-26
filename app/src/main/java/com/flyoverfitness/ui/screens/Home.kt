package com.flyoverfitness.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.flyoverfitness.R
import com.flyoverfitness.ui.ContactUs
import com.flyoverfitness.ui.ImageSliderWindicator
import com.flyoverfitness.ui.MainOptions
import com.flyoverfitness.ui.TextCarousel
import com.flyoverfitness.ui.TextSlider
import com.flyoverfitness.ui.TopBar

@Composable
fun Home(navHostController: NavHostController) {
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
            TopBar()
            Row(modifier = Modifier
                .padding(10.dp)
                .height(160.dp)
                )
                {
                TextCarousel()
            }
                Row {
                    Row(modifier = Modifier
                        .padding(10.dp)
                        .background(color = Color.LightGray, shape = RoundedCornerShape(20.dp)),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Column(
                            Modifier
                                .weight(0.5f)
                                .padding(end = 5.dp)
                                .padding(top = 6.dp, bottom = 6.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Box(
                                modifier = Modifier
                                    .padding(10.dp)
                                    .border(1.dp, Color.DarkGray, RoundedCornerShape(20.dp)),
                                contentAlignment = Alignment.Center
                            ){
                                Image(
                                    painter = painterResource(id = R.drawable.medal),
                                    contentDescription = null,
                                    contentScale = ContentScale.Fit,
                                    modifier = Modifier
                                        .requiredSize(50.dp)
                                        .padding(8.dp)
                                        .clickable {
                                            navHostController.navigate("YoMedals")
                                        }
                                )
                            }
                            Text(
                                text = "Medals",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = Color.Black
                            )
                        }

                        Column(
                            Modifier
                                .weight(0.5f)
                                .padding(end = 5.dp)
                                .padding(top = 6.dp, bottom = 6.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Box(
                                modifier = Modifier
                                    .padding(10.dp)
                                    .border(1.dp, Color.DarkGray, RoundedCornerShape(20.dp)),
                                contentAlignment = Alignment.Center
                            ){
                                Image(
                                    painter = painterResource(id = R.drawable.logo),
                                    contentDescription = null,
                                    contentScale = ContentScale.Fit,
                                    modifier = Modifier
                                        .requiredSize(50.dp)
                                        .padding(8.dp)
                                        .clickable {
                                            navHostController.navigate("Sessions")
                                        }
                                )
                            }
                            Text(
                                text = "Sessions",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = Color.Black
                            )
                        }

                        Column(
                            Modifier
                                .weight(0.5f)
                                .padding(end = 5.dp)
                                .padding(top = 6.dp, bottom = 6.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Box(
                                modifier = Modifier
                                    .padding(10.dp)
                                    .border(1.dp, Color.DarkGray, RoundedCornerShape(20.dp)),
                                contentAlignment = Alignment.Center
                            ){
                                Image(
                                    painter = painterResource(id = R.drawable.info),
                                    contentDescription = null,
                                    contentScale = ContentScale.Fit,
                                    modifier = Modifier
                                        .requiredSize(50.dp)
                                        .padding(8.dp)
                                        .clickable {
                                            //navHostController.navigate("Details")
                                        }
                                )
                            }
                            Text(
                                text = "Details",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.Black
                            )
                        }
                    }
                }



            Row(modifier = Modifier
                .padding(top = 10.dp)
                .border(1.dp, Color.DarkGray, RoundedCornerShape(10.dp)),
                verticalAlignment = Alignment.Bottom) {
                ContactUs()
            }
        }
    }




