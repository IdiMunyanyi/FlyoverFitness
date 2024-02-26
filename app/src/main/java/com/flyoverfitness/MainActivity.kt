package com.flyoverfitness

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.PlayArrow
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerIcon.Companion.Text
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.flyoverfitness.presentation.data.FitnessDB
import com.flyoverfitness.presentation.repository.Repository
import com.flyoverfitness.presentation.sign_in.GoogleAuthUiClient
import com.flyoverfitness.presentation.sign_in.SignInScreen
import com.flyoverfitness.presentation.sign_in.SignInViewModel
import com.flyoverfitness.presentation.viewmodel.FitnessViewModel
import com.flyoverfitness.ui.screens.Home
import com.flyoverfitness.ui.screens.Profile
import com.flyoverfitness.ui.screens.Screens
import com.flyoverfitness.ui.screens.Sessions
import com.flyoverfitness.ui.screens.VideoPlayer
import com.flyoverfitness.ui.screens.YoMedals
import com.flyoverfitness.ui.theme.Bob
import com.flyoverfitness.ui.theme.FlyoverFitnessTheme
import com.flyoverfitness.ui.theme.Purple40
import com.flyoverfitness.ui.theme.PurpleGrey40
import com.google.android.gms.auth.api.identity.Identity
import kotlinx.coroutines.launch


class MainActivity : ComponentActivity() {

    private val googleAuthUiClient by lazy {
        GoogleAuthUiClient(
            context = applicationContext,
            oneTapClient = Identity.getSignInClient(applicationContext)
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FlyoverFitnessTheme {
                Surface(
                    color = Color.DarkGray,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(0.dp)
                ) {
                    //YoBottomBar()
                    val context = LocalContext.current
                    val db = FitnessDB.getDatabase(context)
                    val repository = Repository(db)
                    val myViewModel = FitnessViewModel(repository)
                    val navController = rememberNavController()
                    val viewModel = viewModel<SignInViewModel>()
                    val state by viewModel.state.collectAsState()
                    var showBottomBar by remember {
                        mutableStateOf(false)
                    }
                    if (showBottomBar) {
                        MyApp(navController = navController, viewModel = myViewModel)
                    } else {

                        LaunchedEffect(key1 = Unit) {
                            if (googleAuthUiClient.getSignedInUser() != null) {
                                //navController.navigate("Home")
                                showBottomBar = true
                            }
                        }
                        val launcher = rememberLauncherForActivityResult(
                            contract = ActivityResultContracts.StartIntentSenderForResult(),
                            onResult = { result ->
                                if (result.resultCode == RESULT_OK) {
                                    lifecycleScope.launch {
                                        val signInResult = googleAuthUiClient.SignInWithIntent(
                                            intent = result.data ?: return@launch
                                        )
                                        viewModel.onSignInResult(signInResult)
                                    }
                                }
                            }
                        )

                        LaunchedEffect(key1 = state.isSignInSuccessfull) {
                            if (state.isSignInSuccessfull) {
                                // navController.navigate("Home")
                                showBottomBar = true
                                viewModel.resetState()
                            }
                        }

                        SignInScreen(
                            state = state,
                            onSignInClick = {
                                lifecycleScope.launch {
                                    val signInIntentSender = googleAuthUiClient.signIn()
                                    launcher.launch(
                                        IntentSenderRequest.Builder(
                                            signInIntentSender ?: return@launch
                                        ).build()
                                    )
                                }
                            }
                        )
                    }
                }
            }
        }
    }

    @Composable
    fun MyApp(navController: NavHostController, viewModel: FitnessViewModel) {

        val selected = remember {
            mutableStateOf(Icons.Default.Home)
        }

        Scaffold(
            bottomBar = {
                // Display the Bottom App Bar when signed in
                BottomAppBar(
                    containerColor = Color.Black,
                    modifier = Modifier.background(color = Color.DarkGray),
                    actions = {
                        IconButton(
                            onClick = {
                                selected.value = Icons.Outlined.Home
                                navController.navigate(Screens.Home.screen) {
                                    popUpTo(0)
                                }
                            },
                            modifier = Modifier.weight(1f)
                        ) {
                            Icon(
                                Icons.Outlined.Home,
                                contentDescription = null,
                                modifier = Modifier.size(32.dp),
                                tint = if (selected.value == Icons.Outlined.Home) Color.White else Color.Gray
                            )
                        }
                        IconButton(
                            onClick = {
                                selected.value = Icons.Outlined.Star
                                navController.navigate(Screens.YoMedals.screen) {
                                    popUpTo(0)
                                }
                            },
                            modifier = Modifier.weight(1f)
                        ) {
                            Icon(
                                Icons.Outlined.Star,
                                contentDescription = null,
                                modifier = Modifier.size(32.dp),
                                tint = if (selected.value == Icons.Outlined.Star) Color.White else Color.Gray
                            )
                        }
                        IconButton(
                            onClick = {
                                selected.value = Icons.Outlined.Info
                                navController.navigate(Screens.Sessions.screen) {
                                    popUpTo(0)
                                }
                            },
                            modifier = Modifier.weight(1f)
                        ) {
                            Icon(
                                Icons.Outlined.Info,
                                contentDescription = null,
                                modifier = Modifier.size(32.dp),
                                tint = if (selected.value == Icons.Outlined.Info) Color.White else Color.Gray
                            )
                        }
                        IconButton(
                            onClick = {
                                selected.value = Icons.Outlined.AccountCircle
                                navController.navigate(Screens.Profile.screen) {
                                    popUpTo(0)
                                }
                            },
                            modifier = Modifier.weight(1f)
                        ) {
                            Icon(
                                Icons.Outlined.AccountCircle,
                                contentDescription = null,
                                modifier = Modifier.size(32.dp),
                                tint = if (selected.value == Icons.Outlined.AccountCircle) Color.White else Color.Gray
                            )
                        }
                    }
                )
            },
            content = { innerPadding ->

                NavHost(
                    navController = navController,
                    startDestination = Screens.Home.screen,
                    modifier = Modifier.padding(paddingValues = innerPadding)
                ) {
                    composable(Screens.Home.screen) { Home(navController) }
                    composable(Screens.Sessions.screen) { Sessions(navController) }
                    composable(Screens.YoMedals.screen) { YoMedals(navController) }
                    composable(Screens.Profile.screen) {
                        Profile(
                            userData = googleAuthUiClient.getSignedInUser(),
                            onSignOut = {
                                lifecycleScope.launch {
                                    googleAuthUiClient.signOut()
                                    Toast.makeText(
                                        applicationContext,
                                        "Signed Out",
                                        Toast.LENGTH_LONG
                                    ).show()
                                    navController.navigate("signIn") {
                                        // Pop up to the start destination of the graph to
                                        // avoid building up a stack of the same destination
                                        popUpTo("home") { inclusive = true }
                                    }
                                }
                            },
                            viewModel = viewModel
                        )
                    }
                }
            }
        )
    }


