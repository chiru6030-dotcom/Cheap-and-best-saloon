package com.example.components

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.ui.theme.SalonBrass
import com.example.ui.theme.SalonBrassBright
import com.example.ui.theme.SalonBrassDark
import com.example.ui.theme.SalonPaper
import com.example.ui.theme.SalonRed
import com.example.ui.theme.SalonSteel

@Composable
fun BarberPoleStripeRule(modifier: Modifier = Modifier) {
  Canvas(
    modifier = modifier
      .fillMaxWidth()
      .height(6.dp)
      .testTag("barber_stripe_rule")
  ) {
    val stripeWidth = 14.dp.toPx()
    val totalWidth = size.width
    val colors = listOf(SalonRed, SalonPaper, SalonSteel)
    var x = -size.height
    var colorIdx = 0

    while (x < totalWidth + stripeWidth * 2) {
      val color = colors[colorIdx % colors.size]
      val path = Path().apply {
        moveTo(x, size.height)
        lineTo(x + stripeWidth, 0f)
        lineTo(x + stripeWidth * 2f, 0f)
        lineTo(x + stripeWidth, size.height)
        close()
      }
      drawPath(path = path, color = color)
      x += stripeWidth
      colorIdx++
    }
  }
}

@Composable
fun AuthenticBarberPole(
  modifier: Modifier = Modifier,
  poleWidth: Dp = 64.dp,
  poleHeight: Dp = 260.dp
) {
  val infiniteTransition = rememberInfiniteTransition(label = "pole_spin")
  val stripeOffset by infiniteTransition.animateFloat(
    initialValue = 0f,
    targetValue = 100f,
    animationSpec = infiniteRepeatable(
      animation = tween(durationMillis = 2600, easing = LinearEasing),
      repeatMode = RepeatMode.Restart
    ),
    label = "stripe_offset"
  )

  Column(
    modifier = modifier
      .semantics { contentDescription = "Authentic illuminated barbershop pole rotating cylinder" }
      .testTag("barber_pole_animated"),
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    // Top Brass Finial Globe
    Box(
      modifier = Modifier
        .size(poleWidth * 0.45f)
        .clip(CircleShape)
        .background(
          Brush.radialGradient(
            colors = listOf(SalonBrassBright, SalonBrass, SalonBrassDark),
            center = Offset(30f, 20f)
          )
        )
    )

    // Top Brass Cap
    Box(
      modifier = Modifier
        .offset(y = (-4).dp)
        .width(poleWidth * 1.15f)
        .height(18.dp)
        .clip(RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp, bottomStart = 4.dp, bottomEnd = 4.dp))
        .background(
          Brush.verticalGradient(
            colors = listOf(SalonBrassBright, SalonBrass, SalonBrassDark)
          )
        )
    )

    // Rotating Barber Pole Glass Cylinder
    Box(
      modifier = Modifier
        .width(poleWidth)
        .height(poleHeight)
        .shadow(12.dp, shape = RoundedCornerShape(20.dp))
        .clip(RoundedCornerShape(18.dp))
        .background(Color.White)
    ) {
      Canvas(modifier = Modifier.matchParentSize()) {
        val stripeCycle = 42.dp.toPx()
        val colors = listOf(SalonRed, SalonPaper, SalonSteel)
        val bandHeight = stripeCycle / colors.size
        val totalVertical = size.height + stripeCycle * 3
        val currentOffset = (stripeOffset / 100f) * stripeCycle

        // Draw diagonal candy stripes
        var yPos = -stripeCycle + currentOffset
        var index = 0
        while (yPos < totalVertical) {
          val color = colors[index % colors.size]
          val path = Path().apply {
            moveTo(0f, yPos)
            lineTo(size.width, yPos - size.width * 0.6f)
            lineTo(size.width, yPos - size.width * 0.6f + bandHeight)
            lineTo(0f, yPos + bandHeight)
            close()
          }
          drawPath(path, color = color)
          yPos += bandHeight
          index++
        }

        // Glass cylinder 3D lighting reflection & edge shadow
        drawRect(
          brush = Brush.horizontalGradient(
            colors = listOf(
              Color.Black.copy(alpha = 0.45f),
              Color.Transparent,
              Color.White.copy(alpha = 0.55f),
              Color.Transparent,
              Color.Black.copy(alpha = 0.45f)
            )
          )
        )
      }
    }

    // Bottom Brass Cap
    Box(
      modifier = Modifier
        .offset(y = (-4).dp)
        .width(poleWidth * 1.15f)
        .height(18.dp)
        .clip(RoundedCornerShape(bottomStart = 8.dp, bottomEnd = 8.dp, topStart = 4.dp, topEnd = 4.dp))
        .background(
          Brush.verticalGradient(
            colors = listOf(SalonBrassBright, SalonBrass, SalonBrassDark)
          )
        )
    )

    // Bottom Brass Knob
    Box(
      modifier = Modifier
        .offset(y = (-4).dp)
        .size(poleWidth * 0.35f)
        .clip(CircleShape)
        .background(
          Brush.radialGradient(
            colors = listOf(SalonBrassBright, SalonBrass, SalonBrassDark),
            center = Offset(20f, 15f)
          )
        )
    )

    // Drop Shadow on Floor
    Box(
      modifier = Modifier
        .offset(y = 2.dp)
        .width(poleWidth * 1.4f)
        .height(10.dp)
        .background(
          Brush.radialGradient(
            colors = listOf(Color.Black.copy(alpha = 0.5f), Color.Transparent)
          ),
          shape = CircleShape
        )
    )
  }
}
