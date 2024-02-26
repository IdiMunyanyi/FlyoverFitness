package com.flyoverfitness.ui.screens

import android.content.Context
import android.net.Uri
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView


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

    Box( modifier = Modifier.fillMaxWidth().requiredHeight(200.dp)) {
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


