package com.flyoverfitness.ui.screens

import android.graphics.Paint
import android.widget.Toast
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.shape.ZeroCornerSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.TextUnit
import androidx.lifecycle.LiveData
import co.yml.charts.axis.AxisData
import co.yml.charts.common.model.Point
import co.yml.charts.ui.linechart.LineChart
import co.yml.charts.ui.linechart.model.GridLines
import co.yml.charts.ui.linechart.model.Line
import co.yml.charts.ui.linechart.model.LineChartData
import co.yml.charts.ui.linechart.model.LinePlotData
import com.flyoverfitness.R
import com.flyoverfitness.presentation.data.FitnessEntity
import com.flyoverfitness.presentation.sign_in.UserData
import com.flyoverfitness.presentation.viewmodel.FitnessViewModel
import com.flyoverfitness.ui.TopBar
import kotlin.math.round

@Composable
fun Profile(
    userData: UserData?,
    onSignOut: () -> Unit,
    viewModel: FitnessViewModel,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var height by remember { mutableStateOf("") }
        var weight by remember { mutableStateOf("") }
        var targetWeight by remember { mutableStateOf("") }
        val showDialog = remember { mutableStateOf(false) }
        val showError = remember { mutableStateOf(false) }
        var hasError by remember { mutableStateOf(false) }
        val fitnessData by viewModel.getAll.observeAsState(listOf())
        val data = viewModel.prepareMap(fitnessData)
        TopBar(title = "Flyover Fitness", onSignOut = onSignOut)
        Row(
            modifier = Modifier.padding(8.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (userData?.profilePictureUrl != null) {
                AsyncImage(
                    model = userData.profilePictureUrl,
                    contentDescription = "Profile Picture",
                    modifier = Modifier
                        .size(64.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.width(12.dp))
            }
            if (userData?.userName != null) {
                Text(
                    text = userData.userName,
                    textAlign = TextAlign.Center,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        LastEntryScreen(viewModel = viewModel)
        Spacer(modifier = Modifier.height(8.dp))
        Chart(data = data, maxValue = 120)
        Row(
            modifier = Modifier.padding(16.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Button(onClick = { showDialog.value = true }, colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.inversePrimary)) {
                Text(text = "Update Details")
            }
            Spacer(modifier = Modifier.width(8.dp))
            Button(onClick = onSignOut, colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.inversePrimary
            )) {
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
                        hasError = false // Reset error flag before validation

                        // Validate each field and update hasError
                        if (height.isEmpty()) {
                            hasError = true
                        }
                        if (weight.isEmpty()) {
                            hasError = true
                        }
                        if (targetWeight.isEmpty()) {
                            hasError = true
                        }

                        if (!hasError) {
                            val fitnessEntry = FitnessEntity(
                                id = 0,// Room will auto-generate this ID
                                height = height.toDoubleOrNull()
                                    ?: 0.0, // Handle potential conversion errors
                                weight = weight.toDoubleOrNull() ?: 0.0,
                                targetWeight = targetWeight.toDoubleOrNull() ?: 0.0
                            )
                            viewModel.addDetails(fitnessEntry)
                            showDialog.value = false
                        } else {
                            showError.value = true // Set flag to show separate error dialog
                        }
                    }) {
                        Text("Submit")

                    }
                },
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
                            onValueChange = { newValue ->
                                height = newValue
                                hasError = false // Reset error on input change
                            },
                            label = { Text("Height in cm") },
                            isError = hasError, // Set error state for all fields
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                            modifier = Modifier.fillMaxWidth()
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        OutlinedTextField(
                            value = weight,
                            onValueChange = { newValue ->
                                weight = newValue
                                hasError = false // Reset error on input change
                            },
                            label = { Text("Weight in kg") },
                            isError = hasError, // Set error state for all fields
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                            modifier = Modifier.fillMaxWidth()
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        OutlinedTextField(
                            value = targetWeight,
                            onValueChange = { newValue ->
                                targetWeight = newValue
                                hasError = false // Reset error on input change
                            },
                            label = { Text("Target Weight in kg") },
                            isError = hasError, // Set error state for all fields
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                            modifier = Modifier.fillMaxWidth()
                        )
// Display error message if any field is empty
                        if (showError.value) {
                            Text(
                                text = "Please fill all fields",
                                color = MaterialTheme.colorScheme.error,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 16.dp)
                            )
                        }
                    }
                }
            )
        }
    }
}

