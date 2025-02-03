package com.example.vehicletelemetry.screens


import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Person2
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ScaleFactor
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.vehicletelemetry.Backend.Telemetry
import com.example.vehicletelemetry.R
import com.example.vehicletelemetry.Viewmodels.TelemetryViewModel
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapType
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import kotlin.math.roundToInt


val poppinFont = FontFamily(Font(R.font.poppins_med))
val Dashboardfontfamily = FontFamily((Font(R.font.dashboard_4)))
val colorBox = Color(0xFFAF4B1D)


//@Preview(
//    name = "Landscape prev",
//    showBackground = true,
//    widthDp = 740,
//    heightDp = 360,
//    backgroundColor = 0xFF000000
//)
@Composable
fun HomeScreen(h: Int = 307, w: Int = 763,drawerController:()->Unit) {

    val telemetryViewModel: TelemetryViewModel = hiltViewModel()
    val fetchedData = telemetryViewModel.telemetry.collectAsState().value


    val scaleFactor =
        ScaleFactor(scaleX = (w.toFloat() / 763), scaleY = (h.toFloat() / 307).toFloat())
    Scaffold() { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(color = MaterialTheme.colorScheme.background)
        ) {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = 20.dp * scaleFactor.scaleX,
                        vertical = 15.dp * scaleFactor.scaleY
                    )
            ) {

                //homepage section
                Box(
                    Modifier
                        .padding(horizontal = 10.dp * scaleFactor.scaleX)
                        .fillMaxWidth()
                        .height(40.dp * scaleFactor.scaleY)
                        .border(width = 0.3.dp * scaleFactor.scaleX, color = Color(0xFFF99F01))
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Menu,
                        contentDescription = null,
                        Modifier
                            .width(30.dp * scaleFactor.scaleX)
                            .height(20.dp * scaleFactor.scaleY)
                            .padding(4.dp)
                            .align(Alignment.CenterStart)
                            .fillMaxSize()
                            .clickable {
                                drawerController()
                            },
                    )
                    Text(
                        "Home Page",
                        color = Color.White,
                        fontSize = 26.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = poppinFont,
                        modifier = Modifier.align(
                            Alignment.Center
                        )
                    )
                    Row(
                        modifier = Modifier
                            .align(Alignment.CenterEnd)
                            .padding(
                                top = 7.dp * scaleFactor.scaleY,
                                bottom = 7.dp * scaleFactor.scaleY
                            )
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Notifications,
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier
                                .background(
                                    shape = RoundedCornerShape(20.dp * scaleFactor.scaleX),
                                    color = Color(0xFFF99F01)
                                )
                                .padding(4.dp * scaleFactor.scaleX)
                        )
                        Box(Modifier.width(20.dp * scaleFactor.scaleX))
                        Icon(
                            imageVector = Icons.Outlined.Person2,
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier
                                .background(
                                    shape = RoundedCornerShape(4.dp * scaleFactor.scaleX),
                                    color = Color(0xFFF9E9E9E)
                                )
                                .padding(4.dp * scaleFactor.scaleX)
                        )
                        Box(Modifier.width(20.dp * scaleFactor.scaleX))
                    }
                }

                //measures section
                Row(
                    modifier = Modifier
                        .padding(
                            top = 10.dp * scaleFactor.scaleY,
                            bottom = 6.dp * scaleFactor.scaleY
                        )
                        .align(Alignment.CenterHorizontally)
                ) {
                    //battery
                    Box(
                        modifier = Modifier
                            .width(100.dp * scaleFactor.scaleX)
                            .height(70.dp * scaleFactor.scaleY)
                            .padding(horizontal = 8.dp * scaleFactor.scaleX)
                            .clip(RoundedCornerShape(4.dp * scaleFactor.scaleX))
                            .background(color = colorBox)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(4.dp * scaleFactor.scaleX),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text("Battery", color = Color.White)
                            Text("${fetchedData.battery.roundToInt()}%", color = Color.White)
                        }
                    }
                    //speed
                    Box(
                        modifier = Modifier
                            .width(100.dp * scaleFactor.scaleX)
                            .height(55.dp * scaleFactor.scaleY)
                            .padding(horizontal = 8.dp * scaleFactor.scaleX)
                            .clip(RoundedCornerShape(4.dp * scaleFactor.scaleX))
                            .background(color = colorBox)
                            .align(Alignment.CenterVertically)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(4.dp * scaleFactor.scaleX),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text("Speed", color = Color.White)
                            Text("${fetchedData.speed.roundToInt()} KM/h", color = Color.White)
                        }
                    }
                    //temperature
                    Box(
                        modifier = Modifier
                            .padding(top = 3.dp * scaleFactor.scaleY)
                            .width(180.dp * scaleFactor.scaleX)
                            .height(55.dp * scaleFactor.scaleY)
                            .padding(horizontal = 8.dp * scaleFactor.scaleX)
                            .clip(RoundedCornerShape(4.dp * scaleFactor.scaleX))
                            .background(color = colorBox)
                            .align(Alignment.Top)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(4.dp * scaleFactor.scaleX),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text("TEMPERATURE", color = Color.White)
                            Text("${fetchedData.temperature.roundToInt()}°C", color = Color.White)
                        }
                    }
                    //Tire pressure
                    Box(
                        modifier = Modifier
                            .width(160.dp * scaleFactor.scaleX)
                            .height(55.dp * scaleFactor.scaleY)
                            .padding(horizontal = 8.dp * scaleFactor.scaleX)
                            .clip(RoundedCornerShape(4.dp * scaleFactor.scaleX))
                            .background(color = colorBox)
                            .align(Alignment.Top)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(4.dp * scaleFactor.scaleX),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text("Tire Pressure", color = Color.White)
                            Text(
                                "${fetchedData.tirePressure.roundToInt()} PSI",
                                color = Color.White
                            )
                        }
                    }
                }

                //LastSection
                Row(
                    modifier = Modifier
                        .padding(top = 10.dp * scaleFactor.scaleY)
                        .align(Alignment.CenterHorizontally)
                ) {
                    //map
                    Box(
                        modifier = Modifier
                            .width(150.dp * scaleFactor.scaleX)
                            .height(200.dp * scaleFactor.scaleY)
                            .shadow(
                                100.dp * scaleFactor.scaleX,
                                RoundedCornerShape(16.dp * scaleFactor.scaleX),
                                ambientColor = Color(0xFF64291a),
                                spotColor = Color(0xFF64291a)
                            )
                            .clip(RoundedCornerShape(16.dp * scaleFactor.scaleX))
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(color = Color(0xFF1E1E1E))
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(top = 2.dp * scaleFactor.scaleY)
                            ) {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth(1f)
                                        .background(color = Color(0xFF292929))
                                ) {
                                    Image(
                                        imageVector = Icons.Default.LocationOn,
                                        contentDescription = null,
                                        colorFilter = ColorFilter.tint(Color(0xFFF8810B))
                                    )
                                    Text(
                                        "Lat: ${"%.2f".format(fetchedData.location.latitude)}",
                                        color = Color(0xFFFFFFFF),
                                        fontSize = 13.sp,
                                        fontFamily = Dashboardfontfamily
                                    )
                                }
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth(1f)
                                        .background(color = Color(0xFF292929))
                                ) {
                                    Image(
                                        imageVector = Icons.Default.LocationOn,
                                        contentDescription = null,
                                        colorFilter = ColorFilter.tint(Color(0xFFF8810B))
                                    )
                                    Text(
                                        "Lng: ${"%.2f".format(fetchedData.location.latitude)}",
                                        color = Color(0xFFFFFFFF),
                                        fontSize = 13.sp,
                                        fontFamily = Dashboardfontfamily
                                    )
                                }
                                GoogleMapScreen(27.0, 20.0)
                            }
                        }
                    }
                    Box(Modifier.width(20.dp * scaleFactor.scaleX))
                    //alert and camera
                    Column(modifier = Modifier.align(Alignment.CenterVertically)) {
                        Box(
                            Modifier
                                .fillMaxWidth(0.8f)
                                .height(180.dp * scaleFactor.scaleY)
                                .border(
                                    width = 2.dp * scaleFactor.scaleX,
                                    color = Color(0xFF482F2F)
                                )
                        ) {
                            Column() {
                                Text(
                                    "      Camera View",
                                    fontFamily = poppinFont,
                                    fontWeight = FontWeight.SemiBold
                                )
                                Box(
                                    Modifier
                                        .padding(
                                            start = 10.dp * scaleFactor.scaleX,
                                            end = 10.dp * scaleFactor.scaleX,
                                            bottom = 10.dp * scaleFactor.scaleY
                                        )
                                        .fillMaxWidth()
                                        .height(160.dp * scaleFactor.scaleY)
                                        .clip(RoundedCornerShape(20.dp * scaleFactor.scaleX))
                                        .background(Color(0xFF984C1D))
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

//map


@Composable
fun GoogleMapScreen(latitude: Double = 27.88, longitude: Double = 30.0) {
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(LatLng(latitude, longitude), 15f)
    }
    //custom marker
    val customMarkerBitmap = BitmapFactory.decodeResource(
        LocalContext.current.resources, R.drawable.marker
    )
    // Resize the bitmap (example: set width and height to 100x100)
    val resizedBitmap = Bitmap.createScaledBitmap(customMarkerBitmap, 100, 100, false)

    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState,
        properties = MapProperties(
            mapType = MapType.NORMAL,
            mapStyleOptions = MapStyleOptions(Mapstyle.json)

        ),
        uiSettings = MapUiSettings(

            zoomControlsEnabled = true,
            compassEnabled = false,
            myLocationButtonEnabled = false,
            scrollGesturesEnabledDuringRotateOrZoom = true,
            zoomGesturesEnabled = true,
            scrollGesturesEnabled = false,
            rotationGesturesEnabled = false

        ),

        ) {


        // Marker
        Marker(
            state = MarkerState(LatLng(latitude, longitude)),
            title = "Your Vehicle",
            snippet = null,
            icon = BitmapDescriptorFactory.fromBitmap(resizedBitmap)
        )
    }
}
