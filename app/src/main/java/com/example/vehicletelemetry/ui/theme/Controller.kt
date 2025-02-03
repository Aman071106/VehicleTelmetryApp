package com.example.vehicletelemetry.ui.theme

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.vehicletelemetry.screens.Analytics
import com.example.vehicletelemetry.screens.HomeScreen
import com.example.vehicletelemetry.screens.LoginScreen
import com.example.vehicletelemetry.screens.SettingsScreen

@Composable
fun MyApp(drawerController:()->Unit, PgController:androidx.navigation.NavHostController){
    val w= LocalConfiguration.current.screenWidthDp
    val h= LocalConfiguration.current.screenHeightDp
    Log.d("Print dim","height=${h} and width=${w}")
//    val PgController= rememberNavController()
    NavHost(navController = PgController, startDestination ="LoginScreen" ) {
        composable(route = "LoginScreen") {
            LoginScreen(h,w,PgController)
        }
        composable(route = "HomeScreen") {
            HomeScreen(h,w,drawerController)
        }
        composable(route = "AnalyticsScreen") {
            Analytics(h,w,drawerController)
        }
        composable(route = "SettingsScreen") {
            SettingsScreen(h,w,drawerController)
        }

    }
}