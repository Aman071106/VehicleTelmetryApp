package com.example.vehicletelemetry.screens

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.vehicletelemetry.R

val Notosans = FontFamily(Font(R.font.notosans))

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(h: Int = 307, w: Int = 763, pgController: NavController) {
    val scaleX = w.toFloat() / 763
    val scaleY = h.toFloat() / 307
    val username = remember { mutableStateOf("") }
    val pass = remember { mutableStateOf("") }
    val context = LocalContext.current
    Scaffold { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Image(
                painter = painterResource(R.drawable.login_bg),
                contentDescription = null,
                Modifier.fillMaxSize()
            )
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = (20 * scaleX).dp, end = (80 * scaleX).dp)
            ) {
                Text(
                    "Welcome Back!!",
                    fontSize = (40 * scaleX).sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.align(Alignment.CenterVertically)
                )
                Box(modifier = Modifier.width((70 * scaleX).dp))
                Box(
                    modifier = Modifier
                        .border(0.1.dp, color = Color.White, shape = RoundedCornerShape(20.dp))
                        .fillMaxHeight(0.9f)
                        .width((250 * scaleX).dp)
                        .align(Alignment.CenterVertically)
                ) {
                    Column(
                        verticalArrangement = Arrangement.SpaceEvenly,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(start = (15 * scaleX).dp)
                            .align(Alignment.Center)
                    ) {
                        Text(
                            "Login",
                            fontFamily = Notosans,
                            fontSize = (20 * scaleX).sp,
                            fontWeight = FontWeight.ExtraBold,
                            modifier = Modifier.padding(top = (8 * scaleY).dp)
                        )
                        Text(
                            "Glad you’re back!",
                            fontFamily = Notosans,
                            fontSize = (12 * scaleX).sp
                        )
                        Box(
                            modifier = Modifier
                                .padding(
                                    end = (8 * scaleX).dp,
                                    top = (4 * scaleY).dp,
                                    bottom = (4 * scaleY).dp
                                )
                                .border(1.dp, Color.White, RoundedCornerShape(10.dp)),
                        ) {


                            TextField(
                                modifier = Modifier
                                    .height((40 * scaleY).dp)
                                    .fillMaxWidth(),
                                value = username.value,
                                onValueChange = { newValue ->
                                    username.value = newValue
                                },
                                label = { Text("Username", fontSize = (12 * scaleX).sp) },
                                placeholder = { Text("Type ...", fontSize = (12 * scaleX).sp) },
                                colors = TextFieldDefaults.textFieldColors(
                                    focusedTextColor = Color.White,
                                    focusedLabelColor = Color.White,
                                    containerColor = Color.Transparent,
                                    focusedIndicatorColor = Color.Transparent,
                                    unfocusedIndicatorColor = Color.Transparent
                                )
                            )
                        }
                        Box(
                            modifier = Modifier
                                .padding(
                                    end = (8 * scaleX).dp,
                                    top = (4 * scaleY).dp,
                                    bottom = (4 * scaleY).dp
                                )
                                .border(1.dp, Color.White, RoundedCornerShape(10.dp)),
                        ) {

                            TextField(
                                modifier = Modifier
                                    .height((40 * scaleY).dp)
                                    .fillMaxWidth(),
                                value = pass.value,
                                onValueChange = { newPass ->
                                    pass.value = newPass
                                },
                                label = { Text("Password", fontSize = (12 * scaleX).sp) },
                                placeholder = { Text("Type ...", fontSize = (12 * scaleX).sp) },
                                colors = TextFieldDefaults.textFieldColors(
                                    focusedTextColor = Color.White,
                                    focusedLabelColor = Color.White,
                                    containerColor = Color.Transparent,
                                    focusedIndicatorColor = Color.Transparent,
                                    unfocusedIndicatorColor = Color.Transparent
                                )
                            )
                        }
                        Button(
                            modifier = Modifier
                                .padding(end = (10 * scaleX).dp, top = (10 * scaleY).dp)
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(8.dp))
                                .background(
                                    brush = Brush.linearGradient(
                                        colors = listOf(
                                            Color(0xFFFFA662),
                                            Color(0xFFCD6D40),
                                            Color(0xFF752B04)
                                        )
                                    )
                                ), shape = RoundedCornerShape(8.dp),
                            onClick = {
                                if (pass.value.trim() == "1234" && username.value.trim() == "telemetry") {
                                    pgController.navigate("HomeScreen")
                                } else {
                                    Toast.makeText(
                                        context,
                                        "Invalid Username or Password",
                                        Toast.LENGTH_LONG,
                                    ).show()
                                }

                            },
                            colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)
                        ) {
                            Text(
                                "Login",
                                color = Color.White,
                                fontSize = (16 * scaleX).sp,
                                fontFamily = Notosans,
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Center,
                                modifier = Modifier.align(Alignment.CenterVertically)
                            )
                        }
                        Text(
                            "Forgot Password?",
                            Modifier.align(Alignment.CenterHorizontally),
                            fontFamily = Notosans,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = (14 * scaleX).sp
                        )
                        Text(
                            "or",
                            color = Color.Gray,
                            fontFamily = Notosans,
                            modifier = Modifier.align(Alignment.CenterHorizontally)
                        )
                        Text(
                            "Don’t have an account? Signup",
                            fontFamily = Notosans,
                            modifier = Modifier.align(Alignment.CenterHorizontally),
                            fontSize = (12 * scaleX).sp
                        )
                        Image(
                            painter = painterResource(R.drawable.devicon_google),
                            contentDescription = null,
                            modifier = Modifier
                                .size((20 * scaleX).dp)
                                .align(Alignment.CenterHorizontally)
                        )
                    }
                }
            }
        }
    }
}
