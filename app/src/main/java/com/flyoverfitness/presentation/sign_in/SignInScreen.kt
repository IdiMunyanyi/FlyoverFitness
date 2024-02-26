package com.flyoverfitness.presentation.sign_in

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.flyoverfitness.R
import com.flyoverfitness.ui.theme.BobGreen
import com.flyoverfitness.ui.theme.Purple40

@Composable
fun SignInScreen(
    state: SignInState, onSignInClick: ()-> Unit
){
    val context = LocalContext.current
    LaunchedEffect(key1 = state.signInError){
        state.signInError?.let { error ->
            Toast.makeText(context, error, Toast.LENGTH_LONG).show()
        }
    }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(0.dp)
                .background(
                    color = Color.Black,
                    shape = RectangleShape
                ),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .padding(10.dp)
                    .background(
                        color = Purple40,
                        shape = RoundedCornerShape(25.dp)
                    )
            ) {
                Text(
                    text = "Flyover Fitness",
                    color = Color.White,
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(start = 30.dp, end = 30.dp, top = 5.dp, bottom = 5.dp)
                )
            }

            Image(
                painter = painterResource(id = R.drawable.running),
                contentDescription = null,
                modifier = Modifier
                    .padding(top = 2.dp, bottom = 32.dp)
                    .height(150.dp)
                    .clickable { }
            )

            Image(
                painter = painterResource(id = R.drawable.google),
                contentDescription = null,
                modifier = Modifier
                    .padding(10.dp)
                    .requiredSize(50.dp)
                    .clickable {onSignInClick() }
            )
            Button(
                onClick = onSignInClick,
                colors = ButtonDefaults.buttonColors(
                    containerColor = BobGreen)
            ) {
                Text(text = "Sign In with Google")
            }
        }
    }



