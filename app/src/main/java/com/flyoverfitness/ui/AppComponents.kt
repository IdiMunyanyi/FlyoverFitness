package com.flyoverfitness.ui

import android.content.ContentResolver
import android.content.ContentUris
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.MediaStore
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.flyoverfitness.R
import com.flyoverfitness.ui.theme.Purple40
import kotlinx.coroutines.delay


@Composable
fun TopBar(){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .background(color = Purple40, shape = RoundedCornerShape(20.dp)),
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Flyover Fitness",
            color = Color.White,
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = 5.dp, bottom = 5.dp)
        )
    }
}

@Composable
fun Intro() {
    //Divider(thickness = 3.dp, color = Color.DarkGray)
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 0.dp)
            .background(
                color = Color.Black,
                shape = RectangleShape
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Image(
            painter = painterResource(id = R.drawable.running),
            contentDescription = null,
            modifier = Modifier
                .padding(top = 2.dp)
                .height(150.dp)
                .clickable { }
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
                .background(color = Color.DarkGray, shape = RoundedCornerShape(20.dp)),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "We Sweat and Get Stronger Together!",
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(top = 8.dp, bottom = 8.dp)
            )
        }
    }
    //Divider(thickness = 3.dp, color = Color.DarkGray)
}

@Preview(showBackground = true)
@Composable
fun TopBarPreview() {
    TopBar()
}

@Composable

fun MainOptions() {

    Row(
        modifier = Modifier
            .padding(10.dp)
            .background(color = Color.Gray, shape = RoundedCornerShape(10.dp)),
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
                    .border(1.dp, Color.Gray, RoundedCornerShape(20.dp)),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.medal),
                    contentDescription = null,
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .requiredSize(50.dp)
                        .padding(8.dp)
                        .clickable { }
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
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = null,
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .requiredSize(50.dp)
                        .padding(8.dp)
                        .clickable {

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
            ) {
                Image(
                    painter = painterResource(id = R.drawable.information),
                    contentDescription = null,
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .requiredSize(50.dp)
                        .padding(8.dp)
                        .clickable { }
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

@Preview(showBackground = true)
@Composable
fun MainOptionsPreview() {
    MainOptions()
}

@Composable
fun ContactUs() {
    Row(
        modifier = Modifier
            .padding(5.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier.background(
                color = Color.LightGray,
                shape = RoundedCornerShape(25.dp)
            )
        ) {
            val context = LocalContext.current

            Image(
                painter = painterResource(id = R.drawable.location), // Replace with your image resource
                contentDescription = "Clickable Location Image",
                modifier = Modifier
                    .size(30.dp)
                    .padding(5.dp)
                    .clickable {
                        openGoogleMapsLocation(
                            context,
                            37.7749,
                            -122.4194
                        ) // Replace with the desired latitude and longitude
                    }
            )

        }

        Spacer(modifier = Modifier.width(20.dp))
        Box(modifier = Modifier.background(color = Color.Cyan, shape = RoundedCornerShape(25.dp))) {
            val context = LocalContext.current

            Image(
                painter = painterResource(id = R.drawable.whatsapp), // Replace with your image resource
                contentDescription = "Clickable WhatsApp Group Image",
                modifier = Modifier
                    .size(30.dp)
                    .padding(5.dp)
                    .clickable {
                        openWhatsAppGroup(context) // Replace with the actual group ID
                    }
            )

        }

        Spacer(modifier = Modifier.width(20.dp))
        Box(
            modifier = Modifier.background(
                color = Color.Green,
                shape = RoundedCornerShape(25.dp)
            )
        ) {
            var showDialog by remember { mutableStateOf(false) }
            Image(
                painter = painterResource(id = R.drawable.dollarz),
                contentDescription = null,
                modifier = Modifier
                    .requiredSize(30.dp)
                    .clickable {
                        showDialog = true
                    }
                    .padding(5.dp)
                //.border(1.dp, Color.Black, RoundedCornerShape(20.dp))
            )

            if (showDialog) {
                AlertDialog(
                    onDismissRequest = { showDialog = false },
                            title = {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(color = Purple40, shape = RoundedCornerShape(15.dp)),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "Subscriptions",
                                fontWeight = FontWeight.Bold,
                                fontSize = 25.sp,
                                modifier = Modifier.padding(5.dp)
                            )
                        }
                    },
                    text = {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "New members will pay a once-off subscription of $10 only!",
                                color = Color.LightGray,
                                fontSize = 20.sp,
                                textAlign = TextAlign.Center
                            )
                        }
                    },
                    confirmButton = {
                        Box(modifier = Modifier
                            .fillMaxWidth(),
                            contentAlignment = Alignment.Center){
                            Button(
                                onClick = {
                                    showDialog = false
                                },
                                colors = ButtonDefaults.buttonColors(
                                    Purple40
                                )
                            ) {
                                Text(text = "Noted", fontSize = 18.sp)
                            }
                        }

                    },
                )
            }
        }
    }
}