@Composable
fun LastEntryScreen(viewModel: FitnessViewModel) {
    val lastEntry by viewModel.lastEntry.observeAsState(null)

    if (lastEntry != null) {
        val bmi = lastEntry!!.weight / ((lastEntry!!.height / 100) * (lastEntry!!.height / 100))
        val actualWeight = lastEntry!!.weight
        val targetWeight = lastEntry!!.targetWeight
        val diff =  actualWeight - targetWeight
        val targetBMI = 25
        val backgroundColor = when {
            bmi < 18.5 -> Color.Blue // Underweight
            bmi >= 18.5 && bmi < 26 -> Color.Green // Normal weight
            bmi >= 26 && bmi < 30 -> Color.Red // Overweight
            else -> Color.Red // Obese
        }
        val msg = when {
            bmi < 18.5 -> "You are Underweight!" // Underweight
            bmi >= 18.5 && bmi < 26 -> "You have Normal weight" // Normal weight
            bmi >= 26 && bmi < 30 -> "You are OVERWEIGHT, keep pushing!" // Overweight
            else -> "You are OBESE!! You gotta shed off some kilos!" // Obese
        }

        val target = when {
            actualWeight > targetWeight  -> "lose"
            else -> "gain" // Obese
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            HorizontalDivider(
                modifier = Modifier
                    .fillMaxWidth(),
                thickness = 1.dp,
                color = MaterialTheme.colorScheme.outline
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 26.dp, end = 26.dp, bottom = 4.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.obese),
                    contentDescription = null,
                    //tint = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier
                        .size(42.dp)
                        .padding(start = 12.dp)

                )
                Text(
                    "Weight: ${lastEntry!!.weight}kg",
                    fontSize = 18.sp,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(start = 10.dp)
                )

            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 26.dp, end = 26.dp, bottom = 4.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.target),
                    contentDescription = null,
                    //tint =  MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier
                        .size(42.dp)
                        .padding(start = 12.dp)

                )
                Text(
                    "Target Weight: ${lastEntry!!.targetWeight}kg",
                    fontSize = 18.sp,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(start = 10.dp)
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            WeightProgressBar(actualWeight = actualWeight.toFloat(), targetWeight = targetWeight.toFloat(), color = backgroundColor)
            Text(
                text = "You intend to $target ${diff.toFloat()}kg?. Keep attending our sessions at Flyover Fitness!",
                fontSize = 16.sp,
                textAlign = TextAlign.Justify,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(10.dp)
            )
        }
        HorizontalDivider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            thickness = 1.dp,
            color = MaterialTheme.colorScheme.outline
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 26.dp, end = 26.dp, bottom = 4.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.obese),
                    contentDescription = null,
                    //tint = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier
                        .size(42.dp)
                        .padding(start = 12.dp)

                )
                Text(
                    "BMI: ${bmi.toFloat()}",
                    fontSize = 18.sp,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(start = 10.dp)
                )

            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 26.dp, end = 26.dp, bottom = 4.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.target),
                    contentDescription = null,
                    //tint =  MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier
                        .size(42.dp)
                        .padding(start = 12.dp)

                )
                Text(
                    "Recommended BMI: 25.0",
                    fontSize = 18.sp,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(start = 10.dp)
                )
            }
            WeightProgressBar(actualWeight = bmi.toFloat(), targetWeight = targetBMI.toFloat(), color = backgroundColor)
            Text(
                text = msg,
                color = backgroundColor,
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Start,
                modifier = Modifier.padding(10.dp)
            )
        }
       // WeightProgressBar(weight = actualWeight.toFloat())
        //CircularProgressBar(percentage = (targetWeight / actualWeight).toFloat(), number = diff , bmi = bmi)


        HorizontalDivider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, bottom = 8.dp),
            thickness = 1.dp,
            color = MaterialTheme.colorScheme.outline
        )

    } else {
        Text("No entries found")
    }
}

