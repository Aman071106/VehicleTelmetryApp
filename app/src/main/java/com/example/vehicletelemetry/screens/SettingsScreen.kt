package com.example.vehicletelemetry.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun SettingsScreen(h: Int , w: Int,drawerController:()->Unit) {
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.Black)
        , contentAlignment = Alignment.Center
    ) {
        Icon(

            imageVector = Icons.Outlined.Menu,
            contentDescription = null,
            Modifier
                .width(100.dp )
                .height(120.dp )
                .padding(4.dp)
                .align(Alignment.CenterStart)
                .fillMaxSize()
                .clickable {
                    drawerController()
                },
        )
        Text("Not Created", color = Color.White)
    }
}