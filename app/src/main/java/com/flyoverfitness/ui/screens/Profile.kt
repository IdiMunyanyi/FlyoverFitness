package com.flyoverfitness.ui.screens

import android.app.Application
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.ui.geometry.center
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import com.flyoverfitness.presentation.data.FitnessDB
import com.flyoverfitness.presentation.data.FitnessEntity
import com.flyoverfitness.presentation.repository.Repository
import com.flyoverfitness.presentation.sign_in.UserData
import com.flyoverfitness.presentation.viewmodel.FitnessViewModel
import com.flyoverfitness.ui.theme.Purple40
import java.util.Date

@Composable
fun Profile(
    userData: UserData?,
    onSignOut: () -> Unit,
    viewModel: FitnessViewModel
) {
    var height by remember { mutableStateOf("") }
    var weight by remember { mutableStateOf("") }
    var targetWeight by remember { mutableStateOf("") }
    val showDialog = remember { mutableStateOf(false) }
    Column(
        modifier = Modifier.fillMaxSize(),
        //verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (userData?.profilePictureUrl != null) {
            AsyncImage(
                model = userData.profilePictureUrl,
                contentDescription = "Profile Picture",
                modifier = Modifier
                    .size(150.dp)
                    .clip(CircleShape)
                    .padding(top = 8.dp),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(8.dp))
        }
        if (userData?.userName != null) {
            Text(
                text = userData.userName,
                textAlign = TextAlign.Center,
                fontSize = 36.sp,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(modifier = Modifier.height(8.dp))
        }

        LastEntryScreen(viewModel = viewModel)


        Button(onClick = { showDialog.value = true }) {
            Text(text = "Update Details")
        }
        Button(onClick = onSignOut) {
            Text(text = "Sign Out")
        }
    }
    if (showDialog.value) {
        AlertDialog(onDismissRequest = { showDialog.value = false },
            dismissButton = {
                Button(
                    onClick = {
                        showDialog.value = false
                    }) {
                    Text(text = "Cancel")
                }
            },
            confirmButton = {
                Button(onClick = {
                    val fitnessEntry = FitnessEntity(
                        id = 0, // Room will auto-generate this ID
                        height = height.toDouble(),
                        nowDate = System.currentTimeMillis(),
                        weight = weight.toDouble(),
                        targetWeight = targetWeight.toDouble()
                    )
                    viewModel.addDetails(fitnessEntry)
                    showDialog.value = false
                }) {
                    Text("Submit")

                }
            },
            title = {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = Purple40, shape = RoundedCornerShape(15.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Add Details",
                        fontWeight = FontWeight.Bold,
                        fontSize = 25.sp,
                        modifier = Modifier.padding(5.dp)
                    )
                }
            },
            text = {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    OutlinedTextField(
                        value = height,
                        onValueChange = { height = it },
                        label = { Text("Height in cm") },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    OutlinedTextField(
                        value = weight,
                        onValueChange = { weight = it },
                        label = { Text("Weight in kg") },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    OutlinedTextField(
                        value = targetWeight,
                        onValueChange = { targetWeight = it },
                        label = { Text("Target Weight in kg") },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        )
    }
}


@Composable
fun FitnessEntryScreen(viewModel: FitnessViewModel) {
    var height by remember { mutableStateOf("") }
    var weight by remember { mutableStateOf("") }
    var targetWeight by remember { mutableStateOf("") }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        OutlinedTextField(
            value = height,
            onValueChange = { height = it },
            label = { Text("Height") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = weight,
            onValueChange = { weight = it },
            label = { Text("Weight") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = targetWeight,
            onValueChange = { targetWeight = it },
            label = { Text("Target Weight") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            val fitnessEntry = FitnessEntity(
                id = 0, // Room will auto-generate this ID
                height = height.toDouble(),
                nowDate = System.currentTimeMillis(),
                weight = weight.toDouble(),
                targetWeight = targetWeight.toDouble()
            )
            viewModel.addDetails(fitnessEntry)
        }, modifier = Modifier.align(Alignment.End)) {
            Text("Submit")

        }
    }
}

@Composable
fun LastEntryScreen(viewModel: FitnessViewModel) {
    val lastEntry by viewModel.lastEntry.observeAsState(null)

    if (lastEntry != null) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .requiredHeight(68.dp)
                .padding(10.dp)
                .background(color = Purple40, shape = RoundedCornerShape(20.dp)),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .weight(1f)
            ) {
                Text(
                    "Weight: ${lastEntry!!.weight}kg",
                    fontSize = 20.sp,
                    color = Color.LightGray,
                    fontWeight = FontWeight.SemiBold,
                    // modifier = Modifier.padding(10.dp)
                )
            }
            Box(contentAlignment = Alignment.Center, modifier = Modifier.weight(1f)) {
                Text(
                    "Target: ${lastEntry!!.targetWeight}kg",
                    fontSize = 20.sp,
                    color = Color.LightGray,
                    fontWeight = FontWeight.SemiBold,
                    // modifier = Modifier.padding(10.dp)
                )
            }
        }
        val BMI = lastEntry!!.weight / ((lastEntry!!.height / 100) * (lastEntry!!.height / 100))
        val prog = lastEntry!!.targetWeight / lastEntry!!.weight
        val actualWeight = lastEntry!!.weight.toInt()
        val targetWeight = lastEntry!!.targetWeight.toInt()
        val diff = actualWeight - lastEntry!!.targetWeight.toFloat()
        RowWithDynamicBackgroundColor(bmi = BMI)
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ){
            CircularProgressBar(percentage = prog.toFloat(), number = targetWeight, bmi = BMI)
        }
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Text(
                text = "You are just ${diff}kg from your target. Keep attending our Sessions at Flyover Fitness!",
                fontSize = 18.sp,
                color = Color.LightGray,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(10.dp)
            )
        }
    } else {
        Text("No entries found")
    }
}

