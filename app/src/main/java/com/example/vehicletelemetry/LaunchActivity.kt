package com.example.vehicletelemetry

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Analytics
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowCompat
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.vehicletelemetry.retrofitModule.RequestInterface
import com.example.vehicletelemetry.screens.HomeScreen
import com.example.vehicletelemetry.screens.LoginScreen
import com.example.vehicletelemetry.ui.theme.DrawerItem
import com.example.vehicletelemetry.ui.theme.MyApp
import com.example.vehicletelemetry.ui.theme.VehicleTelemetryTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject


@AndroidEntryPoint
class LaunchActivity : ComponentActivity() {

    /*Hilt Test
    //    @Inject
    //    lateinit var testDependency: TestDependency
    */

//    @Inject
//    lateinit var apiobj:RequestInterface

    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        /*Testing firebase setup
        // Initialize Firebase Database
        val database = FirebaseDatabase.getInstance().getReference("telemetry")

        // Fetch data from Firebase
        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                // Log the raw data for debugging
                Log.d("FirebaseData", "Fetched raw data: ${snapshot.value}")
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle error
                Log.e("FirebaseData", "Error fetching data: ${error.message}")
            }
        })
            */
        /*Test hilt
//        Log.d("HiltTest", "check 1")
//        Log.d("HiltTest", testDependency.sayHello())

         */

        /*
            //retrofit test
    //        GlobalScope.launch {
    //            val response=apiobj.getLastTrackOfVehicle()
    //            Log.d("apiTest", response.body().toString())
    //        }
            */

        setContent {
            val h = LocalConfiguration.current.screenHeightDp
            val w = LocalConfiguration.current.screenWidthDp
            val scalex = w.toFloat() / 1333
            val scaley = h.toFloat() / 728
            VehicleTelemetryTheme {
                val drawerItems = listOf(
                    DrawerItem(
                        "Overview", Icons.Filled.Home, route = "HomeScreen"
                    ),
                    DrawerItem(
                        "Analytics", Icons.Filled.Analytics, route = "AnalyticsScreen"
                    ), DrawerItem(
                        "Settings", Icons.Filled.Settings, route = "SettingsScreen"
                    )
                )
                val navController = rememberNavController()
                val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
                val scope = rememberCoroutineScope()
                HideSystemBars()
                ModalNavigationDrawer(
                    modifier = Modifier.background(Color.Transparent),
                    drawerState = drawerState,
                    scrimColor = Color.Transparent,

                    drawerContent = {
                        ModalDrawerSheet(
                            drawerContainerColor = Color(0xFF432213),
                            modifier = Modifier
                                .width(300.dp * scalex)
                                .background(Color.Transparent)

                        ) {
                            Box(
                                modifier = Modifier.padding(
                                    start = 20.dp * scalex,
                                    top = 60.dp * scaley
                                )
                            ) {
                                Text(
                                    "VEHICLE TELEMETRY APP",
                                    color = Color.White,
                                    fontSize = 30.sp * scalex,
                                    letterSpacing = 1.8.sp,
                                    lineHeight = 40.sp * scaley,
                                    fontWeight = FontWeight.W900
                                )

                            }
                            Spacer(modifier = Modifier.height(50.dp * scaley))
                            LazyColumn() {
                                items(drawerItems.size) { item ->
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .height(80.dp * scaley)
                                            .clickable {
                                                scope.launch { drawerState.close() }
                                                navController.navigate(drawerItems[item].route)
                                            }
                                    ) {
                                        Spacer(Modifier.width(30.dp * scalex))
                                        Image(
                                            drawerItems[item].icon, contentDescription = null,
                                            modifier = Modifier.size(30.dp * scalex),
                                            colorFilter = ColorFilter.tint(Color.White)
                                        )
                                        Spacer(modifier = Modifier.width(20.dp * scalex))
                                        Text(
                                            drawerItems[item].name,
                                            fontWeight = FontWeight.W500,
                                            color = Color.White,
                                            fontSize = 20.sp * scalex
                                        )
                                    }
                                }
                            }

                        }
                    }
                ) {
                    Scaffold() { padding ->

                        Box(Modifier.padding(padding)) {

                            MyApp({ DrawerControllerOpen(scope, drawerState) }, navController)
                        }
                    }
                }
            }
        }
    }
}

//define drawer on click
fun DrawerControllerOpen(scope: CoroutineScope, drawerState: DrawerState) {
    scope.launch {
        drawerState.apply {
            if (isClosed) open() else close()
        }
    }
}

//Bar setup

@Composable
fun HideSystemBars() {
    val systemUiController = rememberSystemUiController()
    val context = LocalContext.current
    val view = LocalView.current

    DisposableEffect(Unit) {
        val window = (context as? Activity)?.window
        if (window != null) {
            // Set the decor fits system windows to false
            WindowCompat.setDecorFitsSystemWindows(window, false)

            // Hide system bars persistently
            view.systemUiVisibility = (
                    View.SYSTEM_UI_FLAG_FULLSCREEN
                            or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                            or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    )
        }

        onDispose {
            // Optionally reset system bars visibility
            window?.decorView?.systemUiVisibility = View.SYSTEM_UI_FLAG_VISIBLE
        }
    }
}

/*testing hilt class
class TestDependency @Inject constructor() {
    fun sayHello(): String = "Hilt is working!"
}
 */

