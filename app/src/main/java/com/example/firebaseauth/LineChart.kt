package com.example.firebaseauth

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import kotlin.math.roundToInt



@Composable
fun LineChart(
    data: List<Pair<Float, Float>> = emptyList(),
    modifier: Modifier = Modifier
) {
    val spacing = 50f
    val graphColor = Color.Cyan
    val axisColor = Color.Black
    val transparentGraphColor = remember { graphColor.copy(alpha = 0.5f) }
    val maxValue = data.maxOfOrNull { it.second } ?: 0f
    val minValue = data.minOfOrNull { it.second } ?: 0f
    val valueRange = maxValue - minValue
    val density = LocalDensity.current

    val textPaint = remember(density) {
        android.graphics.Paint().apply {
            color = android.graphics.Color.BLACK  // Changed to black
            textAlign = android.graphics.Paint.Align.CENTER
            textSize = density.run { 12.sp.toPx() }
        }
    }

    Canvas(modifier = modifier) {
        val height = size.height
        val width = size.width

        // Draw x-axis and y-axis labels
        drawAxisLabels(data, spacing, valueRange, maxValue, minValue, height, width, textPaint)

        // Draw line chart
        val path = Path().apply {
            if (data.isNotEmpty()) {
                val stepX = (width - 2 * spacing) / (data.size - 1)
                val stepY = (height - 2 * spacing) / valueRange

                moveTo(
                    spacing,
                    height - (data[0].second - minValue) * stepY - spacing
                )
                data.drop(1).forEachIndexed { index, (_, y) ->
                    lineTo(
                        spacing + (index + 1) * stepX,
                        height - (y - minValue) * stepY - spacing
                    )
                }
            }
        }

        drawPath(
            path = path,
            color = graphColor,
            style = Stroke(
                width = 2.dp.toPx(),
                cap = StrokeCap.Round
            )
        )

        val fillPath = Path().apply {
            addPath(path)
            lineTo(width - spacing, height - spacing)
            lineTo(spacing, height - spacing)
            close()
        }

        drawPath(
            path = fillPath,
            brush = Brush.verticalGradient(
                colors = listOf(
                    transparentGraphColor,
                    Color.Transparent
                ),
                endY = height - spacing
            )
        )
    }
}

private fun DrawScope.drawAxisLabels(
    data: List<Pair<Float, Float>>,
    spacing: Float,
    valueRange: Float,
    maxValue: Float,
    minValue: Float,
    height: Float,
    width: Float,
    textPaint: android.graphics.Paint
) {
    val stepX = (width - 2 * spacing) / (data.size - 1)
    val stepY = (height - 2 * spacing) / valueRange

    // Draw x-axis labels
    data.forEachIndexed { index, (x, _) ->
        val labelInterval = 10  // Show every 2nd label
        if (index % labelInterval == 0) {
            drawContext.canvas.nativeCanvas.apply {
                drawText(
                    x.toString(),
                    spacing + index * stepX,
                    height - spacing + 15.dp.toPx(),  // Adjust label position
                    textPaint
                )
            }
        }
    }



    // Draw SpO2 and Heart Rate values on the chart
    data.forEach { (x, value) ->
        drawContext.canvas.nativeCanvas.apply {
            drawText(
                value.roundToInt().toString(),
                spacing + x * stepX,
                height - (value - minValue) * stepY - spacing - 10.dp.toPx(),  // Adjust label position
                textPaint
            )
        }
    }
}


@Preview(showSystemUi = true)
@Composable
fun LineChart(){
    val data: List<Pair<Float, Float>> = emptyList()
    val modifier: Modifier = Modifier
    LineChart(data, modifier)
}