@Composable
fun RowWithDynamicBackgroundColor(bmi: Double) {
    // Define the color based on BMI value
    val backgroundColor = when {
        bmi < 18.5 -> Color.Blue // Underweight
        bmi >= 18.5 && bmi < 26 -> Color.Green // Normal weight
        bmi >= 26 && bmi < 30 -> Color.Red // Overweight
        else -> Color.Red // Obese
    }
    var msg = ""
    msg = when {
        bmi < 18.5 -> "You are Underweight!" // Underweight
        bmi >= 18.5 && bmi < 26 -> "You have Normal weight" // Normal weight
        bmi >= 26 && bmi < 30 -> "You are OVERWEIGHT!" // Overweight
        else -> "You are OBESE!!" // Obese
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .background(color = Color.DarkGray, shape = RoundedCornerShape(20.dp))
    ) {
        Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxWidth()) {
            Text(
                text = "BMI: ${bmi.toInt()} - $msg",
                fontSize = 20.sp,
                color = backgroundColor,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(10.dp)
            )
        }
    }
}

@Composable
fun CircularProgressBar(
    percentage: Float,
    number: Int,
    fontSize: TextUnit = 18.sp,
    radius: Dp = 50.dp,
    strokeWidth: Dp = 10.dp,
    animDuration: Int = 1000,
    animDelay: Int = 0,
    bmi: Double
) {
    val progColor = when {
        bmi < 18.5 -> Color.Blue // Underweight
        bmi >= 18.5 && bmi < 25 -> Color.Green // Normal weight
        bmi >= 25 && bmi < 30 -> Color.Red // Overweight
        else -> Color.Red // Obese
    }
    var animationPlayed by remember { mutableStateOf(false) }
    val curPercentage = animateFloatAsState(
        targetValue = if (animationPlayed) percentage else 0f,
        animationSpec = tween(durationMillis = animDuration, delayMillis = animDelay)

    )
    LaunchedEffect(key1 = true) {
        animationPlayed = true
    }
   Box(
       contentAlignment = Alignment.Center,
       modifier = Modifier.size(radius * 2f)
   ) {
       Canvas(modifier = Modifier.size(radius * 2f)) {
           drawArc(
               color = progColor,
               -90f,
               360 * curPercentage.value,
               useCenter = false,
               style = Stroke(strokeWidth.toPx(), cap = StrokeCap.Round)
           )
       }
       Text(
           text = (curPercentage.value * number).toInt().toString(),
           color = progColor,
           fontSize = fontSize,
           fontWeight = FontWeight.Bold
       )
   }
}


@Composable
fun WeightProgressBar(
    actualWeight: Float,
    targetWeight: Float,
    modifier: Modifier = Modifier
) {
    val progress = if (targetWeight > 0f) actualWeight / targetWeight else 0f

    LinearProgressIndicator(
        progress = { progress },
        modifier = modifier
            .fillMaxWidth()
            .height(16.dp),
    )
}
