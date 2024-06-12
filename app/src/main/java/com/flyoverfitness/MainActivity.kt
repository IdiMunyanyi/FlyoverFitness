package com.flyoverfitness

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults.containerColor
import androidx.compose.material3.NavigationBarItem
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.flyoverfitness.navigation.NavItem
import com.flyoverfitness.navigation.listOfNavItems
import com.flyoverfitness.presentation.data.FitnessDB
import com.flyoverfitness.presentation.repository.Repository
import com.flyoverfitness.presentation.sign_in.GoogleAuthUiClient
import com.flyoverfitness.ui.screens.SignInScreen
import com.flyoverfitness.presentation.sign_in.SignInViewModel
import com.flyoverfitness.presentation.viewmodel.FitnessViewModel
import com.flyoverfitness.ui.screens.Home
import com.flyoverfitness.ui.screens.Profile
import com.flyoverfitness.ui.screens.Screens
import com.flyoverfitness.ui.screens.Sessions
import com.flyoverfitness.ui.screens.YoMedals
import com.flyoverfitness.ui.theme.FlyoverFitnessTheme
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
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.dark(
                android.graphics.Color.BLACK
            )
        )
        super.onCreate(savedInstanceState)
        setContent {
            FlyoverFitnessTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.inversePrimary
                ) {
                    //YoBottomBar()
                    val context = LocalContext.current
                    val db = FitnessDB.getDatabase(context)
                    val repository = Repository(db)
                    val myViewModel = FitnessViewModel(repository)
                    val navController = rememberNavController()
                    val viewModel = viewModel<SignInViewModel>()
                    val state by viewModel.state.collectAsStateWithLifecycle()
                    var showBottomBar by remember {
                        mutableStateOf(false)
                    }
                    if (showBottomBar) {
                        AppNavigation(viewModel = myViewModel)
                    } else {

                        LaunchedEffect(key1 = Unit) {
                            if (googleAuthUiClient.getSignedInUser() != null) {
                                //navController.navigate("Home")
                                showBottomBar = true
                            } else { navController.navigate("SignInScreen")}
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
                                navController.navigate("Home")
                                //showBottomBar = true
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
    fun AppNavigation(viewModel: FitnessViewModel) {
        val selected = remember { mutableStateOf(Icons.Default.Home) }
        val navController: NavHostController = rememberNavController()
        Scaffold(
            bottomBar = {
                // Display the Bottom App Bar when signed in
                NavigationBar(containerColor = MaterialTheme.colorScheme.primary,) {
                    val navBackStackEntry: NavBackStackEntry? by navController.currentBackStackEntryAsState()
                    val currentDestination: NavDestination? = navBackStackEntry?.destination
                    listOfNavItems.forEach { navItem: NavItem ->
                        NavigationBarItem(
                            selected = currentDestination?.hierarchy?.any { it.route == navItem.route } == true,
                            onClick = {
                                navController.navigate(navItem.route) {
                                    popUpTo(navController.graph.findStartDestination().id) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            },
                            icon = {
                                Icon(
                                    imageVector = navItem.icon,
                                    contentDescription = null
                                )
                            },
                            label = { Text(text = navItem.label) }
                        )
                    }
                }
            },
            content = { innerPadding ->

                NavHost(
                    navController = navController,
                    startDestination = Screens.Home.screen,
                    modifier = Modifier.padding(paddingValues = innerPadding)
                ) {
                    composable(Screens.Home.screen) { Home(navController) }
                    composable(Screens.Sessions.screen) { Sessions(navController) }
                    composable(Screens.Champions.screen) { YoMedals(navController) }
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
                                        //popUpTo("signIn") { inclusive = true }
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