@Composable
fun CircularProgressBar(
    percentage: Float,
    number: Double,
    fontSize: TextUnit = 16.sp,
    radius: Dp = 54.dp,
    strokeWidth: Dp = 6.dp,
    animDuration: Int = 1000,
    animDelay: Int = 0,
    bmi: Double,
) {
    val progColor = when {
        bmi < 18.5 -> MaterialTheme.colorScheme.inversePrimary // Underweight
        bmi >= 18.5 && bmi < 25 -> MaterialTheme.colorScheme.outline // Normal weight
        bmi >= 25 && bmi < 30 -> MaterialTheme.colorScheme.error // Overweight
        else -> MaterialTheme.colorScheme.error // Obese
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
        modifier = Modifier
            .size(radius * 2f)
            .padding(4.dp)
    ) {
        Canvas(modifier = Modifier.size(radius * 2f)) {
            drawArc(
                color = progColor,
                -180f,
                180 * curPercentage.value,
                useCenter = false,
                style = Stroke(strokeWidth.toPx(), cap = StrokeCap.Round)
            )
        }
        Text(
            text = (number).toFloat().toString() + "kg",
            color = progColor,
            fontSize = fontSize,
            fontWeight = FontWeight.Bold
        )
    }
}

// This code assumes your Room entity is named FitnessEntity with fields nowDate and weight

@Composable
fun WeightChart(
    allEntries: LiveData<List<FitnessEntity>>
) {
    val entries by allEntries.observeAsState(initial = emptyList())
    val pointsData = entries.map { Point(it.id.toFloat(), it.weight.toFloat()) } // Use time in milliseconds for x-axis

    if (entries.isEmpty()) {
        Text(text = "No weight data available")
    } else {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            val xAxisData = AxisData.Builder()
                .labelData { i -> i.toString() }
                .steps(pointsData.size)
                .labelAndAxisLinePadding(15.dp)
                .build()

            val yAxisData = AxisData.Builder()
                .steps(7) // Adjust number of steps for desired labels
                .labelData { i -> (i * 10).toString() } // Set fixed labels (e.g., 0, 10, 20, ...)
                .labelAndAxisLinePadding(20.dp)
                .build()

            val lineChartData = LineChartData(
                linePlotData = LinePlotData(
                    lines = listOf(
                        Line(
                            dataPoints = pointsData,
                        )
                    ),
                ),
                xAxisData = xAxisData,
                yAxisData = yAxisData,
                gridLines = GridLines(),
                backgroundColor = Color.White
            )
            LineChart(modifier = Modifier.fillMaxSize(), lineChartData = lineChartData)
        }
    }
}