fun openGoogleMapsLocation(context: Context, latitude: Double, longitude: Double) {
    val gmmIntentUri = Uri.parse("geo:$latitude,$longitude")
    val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
    mapIntent.setPackage("com.google.android.apps.maps")

    if (mapIntent.resolveActivity(context.packageManager) != null) {
        context.startActivity(mapIntent)
    } else {
        // Handle case where Google Maps app is not installed
        // You can redirect to the Google Maps website or display a message
        // For simplicity, we'll just print a message to logcat
        println("Google Maps app is not installed.")
    }
}

fun openWhatsAppGroup(context: Context) {
    val uri = Uri.parse("https://chat.whatsapp.com/IHDNKerAw6v84yRFfdm6VO")
    val intent = Intent(Intent.ACTION_VIEW, uri)

    if (intent.resolveActivity(context.packageManager) != null) {
        context.startActivity(intent)
    } else {
        // Handle case where WhatsApp is not installed
        // You can redirect to the WhatsApp website or display a message
        // For simplicity, we'll just print a message to logcat
        println("WhatsApp is not installed.")
    }
}


@Preview(showBackground = true)
@Composable
fun ContactUsPreview() {
    ContactUs()
}

@Composable
fun OMedals() {
    Row(
        modifier = Modifier
            .padding(10.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        Column(
            Modifier
                .weight(0.5f)
                .padding(5.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.stewie),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .requiredSize(160.dp)
                    .padding(8.dp)
                    .clip(RoundedCornerShape(20.dp))
            )
            Text(
                text = "Completed 15km in 1hr 13 minutes!",
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.White,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(6.dp)
            )
        }

        Column(
            Modifier
                .weight(0.5f)
                .padding(5.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.idi),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .requiredSize(160.dp)
                    .padding(8.dp)
                    .clip(RoundedCornerShape(20.dp))
            )
            Text(
                text = "Last-Man-Standing at 42 push-ups!",
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.White,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(6.dp)
            )
        }
    }
    Row(
        modifier = Modifier
            .padding(10.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        Column(
            Modifier
                .weight(0.5f)
                .padding(5.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.stewie),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .requiredSize(160.dp)
                    .padding(8.dp)
                    .clip(RoundedCornerShape(20.dp))
            )
            Text(
                text = "Completed 15km in 1hr 13 minutes!",
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.White,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(6.dp)
            )
        }

        Column(
            Modifier
                .weight(0.5f)
                .padding(5.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.idi),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .requiredSize(160.dp)
                    .padding(8.dp)
                    .clip(RoundedCornerShape(20.dp))
            )
            Text(
                text = "Last-Man-Standing at 42 push-ups!",
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.White,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(6.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MedalsPreview() {
    OMedals()
}

val images = listOf(
    R.drawable.medal,
    R.drawable.logo,
    R.drawable.info
)

@Composable
fun Testimonials(imageRes: Int) {
    Image(
        painter = painterResource(id = imageRes),
        contentDescription = null,
        contentScale = ContentScale.Fit,
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .padding(.10.dp)
    )
}

@Composable
fun ImageSliderWindicator(images: List<Int>) {
    val currentIndex = remember {
        mutableStateOf(0)
    }
    LaunchedEffect(Unit) {
        while (true) {
            delay(3000)
            currentIndex.value = (currentIndex.value + 1) % images.size
        }
    }
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Box(
            modifier = Modifier
                .padding(top = 10.dp)
                .weight(1f)
                .background(
                    color = Color.Transparent,
                    shape = RoundedCornerShape(12.dp),
                ),
            contentAlignment = Alignment.Center
        ) {
            Testimonials(imageRes = images[currentIndex.value])

        }
    }
}

@Preview(showBackground = true)
@Composable
fun ImageSliderWindicatorPreview() {
    ImageSliderWindicator(images = images)
}


@Composable
fun TextCarousel() {
    val texts = arrayOf(
        "\"I no longer miss a session at Flyover Fitness. I now feel fit and healthier!\" - Ba' Lao",
        "\"Flyover Fitness Team is so supportive and this has helped to keep me motivated to achieve my fitness goals.\" - Ma' Vongi",
        "\"I weighed 104 kilos when I joined Flyover Fitness and 3 months down the line I had lost 9 kilos. I have never looked back since then.\" - Idi",
        "\"I enjoy the variety of exercises like squats, running, pushups which keeps you motivated and raring to go \" - Linda",
        "\"The coaches are so patient but strict. They do push you to the limit and I have learnt what endurance really is\" - Ma' Nyasha"
    )

    //val texts = remember { stringArrayResource(id = R.array.testimonials) }
    var currentIndex by remember { mutableStateOf(0) }

    LaunchedEffect(Unit) {
        while (true) {
            delay(8200) // Adjust the delay based on your preference
            currentIndex = (currentIndex + 1) % texts.size
        }
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Purple40, shape = RoundedCornerShape(20.dp)),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = texts[currentIndex],
            fontSize = 18.sp,
            modifier = Modifier
                .padding(10.dp)
                .clip(MaterialTheme.shapes.medium)
                .background(Color.Transparent),
            style = MaterialTheme.typography.bodyLarge,
            color = Color.White,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TestimonialsPreview() {
    TextCarousel()
}

@Composable
fun ScheduleTable() {
    Row(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
            .height(46.dp)
            .background(color = Color.DarkGray, shape = RoundedCornerShape(20.dp)),
        horizontalArrangement = Arrangement.Center
    ) {
        Box(modifier = Modifier.weight(1f)) {
            Text(
                text = "Mondays",
                color = Color.LightGray,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(10.dp)
            )
        }
        Divider(
            color = Color.LightGray, modifier = Modifier
                .fillMaxHeight()
                .width(1.dp)
        )
        Box(modifier = Modifier.weight(2f)) {
            Text(
                text = "Suspension Day",
                color = Color.LightGray,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(10.dp)
            )
        }
    }
    Row(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
            .height(46.dp)
            .background(color = Purple40, shape = RoundedCornerShape(20.dp)),
        horizontalArrangement = Arrangement.Center
    ) {
        Box(modifier = Modifier.weight(1f)) {
            Text(
                text = "Tuesdays",
                color = Color.LightGray,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(10.dp)
            )
        }
        Divider(
            color = Color.LightGray, modifier = Modifier
                .fillMaxHeight()
                .width(1.dp)
        )
        Box(modifier = Modifier.weight(2f)) {
            Text(
                text = "HIIT Step",
                color = Color.LightGray,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(10.dp)
            )
        }
    }
    Row(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
            .height(46.dp)
            .background(color = Color.DarkGray, shape = RoundedCornerShape(20.dp)),
        horizontalArrangement = Arrangement.Center
    ) {
        Box(modifier = Modifier.weight(1f)) {
            Text(
                text = "Wednesdays",
                color = Color.LightGray,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(10.dp)
            )
        }
        Divider(
            color = Color.LightGray, modifier = Modifier
                .fillMaxHeight()
                .width(1.dp)
        )
        Box(modifier = Modifier.weight(2f)) {
            Text(
                text = "Abs Day",
                color = Color.LightGray,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(10.dp)
            )
        }
    }
    Row(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
            .height(46.dp)
            .background(color = Purple40, shape = RoundedCornerShape(20.dp)),
        horizontalArrangement = Arrangement.Center
    ) {
        Box(modifier = Modifier.weight(1f)) {
            Text(
                text = "Thursdays",
                color = Color.LightGray,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(10.dp)
            )
        }
        Divider(
            color = Color.LightGray, modifier = Modifier
                .fillMaxHeight()
                .width(1.dp)
        )
        Box(modifier = Modifier.weight(2f)) {
            Text(
                text = "10km Jogging Day",
                color = Color.LightGray,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(10.dp)
            )
        }
    }
    Row(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
            .height(46.dp)
            .background(color = Color.DarkGray, shape = RoundedCornerShape(20.dp)),
        horizontalArrangement = Arrangement.Center
    ) {
        Box(modifier = Modifier.weight(1f)) {
            Text(
                text = "Saturdays",
                color = Color.LightGray,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(10.dp)
            )
        }
        Divider(
            color = Color.LightGray, modifier = Modifier
                .fillMaxHeight()
                .width(1.dp)
        )
        Box(modifier = Modifier.weight(2f)) {
            Text(
                text = "One Hour Non-Stop",
                color = Color.LightGray,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(10.dp)
            )
        }
    }
}

@Preview
@Composable
fun ScheduleTablePreview() {
    ScheduleTable()
}
