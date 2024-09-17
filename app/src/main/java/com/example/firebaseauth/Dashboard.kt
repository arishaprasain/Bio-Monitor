package com.example.firebaseauth

import androidx.benchmark.perfetto.Row
import androidx.compose.foundation.Image
import androidx.compose.foundation.MutatePriority
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.firebaseauth.navigation.Routes



@Composable
fun Dashboard(navController: NavController, viewModel: SensorViewModel = viewModel()) {
    // Observe the sensor data from the ViewModel
    val bpm by viewModel.bpm
    val spo2 by viewModel.spo2

    // Observe historical data for plotting
    val bpmHistory = viewModel.bpmHistory.toList()  // Convert to immutable list
    val spo2History = viewModel.spo2History.toList()  // Convert to immutable list


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Header with "Guest Dashboard" and profile icon
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Your Dashboard",
                //style = MaterialTheme.typography.h5,
                fontWeight = FontWeight.Bold
            )
            // Circular profile icon
            Image(
                painter = painterResource(id = R.drawable.ic_profile_placeholder),
                contentDescription = "Profile Icon",
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .border(2.dp, Color.Gray, CircleShape)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Card for displaying live BPM and SpO2
        Card(
            modifier = Modifier.fillMaxWidth(),
            // elevation = 4.dp,
            shape = RoundedCornerShape(12.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Live Data",
                    //style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    horizontalArrangement = Arrangement.SpaceAround,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(text = "BPM", fontWeight = FontWeight.Bold)
                        Text(text = "$bpm BPM")
                    }
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(text = "SpO2", fontWeight = FontWeight.Bold)
                        Text(text = "$spo2 %")
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        // "Measure Vitals" section
        Text(
            text = "Measure Vitals",
            //style = MaterialTheme.typography.h6,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp
        )

        Spacer(modifier = Modifier.height(30.dp))

        //Buttons for vitals
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            VitalButton(navController, "Temperature", R.drawable.ic_temperature)
            VitalButton(navController, "Pulse Oximeter", R.drawable.ic_pulse_oximeter)
        }

        Spacer(modifier = Modifier.height(30.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            VitalButton(navController, "Heart Beat", R.drawable.ic_heart_beat)
            VitalButton(navController, "ECG", R.drawable.ic_ecg)
        }
    }

}


// Custom Button for vitals
@Composable
fun VitalButton(navController : NavController, vitalName: String, iconId: Int) {

    val route: String
    val draw : Int

    if (vitalName == "Temperature") {
        route = Routes.SpO2
        draw = R.drawable.ic_temperature

    } else if (vitalName == "Pulse Oximeter") {
        route = Routes.SpO2
        draw = R.drawable.ic_pulse_oximeter
    } else if (vitalName == "Heart Beat") {
        route = Routes.HeartBeat
        draw = R.drawable.ic_heart_beat
    } else if (vitalName == "ECG") {
        route = Routes.HeartBeat
        draw = R.drawable.ic_ecg
    } else {
        route = Routes.HeartBeat // fallback route
        draw = R.drawable.ic_heart_beat
    }

    Column(horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.wrapContentWidth()
    ) {
        Image(
            painter = painterResource(id = draw),
            contentDescription = "$vitalName Icon",
            modifier = Modifier
                .size(70.dp)
                .clip(CircleShape)
                .border(1.dp, Color.Gray, CircleShape)
        )

        Spacer(modifier = Modifier.height(10.dp))

        Button(
            onClick = { navController.navigate(route) },
            modifier = Modifier
                .size(120.dp)
                .padding(vertical = 10.dp)
                .wrapContentWidth()
                .wrapContentSize()
                .align(Alignment.CenterHorizontally)


        ) {
            Text(
                text = vitalName,
                fontSize = 10.sp,
                color = Color.White, // Assuming the button has a background color, white text for visibility
                fontWeight = FontWeight.Bold

            )
        }
    }
}


    @Composable
    fun HeartRateHistoryScreen(viewModel: SensorViewModel = viewModel()) {
        // Observe bpmHistory for graph data
        val bpmHistory = viewModel.bpmHistory.toList()


        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(brush = Brush.verticalGradient(
                    colors = listOf(Color(0xFFEDE7F6), Color(0xFFBBDEFB))
                ))
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Title
            Text(
                text = "Live Heart Beat Rate",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 16.dp),
                color = Color(0xFF000000)
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Card for the Line Chart
            Card(
               // elevation = 8.dp,
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                Column(
                    modifier = Modifier
                        .background(Color.White)
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Line Chart",
                        fontSize = 16.sp,
                        color = Color.Gray
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Display the Heart Rate LineChart
                    LineChart(
                        data = bpmHistory,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            HeartRateStatistics(viewModel.bpmLst)
        }
    }

    @Composable
    fun SpO2HistoryScreen(viewModel: SensorViewModel = viewModel()) {// Observe spo2History for graph data
        val spo2History = viewModel.spo2History.toList()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(brush = Brush.verticalGradient(
                    colors = listOf(Color(0xFFEDE7F6), Color(0xFFBBDEFB))
                ))
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Title
            Text(
                text = "Live SpO2 Levels",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 16.dp),
                color = Color(0xFF000000)
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Card for the Line Chart
            Card(
                // elevation = 8.dp,
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                Column(
                    modifier = Modifier
                        .background(Color.White)
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Line Chart",
                        fontSize = 16.sp,
                        color = Color.Gray
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Display the SpO2 LineChart
                    LineChart(
                        data = spo2History,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Spo2Statistics(viewModel.spo2Lst)
        }
    }

@Composable
fun HeartRateStatistics(bpmLst: List<Float>, viewModel: SensorViewModel = viewModel()) {
    val averageBpm = bpmLst.average()
    val maxBpm = bpmLst.maxOrNull() ?: 0f
    val minBpm = bpmLst.minOrNull() ?: 0f

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(text = "Average BPM: ${"%.1f".format(averageBpm)}", fontSize = 16.sp)
        Text(text = "Max BPM: ${maxBpm.toInt()}", fontSize = 16.sp)
        Text(text = "Min BPM: ${minBpm.toInt()}", fontSize = 16.sp)
    }
}

@Composable
fun Spo2Statistics(spo2Lst: List<Float>, viewModel: SensorViewModel = viewModel()) {
    val averageSpO2 = spo2Lst.average()
    val maxSpO2 = spo2Lst.maxOrNull() ?: 0f
    val minSpO2 = spo2Lst.minOrNull() ?: 0f

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(text = "Average SpO2: ${"%.1f".format(averageSpO2)}", fontSize = 16.sp)
        Text(text = "Max SpO2: ${maxSpO2.toInt()}", fontSize = 16.sp)
        Text(text = "Min SpO2: ${minSpO2.toInt()}", fontSize = 16.sp)
    }
}

@Preview(showSystemUi = true)
@Composable
fun Dashboard(){
    // Create a dummy NavController for the preview
    val navController = rememberNavController()
    val viewModel : SensorViewModel = viewModel()
    Dashboard(navController, viewModel)
}