@Composable
fun Chart(
    data: Map<Float, String>,
    maxValue: Int
) {

    val context = LocalContext.current
    // BarGraph Dimensions
    val barGraphHeight by remember { mutableStateOf(150.dp) }
    val barGraphWidth by remember { mutableStateOf(12.dp) }
    // Scale Dimensions
    val scaleYAxisWidth by remember { mutableStateOf(50.dp) }
    val scaleLineWidth by remember { mutableStateOf(2.dp) }

    Column(
        modifier = Modifier
            .padding(horizontal = 32.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            text = "Weigh yourself either weekly, fortnightly or monthly, graph shows last 12 entries!",
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
            textAlign = TextAlign.Justify,
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(barGraphHeight),
            verticalAlignment = Alignment.Bottom,
            //horizontalArrangement = Arrangement.Start
        ) {
            // scale Y-Axis
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(scaleYAxisWidth)
                    .padding(bottom = 5.dp),
                verticalArrangement = Arrangement.SpaceAround
            ) {
                Text(text = maxValue.toString())
                Text(text = (maxValue - 40).toString())
                Text(text = (maxValue - 80).toString())
            }

            // Y-Axis Line
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(scaleLineWidth)
                    .background(Color.Black)
            )

            // graph
            data.forEach {
                Box(
                    modifier = Modifier
                        .padding(start = barGraphWidth, bottom = 5.dp)
                        .clip(CircleShape)
                        .width(barGraphWidth)
                        .height((it.key.toInt()).dp)
                        .background(MaterialTheme.colorScheme.inversePrimary)
                        .clickable {
                            Toast
                                .makeText(
                                    context,
                                    "Weight: " + it.key.toString() + "kg",
                                    Toast.LENGTH_SHORT
                                )
                                .show()
                        },
                )
            }

        }

        // X-Axis Line
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(scaleLineWidth)
                .background(Color.Black)
        )

        // Scale X-Axis
        Row(
            modifier = Modifier
                .padding(start = scaleYAxisWidth + barGraphWidth + scaleLineWidth)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(barGraphWidth)
        ) {

            data.values.forEach {
                Text(
                    modifier = Modifier.width(barGraphWidth),
                    text = it,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Composable
fun WeightProgressBar(
    actualWeight: Float,
    targetWeight: Float,
    color: Color,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(20.dp)
            .padding(horizontal = 8.dp)
    ) {
        // Progress bar background
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(8.dp)
                .background(Color.DarkGray, shape = RoundedCornerShape(12.dp))
        )

        // Progress bar fill
        Box(
            modifier = Modifier
                .fillMaxWidth(targetWeight / actualWeight)
                .height(8.dp)
                .background(
                    color,
                    shape = RoundedCornerShape(
                        topStart = 12.dp,
                        bottomStart = 12.dp,
                        topEnd = 0.dp,
                        bottomEnd = 0.dp
                    )
                )
        )
    }
}

@Composable
fun WeightProgressBar(weight: Float) {
    val stages = listOf(
        Stage("Underweight", 0f, 18.5f, Color.Green),
        Stage("Normal weight", 18.5f, 25f, Color.Yellow),
        Stage("Overweight", 25f, 30f, Color.Cyan),
        Stage("Obese", 30f, Float.POSITIVE_INFINITY, Color.Red)
    )

    val progress = stages.find { weight in it.range }?.progress(weight) ?: 0f

    ProgressBar(
        progress = progress,
        stages = stages.map { it.color }
    )
}

@Composable
fun ProgressBar(progress: Float, stages: List<Color>) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(20.dp)
            .background(Color.Gray)
    ) {
        Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .height(20.dp)
        ) {
            val width = size.width * progress
            stages.forEachIndexed { index, color ->
                drawRect(
                    color = color,
                    topLeft = Offset(
                        x = size.width * (index.toFloat() / stages.size),
                        y = 0f
                    ),
                    size = Size(
                        width = size.width * (1.toFloat() / stages.size),
                        height = size.height
                    )
                )
            }
        }
    }
}

data class Stage(val name: String, val min: Float, val max: Float, val color: Color) {
    val range = min..max

    fun progress(weight: Float) = (weight - min) / (max - min)
}




@Composable
fun TopBar( title: String, onSignOut: () -> Unit) {
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
                { Text("Update") },
                onClick = { /* Handle menu item click */ }
            )
            DropdownMenuItem(
                { Text("Permissions") },
                onClick = { /* Handle menu item click */ }
            )
            DropdownMenuItem(
                { Text("Erase User Data") },
                onClick = { /* Handle menu item click */ }
            )
            DropdownMenuItem(
                { Text("Sign Out") },
                onClick = onSignOut
            )
        }
    }
}
