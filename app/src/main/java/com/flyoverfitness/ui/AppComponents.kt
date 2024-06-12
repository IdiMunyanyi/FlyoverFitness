package com.flyoverfitness.ui


import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.flyoverfitness.R
import com.flyoverfitness.ui.theme.bob
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import kotlinx.coroutines.delay


@Composable
fun TopBar( title: String, one: String, two: String, three: String, four: String, five: String, ) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(64.dp)
            .background(color = MaterialTheme.colorScheme.primary),
        horizontalArrangement = Arrangement.Absolute.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        var expanded by remember { mutableStateOf(false) }
        Box (modifier = Modifier.weight(0.5f), contentAlignment = Alignment.CenterStart){
            Text(
                text = title,
                color = MaterialTheme.colorScheme.onPrimary,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.titleLarge,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(start = 16.dp)
            )
        }

        Box(modifier = Modifier.weight(0.5f), contentAlignment = Alignment.CenterEnd) {
            IconButton(onClick = { expanded = !expanded }) {
                Icon(
                    Icons.Default.MoreVert,
                    contentDescription = "select",
                    modifier = Modifier.size(32.dp),
                    tint = MaterialTheme.colorScheme.onPrimary,
                )
            }
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            offset = DpOffset(212.dp, 4.dp),
            modifier = Modifier
                .wrapContentSize(Alignment.TopStart)
                .background(color = Color.Transparent, shape = RoundedCornerShape(25.dp))
        ) {
                DropdownMenuItem(
                    { Text(one) },
                    onClick = { /* Handle menu item click */ }
                )
                DropdownMenuItem(
                    { Text(two) },
                    onClick = { /* Handle menu item click */ }
                )
                DropdownMenuItem(
                    { Text(three) },
                    onClick = { /* Handle menu item click */ }
                )
                DropdownMenuItem(
                    { Text(four) },
                    onClick = { /* Handle menu item click */ }
                )
            DropdownMenuItem(
                { Text(five) },
                onClick = { }
            )
            }
        }
}

