package com.flyoverfitness.ui.screens

import android.content.Context
import android.net.Uri
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.outlined.PlayArrow
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import androidx.navigation.NavHostController
import com.flyoverfitness.R
import com.flyoverfitness.ui.ContactUs
import com.flyoverfitness.ui.Intro
import com.flyoverfitness.ui.TextCarousel
import com.flyoverfitness.ui.TopBar

@Composable
fun Home(navHostController: NavHostController) {
    val showDialog = remember { mutableStateOf(false) }
    val showCoaches = remember { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 0.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TopBar(title = "Flyover Fitness", one = "About", two = "Permissions", three = "Location", four ="Membership" , five = "Version 1.0")
        Intro()
        if (showDialog.value) {
            VideoPlayer(
                onBackClicked = { // This function is passed as an argument
                    showDialog.value = false
                    navHostController.popBackStack()  // Assuming VideoPlayer is a composable for playing videos
                }
            )
        } else {
            Row(
                modifier = Modifier
                    .padding(10.dp)
                    .height(132.dp)
            )
            {
                TextCarousel()
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
                    .height(70.dp)
                    .background(
                        color = MaterialTheme.colorScheme.inversePrimary,
                        shape = RoundedCornerShape(20.dp)
                    ),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .padding(start = 18.dp)
                        .background(
                            color = Color.White,
                            shape = RoundedCornerShape(25.dp)
                        )
                ) {
                    Icon(
                        Icons.Filled.PlayArrow,
                        contentDescription = null,
                        tint = Color.Black,
                        modifier = Modifier
                            .size(42.dp)
                            .clickable {
                                showDialog.value = true
                            }
                    )
                }
                Text(
                    text = "Highlights of our sessions.",
                    color = MaterialTheme.colorScheme.onSurface,
                    fontSize = 18.sp,
                    modifier = Modifier.padding(10.dp)
                )
            }

            if (showCoaches.value) {
                AlertDialog(
                    onDismissRequest = { showCoaches.value = false },
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
                                text = "Our Coaches",
                                fontSize = 22.sp,
                                modifier = Modifier.padding(5.dp)
                            )
                        }
                    },
                    text = {
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
                                        .requiredSize(130.dp)
                                        .padding(8.dp)
                                        .clip(RoundedCornerShape(20.dp))
                                )
                                Text(
                                    text = "Stewart Scott",
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.SemiBold,
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
                                        .requiredSize(130.dp)
                                        .padding(8.dp)
                                        .clip(RoundedCornerShape(20.dp))
                                )
                                Text(
                                    text = "Coach Rob",
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.SemiBold,
                                    textAlign = TextAlign.Center,
                                    modifier = Modifier.padding(6.dp)
                                )
                            }
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
                                    showCoaches.value = false
                                },
                                //colors = ButtonDefaults.buttonColors(Color.Gray),
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Text(text = "Noted", fontSize = 18.sp)
                            }
                        }

                    },
                )
            } else {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                        .height(70.dp)
                        .background(
                            color = MaterialTheme.colorScheme.inversePrimary,
                            shape = RoundedCornerShape(20.dp)
                        ),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .padding(start = 18.dp)
                            .background(
                                color = Color.White,
                                shape = RoundedCornerShape(25.dp)
                            )
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.logo),
                            contentDescription = null,
                            tint = Color.Black,
                            modifier = Modifier
                                .size(46.dp)
                                .padding(8.dp)
                                .clickable {
                                    showCoaches.value = true
                                }
                        )
                    }

                    Text(
                        text = "Our Coaches",
                        color = MaterialTheme.colorScheme.onSurface,
                        fontSize = 18.sp,
                        modifier = Modifier.padding(10.dp)
                    )
                }
            }
            Row(
                modifier = Modifier
                    .padding(top = 10.dp)
                    .background(
                        color = MaterialTheme.colorScheme.inversePrimary,
                        RoundedCornerShape(20.dp)
                    ),
                verticalAlignment = Alignment.Bottom
            ) {
                ContactUs()
            }
        }
    }
}

@Composable
fun VideoPlayer(onBackClicked: () -> Unit) {
    val exoPlayer: ExoPlayer
    //val playerView: PlayerView
    var showVideoPlayer by remember { mutableStateOf(false) }
    val context: Context = LocalContext.current
    exoPlayer = ExoPlayer.Builder(context).build()
    val videoUri = "android.resource://${context.packageName}/${R.raw.highlights}"
    val mediaItem = androidx.media3.common.MediaItem.fromUri(Uri.parse(videoUri))


    exoPlayer.setMediaItem(mediaItem)
    exoPlayer.prepare()
    exoPlayer.playWhenReady

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .requiredHeight(350.dp)
    ) {
        DisposableEffect(key1 = Unit) {
            onDispose {
                exoPlayer.release()

            }
        }
        AndroidView(factory = {
            PlayerView(context).apply {
                player = exoPlayer
                layoutParams =
                    FrameLayout.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.MATCH_PARENT
                    )
            }
        })
        IconButton( // Add IconButton for closing the video player
            modifier = Modifier.align(Alignment.TopEnd),
            onClick = onBackClicked
        ) {
            Icon(Icons.Default.Close, contentDescription = "Close Video")
        }
    }
}

