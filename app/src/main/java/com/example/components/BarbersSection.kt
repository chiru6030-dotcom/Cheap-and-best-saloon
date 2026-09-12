package com.example.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.BarberItem
import com.example.data.SalonData
import com.example.ui.theme.SalonBrass
import com.example.ui.theme.SalonBrassBright
import com.example.ui.theme.SalonGold
import com.example.ui.theme.SalonInk
import com.example.ui.theme.SalonInkBorder
import com.example.ui.theme.SalonInkCard
import com.example.ui.theme.SalonInkSoft
import com.example.ui.theme.SalonPaper
import com.example.ui.theme.SalonPaperDim

@Composable
fun BarbersSection(
  onSelectBarberForBooking: (BarberItem) -> Unit,
  modifier: Modifier = Modifier
) {
  Box(
    modifier = modifier
      .fillMaxWidth()
      .background(SalonInkSoft)
      .padding(horizontal = 20.dp, vertical = 36.dp)
      .testTag("barbers_section")
  ) {
    Column {
      Text(
        text = "BEHIND THE CHAIR",
        color = SalonBrassBright,
        fontSize = 12.sp,
        fontWeight = FontWeight.Bold,
        letterSpacing = 1.2.sp
      )
      Spacer(modifier = Modifier.height(6.dp))
      Text(
        text = "The hands customers ask for",
        color = SalonPaper,
        fontSize = 26.sp,
        fontWeight = FontWeight.Bold,
        letterSpacing = 0.5.sp
      )
      Spacer(modifier = Modifier.height(10.dp))
      Text(
        text = "A few names come up again and again in reviews. Ask for them directly, or let any of the team take care of you.",
        color = SalonPaperDim,
        fontSize = 14.sp,
        lineHeight = 21.sp
      )
      Spacer(modifier = Modifier.height(24.dp))

      Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        SalonData.barbers.forEach { barber ->
          BarberCard(
            barber = barber,
            onBookWithBarber = { onSelectBarberForBooking(barber) }
          )
        }
      }
    }
  }
}

@Composable
private fun BarberCard(
  barber: BarberItem,
  onBookWithBarber: () -> Unit
) {
  Box(
    modifier = Modifier
      .fillMaxWidth()
      .clip(RoundedCornerShape(8.dp))
      .background(SalonInk)
      .border(1.dp, SalonInkBorder, RoundedCornerShape(8.dp))
      .testTag("barber_card_${barber.id}")
  ) {
    // Top brass accent bar (matching the web design)
    Box(
      modifier = Modifier
        .fillMaxWidth()
        .height(3.dp)
        .background(SalonBrass)
    )

    Column(
      modifier = Modifier.padding(20.dp)
    ) {
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Row(
          verticalAlignment = Alignment.CenterVertically,
          horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
          // Barber Avatar Icon
          Box(
            modifier = Modifier
              .size(44.dp)
              .clip(CircleShape)
              .background(SalonInkCard)
              .border(1.dp, SalonBrass.copy(alpha = 0.5f), CircleShape),
            contentAlignment = Alignment.Center
          ) {
            Icon(
              imageVector = Icons.Default.Person,
              contentDescription = "Barber ${barber.name}",
              tint = SalonBrassBright,
              modifier = Modifier.size(24.dp)
            )
          }

          Column {
            Text(
              text = barber.name,
              color = SalonPaper,
              fontSize = 20.sp,
              fontWeight = FontWeight.Bold,
              letterSpacing = 0.4.sp
            )
            Text(
              text = barber.tag.uppercase(),
              color = SalonBrassBright,
              fontSize = 11.sp,
              fontWeight = FontWeight.SemiBold,
              letterSpacing = 0.8.sp
            )
          }
        }

        // Rating & Experience Pill
        Column(horizontalAlignment = Alignment.End) {
          Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(2.dp)
          ) {
            Icon(
              imageVector = Icons.Default.Star,
              contentDescription = "5.0 rating",
              tint = SalonGold,
              modifier = Modifier.size(14.dp)
            )
            Text(
              text = "5.0",
              color = SalonPaper,
              fontSize = 13.sp,
              fontWeight = FontWeight.Bold
            )
          }
          Text(
            text = barber.experience,
            color = SalonPaperDim,
            fontSize = 11.sp
          )
        }
      }

      Spacer(modifier = Modifier.height(14.dp))

      Text(
        text = barber.quote,
        color = SalonPaperDim,
        fontSize = 13.sp,
        lineHeight = 20.sp
      )

      Spacer(modifier = Modifier.height(16.dp))

      OutlinedButton(
        onClick = onBookWithBarber,
        colors = ButtonDefaults.outlinedButtonColors(
          contentColor = SalonBrassBright,
          containerColor = SalonInkCard.copy(alpha = 0.6f)
        ),
        border = ButtonDefaults.outlinedButtonBorder.copy(brush = androidx.compose.ui.graphics.SolidColor(SalonBrass)),
        shape = RoundedCornerShape(4.dp),
        modifier = Modifier
          .fillMaxWidth()
          .height(42.dp)
          .testTag("btn_request_barber_${barber.id}")
      ) {
        Icon(
          imageVector = Icons.Default.CalendarMonth,
          contentDescription = null,
          tint = SalonBrassBright,
          modifier = Modifier.size(16.dp)
        )
        Spacer(modifier = Modifier.width(6.dp))
        Text(
          text = "REQUEST ${barber.name.uppercase()} FOR APPOINTMENT",
          fontSize = 11.sp,
          fontWeight = FontWeight.Bold,
          letterSpacing = 0.5.sp,
          color = SalonPaper
        )
      }
    }
  }
}
