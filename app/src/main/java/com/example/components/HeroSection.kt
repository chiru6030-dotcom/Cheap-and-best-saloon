package com.example.components

import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Chat
import androidx.compose.material.icons.filled.Navigation
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.SalonData
import com.example.ui.theme.SalonBrass
import com.example.ui.theme.SalonBrassBright
import com.example.ui.theme.SalonGold
import com.example.ui.theme.SalonInk
import com.example.ui.theme.SalonInkBorder
import com.example.ui.theme.SalonPaper
import com.example.ui.theme.SalonPaperDim

@Composable
fun HeroSection(
  onBookAppointment: () -> Unit,
  onWhatsAppDirect: () -> Unit,
  onCallNow: () -> Unit,
  onGetDirections: () -> Unit,
  modifier: Modifier = Modifier
) {
  Box(
    modifier = modifier
      .fillMaxWidth()
      .background(
        Brush.radialGradient(
          colors = listOf(
            SalonBrass.copy(alpha = 0.12f),
            Color.Transparent
          ),
          center = Offset(600f, 300f),
          radius = 700f
        )
      )
      .padding(horizontal = 20.dp, vertical = 24.dp)
      .testTag("hero_section")
  ) {
    Column(
      modifier = Modifier.fillMaxWidth()
    ) {
      // 5.0 Rating Eyebrow Badge
      Row(
        modifier = Modifier
          .clip(RoundedCornerShape(20.dp))
          .background(SalonInkBorder.copy(alpha = 0.4f))
          .padding(horizontal = 12.dp, vertical = 6.dp)
          .semantics { contentDescription = "5.0 rating by 65 customers in Bogadi 2nd Stage" },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
      ) {
        Row(
          verticalAlignment = Alignment.CenterVertically,
          horizontalArrangement = Arrangement.spacedBy(3.dp)
        ) {
          Icon(
            imageVector = Icons.Default.Star,
            contentDescription = null,
            tint = SalonGold,
            modifier = Modifier.size(16.dp)
          )
          Text(
            text = SalonData.RATING,
            color = SalonBrassBright,
            fontWeight = FontWeight.Bold,
            fontSize = 17.sp
          )
        }
        Text(
          text = "•",
          color = SalonPaperDim,
          fontSize = 12.sp
        )
        Text(
          text = "rated by ${SalonData.REVIEW_COUNT} customers in Bogadi 2nd Stage",
          color = SalonPaperDim,
          fontSize = 11.sp,
          fontWeight = FontWeight.Medium
        )
      }

      Spacer(modifier = Modifier.height(20.dp))

      // Main Headline with Barber Pole visual alongside
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Column(modifier = Modifier.weight(1f)) {
          Text(
            text = "Sharp cuts,",
            color = SalonPaper,
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            lineHeight = 36.sp,
            letterSpacing = 0.5.sp
          )
          Text(
            text = "fair prices,",
            color = SalonBrassBright,
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            lineHeight = 36.sp,
            letterSpacing = 0.5.sp
          )
          Text(
            text = "and a shop that treats you right",
            color = SalonPaper,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            lineHeight = 30.sp,
            letterSpacing = 0.4.sp
          )
        }

        Spacer(modifier = Modifier.width(12.dp))

        // Illuminated Rotating Barber Pole
        AuthenticBarberPole(
          poleWidth = 52.dp,
          poleHeight = 180.dp,
          modifier = Modifier.padding(end = 4.dp)
        )
      }

      Spacer(modifier = Modifier.height(16.dp))

      // Lede description
      Text(
        text = SalonData.SALON_SUBTITLE,
        color = SalonPaperDim,
        fontSize = 14.sp,
        lineHeight = 22.sp,
        modifier = Modifier.fillMaxWidth()
      )

      Spacer(modifier = Modifier.height(22.dp))

      // Primary Hero Actions
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
      ) {
        ElevatedButton(
          onClick = onBookAppointment,
          colors = ButtonDefaults.elevatedButtonColors(
            containerColor = SalonBrass,
            contentColor = SalonInk
          ),
          shape = RoundedCornerShape(4.dp),
          modifier = Modifier
            .weight(1f)
            .height(48.dp)
            .testTag("btn_hero_book")
        ) {
          Icon(
            imageVector = Icons.Default.CalendarMonth,
            contentDescription = null,
            tint = SalonInk,
            modifier = Modifier.size(17.dp)
          )
          Spacer(modifier = Modifier.width(6.dp))
          Text(
            text = "BOOK APPOINTMENT",
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = 0.5.sp
          )
        }

        OutlinedButton(
          onClick = onWhatsAppDirect,
          colors = ButtonDefaults.outlinedButtonColors(
            contentColor = SalonPaper,
            containerColor = SalonInk
          ),
          border = ButtonDefaults.outlinedButtonBorder.copy(brush = androidx.compose.ui.graphics.SolidColor(SalonBrassBright)),
          shape = RoundedCornerShape(4.dp),
          modifier = Modifier
            .height(48.dp)
            .testTag("btn_hero_whatsapp")
        ) {
          Icon(
            imageVector = Icons.Default.Chat,
            contentDescription = "WhatsApp booking",
            tint = SalonBrassBright,
            modifier = Modifier.size(17.dp)
          )
          Spacer(modifier = Modifier.width(6.dp))
          Text(
            text = "WHATSAPP",
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold,
            color = SalonPaper
          )
        }
      }

      Spacer(modifier = Modifier.height(10.dp))

      // Secondary row: Call & Directions
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
      ) {
        OutlinedButton(
          onClick = onCallNow,
          colors = ButtonDefaults.outlinedButtonColors(
            contentColor = SalonPaper,
            containerColor = SalonInk
          ),
          border = ButtonDefaults.outlinedButtonBorder.copy(brush = androidx.compose.ui.graphics.SolidColor(SalonInkBorder)),
          shape = RoundedCornerShape(4.dp),
          modifier = Modifier
            .weight(1f)
            .height(44.dp)
            .testTag("btn_hero_call")
        ) {
          Icon(
            imageVector = Icons.Default.Phone,
            contentDescription = null,
            tint = SalonBrassBright,
            modifier = Modifier.size(15.dp)
          )
          Spacer(modifier = Modifier.width(6.dp))
          Text(
            text = "CALL 099018 17155",
            fontSize = 10.sp,
            fontWeight = FontWeight.Bold,
            color = SalonPaper
          )
        }

        OutlinedButton(
          onClick = onGetDirections,
          colors = ButtonDefaults.outlinedButtonColors(
            contentColor = SalonPaper,
            containerColor = SalonInk
          ),
          border = ButtonDefaults.outlinedButtonBorder.copy(brush = androidx.compose.ui.graphics.SolidColor(SalonInkBorder)),
          shape = RoundedCornerShape(4.dp),
          modifier = Modifier
            .weight(1f)
            .height(44.dp)
            .testTag("btn_hero_directions")
        ) {
          Icon(
            imageVector = Icons.Default.Navigation,
            contentDescription = null,
            tint = SalonBrassBright,
            modifier = Modifier.size(15.dp)
          )
          Spacer(modifier = Modifier.width(6.dp))
          Text(
            text = "GET DIRECTIONS",
            fontSize = 10.sp,
            fontWeight = FontWeight.Bold,
            color = SalonPaper
          )
        }
      }
    }
  }
}