    @Composable
    fun MyBar(navController: NavHostController, viewModel: FitnessViewModel) {

        val selected = remember {
            mutableStateOf(Icons.Default.Home)
        }

        Scaffold(
            bottomBar = {
                // Display the Bottom App Bar when signed in
                BottomAppBar(
                    containerColor = Color.Black,
                    modifier = Modifier.background(color = Color.DarkGray),
                    actions = {
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .padding(16.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            IconButton(onClick = {
                                selected.value = Icons.Default.PlayArrow
                                navController.navigate(Screens.Sessions.screen) {
                                    popUpTo(0)
                                }
                            }) {
                                Icon(
                                    Icons.Default.Home,
                                    contentDescription = null,
                                    modifier = Modifier.size(26.dp),
                                    tint = Bob
                                )
                            }
                            Text(text = "Home")
                        }
                        IconButton(
                            onClick = {
                                selected.value = Icons.Default.Home
                                navController.navigate(Screens.Home.screen) {
                                    popUpTo(0)
                                }
                            },
                            modifier = Modifier.weight(1f)
                        ) {
                            Icon(
                                Icons.Default.Home,
                                contentDescription = null,
                                modifier = Modifier.size(32.dp),
                                tint = if (selected.value == Icons.Default.Home) Color.White else Color.Gray
                            )
                        }
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .padding(16.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            FloatingActionButton(onClick = {
                                selected.value = Icons.Default.Info
                                navController.navigate(Screens.Sessions.screen) {
                                    popUpTo(0)
                                }
                            }) {
                                Icon(
                                    Icons.Default.Info,
                                    contentDescription = null,
                                    modifier = Modifier.size(36.dp),
                                    tint = Bob
                                )
                            }
                            Text(text = "Sessions")
                        }
                        IconButton(
                            onClick = {
                                selected.value = Icons.Default.AccountCircle
                                navController.navigate(Screens.Profile.screen) {
                                    popUpTo(0)
                                }
                            },
                            modifier = Modifier.weight(1f)
                        ) {
                            Icon(
                                Icons.Default.AccountCircle,
                                contentDescription = null,
                                modifier = Modifier.size(32.dp),
                                tint = if (selected.value == Icons.Default.AccountCircle) Color.White else Color.Gray
                            )
                        }
                    }
                )
            },
            content = { innerPadding ->

                NavHost(
                    navController = navController,
                    startDestination = Screens.Home.screen,
                    modifier = Modifier.padding(paddingValues = innerPadding)
                ) {
                    composable(Screens.Home.screen) { Home(navController) }
                    composable(Screens.Sessions.screen) { Sessions(navController) }
                    composable(Screens.YoMedals.screen) { YoMedals(navController) }
                    composable(Screens.VideoPlayer.screen) {

                        var videoUrl by remember { mutableStateOf("") }
                        var showVideoPlayer by remember { mutableStateOf(false) }
                        if (showVideoPlayer) {
                            VideoPlayer(
                                videoUrl = videoUrl,
                                onBackClicked = { // This function is passed as an argument
                                    showVideoPlayer = false
                                    navController.popBackStack()  // Assuming VideoPlayer is a composable for playing videos
                                })
                        }
                    }
                    composable(Screens.Profile.screen) {
                        Profile(
                            userData = googleAuthUiClient.getSignedInUser(),
                            onSignOut = {
                                lifecycleScope.launch {
                                    googleAuthUiClient.signOut()
                                    Toast.makeText(
                                        applicationContext,
                                        "Signed Out",
                                        Toast.LENGTH_LONG
                                    ).show()
                                    navController.navigate("signIn") {
                                        // Pop up to the start destination of the graph to
                                        // avoid building up a stack of the same destination
                                        popUpTo("home") { inclusive = true }
                                    }
                                }
                            },
                            viewModel = viewModel
                        )
                    }
                }
            }
        )
    }
}