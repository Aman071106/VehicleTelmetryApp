package com.example.vehicletelemetry.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import co.yml.charts.axis.AxisData
import co.yml.charts.common.model.Point
import co.yml.charts.ui.linechart.LineChart
import co.yml.charts.ui.linechart.model.GridLines
import co.yml.charts.ui.linechart.model.IntersectionPoint
import co.yml.charts.ui.linechart.model.Line
import co.yml.charts.ui.linechart.model.LineChartData
import co.yml.charts.ui.linechart.model.LinePlotData
import co.yml.charts.ui.linechart.model.LineStyle
import co.yml.charts.ui.linechart.model.SelectionHighlightPoint
import co.yml.charts.ui.linechart.model.SelectionHighlightPopUp
import co.yml.charts.ui.linechart.model.ShadowUnderLine

@Composable
fun Analytics(h: Int, w: Int,drawerController:()->Unit) {
    val scalex = w / 1333
    val scaley = h / 728
    return Scaffold { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
//                .background(color = Color.Blue)
        ) {

            Box(
                modifier = Modifier
                    .padding(start = 66.dp * scalex)
                    .width(2.dp * scalex)
                    .fillMaxHeight()
                    .background(color = Color(0xFF383838))
            )
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                Row(
                    Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Menu,
                        contentDescription = null,
                        Modifier
                            .padding(top = 20.dp * scaley)
                            .width(60.dp * scalex)
                            .height(40.dp * scaley)
                            .padding(4.dp)
                            .clickable {
                            drawerController()
                        },

                    )
                    Box(Modifier.width(20.dp * scalex))
                    Text(
                        modifier = Modifier.padding(
                            top = 20.dp * scaley
                        ),
                        text = "ANALYTICS",
                        fontSize = 50.sp * scalex,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFFB4A6A6)
                    )
                }
                Box(
                    Modifier
                        .padding(10.dp * scaley)
                        .height(2.dp * scaley)
                        .fillMaxWidth()
                        .background(color = Color(0xFF383838))
                )
                Box(
                    modifier = Modifier
                        .height(500.dp * scalex)
                        .width(800.dp * scalex)
                        .align(Alignment.End)
                ) {

                    LineGraph(500 * scaley, 800 * scalex, scaley, scalex)
                }
            }
        }
    }
}

@Composable
fun LineGraph(height: Int, width: Int, scaley: Int, scalex: Int) {
    val pointsData: List<Point> =
        listOf(Point(0f, 40f), Point(1f, 35f), Point(2f, 0f), Point(3f, 60f), Point(4f, 10f))
    val xAxisData =
        AxisData.Builder().axisStepSize(100.dp * scalex).backgroundColor(Color.Transparent)
            .steps(pointsData.size - 1).labelData { i -> (i+1).toString() }
            .labelAndAxisLinePadding(15.dp).build()
    val steps = 14
    val yScale = 10
    val yAxisData = AxisData.Builder().axisStepSize(50.dp * scaley).steps(steps)
        .backgroundColor(Color.Transparent).labelData { i ->
            (i * yScale).toString()
        }.build()
    val lineChartData = LineChartData(
        linePlotData = LinePlotData(
            lines = listOf(
                Line(
                    dataPoints = pointsData,
                    LineStyle(color = Color.White, width = 2f),
                    IntersectionPoint(Color.White),
                    SelectionHighlightPoint(),
                    ShadowUnderLine(color = Color.Transparent),
                    SelectionHighlightPopUp()
                )
            ),
        ),
        xAxisData = xAxisData,
        yAxisData = yAxisData,
        gridLines = GridLines(color = Color.Transparent),
        backgroundColor = Color.Transparent
    )
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center // Ensures everything stays centered
    ) {
        LineChart(
            modifier = Modifier
                .fillMaxWidth()  // Reduces width
                .height(300.dp * scaley), lineChartData = lineChartData.copy(
                xAxisData = xAxisData.copy(
                    axisLabelColor = Color.White,
                    axisLineColor = Color.Transparent,
                    axisStepSize = (100 * scalex).dp // Adjusts axis step size dynamically
                ), yAxisData = yAxisData.copy(
                    axisLabelColor = Color.White,
                    axisLineColor = Color.Transparent,
                    axisStepSize = (50 * scaley).dp
                )
            )
        )
    }

}


