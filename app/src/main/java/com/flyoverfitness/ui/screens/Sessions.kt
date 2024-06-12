package com.flyoverfitness.ui.screens

import android.content.Context
import android.net.Uri
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
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
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.flyoverfitness.R
import com.flyoverfitness.ui.ContactUs
import com.flyoverfitness.ui.ScheduleTable
import com.flyoverfitness.ui.TopBar


@Composable
fun Sessions(navController: NavController) {
    val videosList = listOf(
        "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4",
        "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/TearsOfSteel.mp4",
        "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerBlazes.mp4",
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 0.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier
                .padding(top = 15.dp, bottom = 25.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Our Schedule",
                fontSize = 24.sp,
                fontWeight = FontWeight.SemiBold
            )
        }
        ScheduleTable()
        Spacer(modifier = Modifier.height(8.dp))
        /*YoImageGrid(
            videoList = videosList,
            imageList = imagesList,
            navController = rememberNavController()
        )
         */

        Row(
            modifier = Modifier
                .padding(top = 10.dp)
                .background(
                    color = MaterialTheme.colorScheme.surfaceContainerHigh,
                    RoundedCornerShape(20.dp)
                ),
            verticalAlignment = Alignment.Bottom
        ) {
            ContactUs()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SessionsPreview() {
    Sessions(navController = rememberNavController())
}

/*
@Composable
fun YoImageGrid(videoList: List<String>, imageList: List<Int>, navController: NavController) {
    val lazyGridState = rememberLazyGridState()
    var showVideoPlayer by remember { mutableStateOf(false) }
    var currentVideoIndex by remember { mutableStateOf(-1) } // Track the currently selected video index

    if (showVideoPlayer) {
        val itemAtIndex = videoList.getOrNull(currentVideoIndex)
        if (itemAtIndex != null) {
            VideoPlayer(
                videoUrl = itemAtIndex,
                onBackClicked = { // This function is passed as an argument
                    showVideoPlayer = false
                    navController.popBackStack()  // Assuming VideoPlayer is a composable for playing videos
                }
            )
        }
    } else {
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            state = lazyGridState,
            modifier = Modifier.padding(10.dp)
        ) {
            itemsIndexed(items = imageList) { index, imageResource ->
                Image(
                    painter = painterResource(id = imageResource),
                    contentDescription = null,
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier
                        .size(120.dp)
                        .aspectRatio(1f)
                        .clip(RoundedCornerShape(15.dp))
                        .padding(4.dp)
                        .clickable {
                            currentVideoIndex = index // Update the selected video index
                            showVideoPlayer = true      // Trigger video playback
                        }
                )
            }
        }
    }
}
@Composable
fun VideoPlayer(videoUrl: String, onBackClicked: () -> Unit) {
    val exoPlayer: ExoPlayer
    //val playerView: PlayerView
    var showVideoPlayer by remember { mutableStateOf(false) }
    val context: Context = LocalContext.current
    exoPlayer = ExoPlayer.Builder(context).build()
    val mediaItem = androidx.media3.common.MediaItem.fromUri(Uri.parse(videoUrl))

    exoPlayer.setMediaItem(mediaItem)
    exoPlayer.prepare()
    exoPlayer.playWhenReady

    Box(modifier = Modifier) {
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

 */