@Composable
fun Intro() {
    //Divider(thickness = 3.dp, color = Color.DarkGray)
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Icon(
            painter = painterResource(id = R.drawable.running),
            contentDescription = null,
            modifier = Modifier
                .padding(top = 4.dp)
                .size(180.dp),
            tint = MaterialTheme.colorScheme.secondary
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .background(color = MaterialTheme.colorScheme.inversePrimary, shape = RoundedCornerShape(15.dp)),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "We Sweat and Get Stronger Together!",
                color = MaterialTheme.colorScheme.onSurface,
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(top = 8.dp, bottom = 8.dp)
            )
        }
    }
    //Divider(thickness = 3.dp, color = Color.DarkGray)
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
                            -17.875795,
                            30.954931
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
                                .background(
                                    color = MaterialTheme.colorScheme.inversePrimary,
                                    shape = RoundedCornerShape(15.dp)
                                ),
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
                                fontSize = 20.sp,
                                textAlign = TextAlign.Center
                            )
                        }
                    },
                    confirmButton = {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth(),
                            contentAlignment = Alignment.Center
                        ) {
                            Button(
                                onClick = {
                                    showDialog = false
                                },
                                //colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.onTertiary)
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
        mutableIntStateOf(0)
    }
    LaunchedEffect(Unit) {
        while (true) {
            delay(3000)
            currentIndex.intValue = (currentIndex.intValue + 1) % images.size
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
            Testimonials(imageRes = images[currentIndex.intValue])

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
        "\"I have learnt that consistency is the key and I am glad with my steady progress.\" - Bee",
        "\"I lost 9 kilos in a space of 3 months and I have never looked back since then.\" - Idi",
        "\"I enjoy the variety of exercises like squats, running, pushups which keeps you motivated.\" - Linda",
        "\"The coaches are so patient but strict, they do push you to the limit.\" - Ma' Nyasha"
    )

    //val texts = remember { stringArrayResource(id = R.array.testimonials) }
    var currentIndex by remember { mutableStateOf(0) }

    LaunchedEffect(Unit) {
        while (true) {
            delay(8200) // Adjust the delay based on your preference
            currentIndex = (currentIndex + 1) % texts.size
        }
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = MaterialTheme.colorScheme.inversePrimary,
                shape = RoundedCornerShape(20.dp)
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = texts[currentIndex],
            fontSize = 18.sp,
            modifier = Modifier
                .padding(10.dp),
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurface,
            textAlign = TextAlign.Center
        )
        Row(
            modifier = Modifier.padding(8.dp),
            horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.Bottom
        ) {
            for (i in texts.indices) {
                TextIndicator(selected = i == currentIndex)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TestimonialsPreview() {
    TextCarousel()
}

@Composable
fun TextIndicator(selected: Boolean) {
    val color =
        if (selected) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurfaceVariant
    Box(
        modifier = Modifier
            .size(16.dp)
            .padding(4.dp)
            .clip(CircleShape)
            .background(color)
    )
}

@Composable
fun ScheduleTable() {
    Row(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
            .height(46.dp)
            .background(
                color = MaterialTheme.colorScheme.surfaceVariant,
                shape = RoundedCornerShape(20.dp)
            ),
        horizontalArrangement = Arrangement.Center
    ) {
        Box(modifier = Modifier.weight(1f)) {
            Text(
                text = "Mondays",
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
            .background(
                color = MaterialTheme.colorScheme.inversePrimary,
                shape = RoundedCornerShape(20.dp)
            ),
        horizontalArrangement = Arrangement.Center
    ) {
        Box(modifier = Modifier.weight(1f)) {
            Text(
                text = "Tuesdays",
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
            .background(
                color = MaterialTheme.colorScheme.surfaceVariant,
                shape = RoundedCornerShape(20.dp)
            ),
        horizontalArrangement = Arrangement.Center
    ) {
        Box(modifier = Modifier.weight(1f)) {
            Text(
                text = "Wednesdays",
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
            .background(
                color = MaterialTheme.colorScheme.inversePrimary,
                shape = RoundedCornerShape(20.dp)
            ),
        horizontalArrangement = Arrangement.Center
    ) {
        Box(modifier = Modifier.weight(1f)) {
            Text(
                text = "Thursdays",
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
            .background(
                color = MaterialTheme.colorScheme.surfaceVariant,
                shape = RoundedCornerShape(20.dp)
            ),
        horizontalArrangement = Arrangement.Center
    ) {
        Box(modifier = Modifier.weight(1f)) {
            Text(
                text = "Saturdays",
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
            //.height(46.dp)
            .background(
                color = MaterialTheme.colorScheme.error,
                shape = RoundedCornerShape(20.dp)
            ),
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = "NB: Last Saturday of every month we go for a half-marathon road-run.",
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(10.dp),
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onError
        )
    }
}

@Preview
@Composable
fun ScheduleTablePreview() {
    ScheduleTable()
}


@Composable
fun WorkoutButtons() {
    //var kingImage: Int = R.drawable.monkey
    //var queenImage: Int = R.drawable.monkey
    var selected = remember {
        mutableStateOf("Marathon")
    }
    val kingImage = when {
        selected.value == "Marathon" -> R.drawable.idi
        selected.value == "Squats" -> R.drawable.monkey // Underweight
        selected.value == "Push-Ups" -> R.drawable.stewie
        selected.value == "Plank" -> R.drawable.idi
        else -> R.drawable.idi
    }
    val queenImage = when (selected.value) {
        "Marathon" -> R.drawable.monkey
        "Squats" -> R.drawable.liz // Underweight
        "Push-Ups" -> R.drawable.monkey
        "Plank" -> R.drawable.liz
        else -> R.drawable.bg
    }
    val hailKing = when (selected.value) {
        "Marathon" -> "Completed 15km in 1hr 13 minutes!"
        "Squats" -> "Last-man-standing at 160 squats"
        "Push-Ups" -> "Last-man-standing at 52 push-ups"
        "Plank" -> "Last-man-standing at 4 mins 12 sec"
        else -> "Came tops in a 200m race"
    }
    val hailQueen = when (selected.value) {
        "Marathon" -> "Completed 15km in 1hr 13 minutes!"
        "Squats" -> "Last-man-standing at 160 squats"
        "Push-Ups" -> "Last-man-standing at 52 push-ups"
        "Plank" -> "Last-man-standing at 4 mins 12 sec"
        else -> "Came tops in a 200m race"
    }

    Column(modifier = Modifier.padding(10.dp)) {
        Row {  // First row with two buttons

            Button(
                onClick = { selected.value = "Marathon" },
                //colors = ButtonDefaults.buttonColors(BobGreen),
                colors = if (selected.value == "Marathon") ButtonDefaults.buttonColors(MaterialTheme.colorScheme.error) else ButtonDefaults.buttonColors(
                    MaterialTheme.colorScheme.inversePrimary
                ),
                modifier = Modifier
                    .weight(1f)
            ) {
                Text(
                    text = "Marathon",
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }

            Spacer(modifier = Modifier.width(4.dp))  // Add spacing between buttons
            Button(
                onClick = { selected.value = "Push-Ups" },
                colors = if (selected.value == "Push-Ups") ButtonDefaults.buttonColors(MaterialTheme.colorScheme.error) else ButtonDefaults.buttonColors(
                    MaterialTheme.colorScheme.inversePrimary
                ),
                modifier = Modifier
                    .weight(1f)
            ) {
                Text(
                    text = "Push-Ups",
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }
        }



        Row {  // Second row with three buttons
            Button(
                onClick = { selected.value = "Squats" },
                colors = if (selected.value == "Squats") ButtonDefaults.buttonColors(MaterialTheme.colorScheme.error) else ButtonDefaults.buttonColors(
                    MaterialTheme.colorScheme.outline
                ),
                modifier = Modifier
                    .weight(0.3f)
            ) {
                Text(
                    text = "Squats",
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }
            Spacer(modifier = Modifier.width(4.dp))  // Add spacing between rows

            Button(
                onClick = { selected.value = "Plank" },
                colors = if (selected.value == "Plank") ButtonDefaults.buttonColors(MaterialTheme.colorScheme.error) else ButtonDefaults.buttonColors(
                    MaterialTheme.colorScheme.onPrimaryContainer
                ),
                modifier = Modifier
                    .weight(0.3f)
            ) {
                Text(
                    text = "Plank",
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }
            Spacer(modifier = Modifier.width(4.dp))  // Add spacing between rows

            Button(
                onClick = { selected.value = "Track-Run" },
                colors = if (selected.value == "Track-Run") ButtonDefaults.buttonColors(
                    MaterialTheme.colorScheme.error
                ) else ButtonDefaults.buttonColors(MaterialTheme.colorScheme.outlineVariant),
                modifier = Modifier
                    .weight(0.4f)
            ) {
                Text(
                    text = "Track-Run",
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }

        }
        Champs(
            kingImage = kingImage,
            queenImage = queenImage,
            hailKing = hailKing,
            hailQueen = hailQueen
        )

    }
}


@Composable
fun Champs(kingImage: Int, queenImage: Int, hailKing: String, hailQueen: String) {
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
                painter = painterResource(id = kingImage),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .requiredSize(160.dp)
                    .padding(8.dp)
                    .clip(RoundedCornerShape(20.dp))
            )
            Text(
                text = hailKing,
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
                painter = painterResource(id = queenImage),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .requiredSize(160.dp)
                    .padding(8.dp)
                    .clip(RoundedCornerShape(20.dp))
            )
            Text(
                text = hailQueen,
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.White,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(6.dp)
            )
        }
    }
}