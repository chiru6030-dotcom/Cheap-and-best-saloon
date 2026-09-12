package com.example.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.material.icons.filled.ContentCut
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.LocalFlorist
import androidx.compose.material.icons.filled.Shower
import androidx.compose.material.icons.filled.Spa
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.SalonData
import com.example.data.ServiceItem
import com.example.ui.theme.SalonBrass
import com.example.ui.theme.SalonBrassBright
import com.example.ui.theme.SalonInk
import com.example.ui.theme.SalonInkBorder
import com.example.ui.theme.SalonInkCard
import com.example.ui.theme.SalonPaper
import com.example.ui.theme.SalonPaperDim
import com.example.ui.theme.SalonRed

@Composable
fun ServicesSection(
  onSelectServiceForBooking: (ServiceItem) -> Unit,
  modifier: Modifier = Modifier
) {
  Column(
    modifier = modifier
      .fillMaxWidth()
      .padding(horizontal = 20.dp, vertical = 32.dp)
      .testTag("services_section")
  ) {
    // Section Header
    Text(
      text = "IN THE CHAIR",
      color = SalonBrassBright,
      fontSize = 12.sp,
      fontWeight = FontWeight.Bold,
      letterSpacing = 1.2.sp
    )
    Spacer(modifier = Modifier.height(6.dp))
    Text(
      text = "What we do best",
      color = SalonPaper,
      fontSize = 26.sp,
      fontWeight = FontWeight.Bold,
      letterSpacing = 0.5.sp
    )
    Spacer(modifier = Modifier.height(10.dp))
    Text(
      text = "No overselling, no upsells you didn't ask for — just the core barbershop services, done properly, by people who've been asked for by name.",
      color = SalonPaperDim,
      fontSize = 14.sp,
      lineHeight = 21.sp
    )
    Spacer(modifier = Modifier.height(24.dp))

    // Services List
    Column(
      verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
      SalonData.services.forEach { service ->
        ServiceCard(
          service = service,
          onBook = { onSelectServiceForBooking(service) }
        )
      }
    }
  }
}

@Composable
private fun ServiceCard(
  service: ServiceItem,
  onBook: () -> Unit
) {
  val icon: ImageVector = when (service.iconName) {
    "scissors" -> Icons.Default.ContentCut
    "beard" -> Icons.Default.Face
    "wash" -> Icons.Default.Shower
    "wedding" -> Icons.Default.Star
    "spa" -> Icons.Default.Spa
    else -> Icons.Default.LocalFlorist
  }

  Box(
    modifier = Modifier
      .fillMaxWidth()
      .clip(RoundedCornerShape(8.dp))
      .background(SalonInkCard)
      .border(1.dp, SalonInkBorder, RoundedCornerShape(8.dp))
      .clickable(role = Role.Button, onClick = onBook)
      .padding(20.dp)
      .testTag("service_card_${service.number}")
      .semantics { contentDescription = "Service: ${service.title}, Price: ${service.price}" }
  ) {
    Column {
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Row(
          verticalAlignment = Alignment.CenterVertically,
          horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
          Text(
            text = service.number,
            color = SalonRed,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = 0.8.sp
          )
          Box(
            modifier = Modifier
              .size(32.dp)
              .clip(RoundedCornerShape(6.dp))
              .background(SalonInkBorder.copy(alpha = 0.5f)),
            contentAlignment = Alignment.Center
          ) {
            Icon(
              imageVector = icon,
              contentDescription = null,
              tint = SalonBrassBright,
              modifier = Modifier.size(18.dp)
            )
          }
        }

        // Price & duration pill
        Row(
          verticalAlignment = Alignment.CenterVertically,
          horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
          Text(
            text = service.duration,
            color = SalonPaperDim,
            fontSize = 12.sp
          )
          Box(
            modifier = Modifier
              .clip(RoundedCornerShape(4.dp))
              .background(SalonBrass.copy(alpha = 0.2f))
              .border(1.dp, SalonBrass, RoundedCornerShape(4.dp))
              .padding(horizontal = 8.dp, vertical = 2.dp)
          ) {
            Text(
              text = service.price,
              color = SalonBrassBright,
              fontSize = 13.sp,
              fontWeight = FontWeight.Bold
            )
          }
        }
      }

      Spacer(modifier = Modifier.height(14.dp))

      Text(
        text = service.title,
        color = SalonPaper,
        fontSize = 19.sp,
        fontWeight = FontWeight.Bold,
        letterSpacing = 0.3.sp
      )

      Spacer(modifier = Modifier.height(8.dp))

      Text(
        text = service.description,
        color = SalonPaperDim,
        fontSize = 13.sp,
        lineHeight = 19.sp
      )

      Spacer(modifier = Modifier.height(16.dp))

      ElevatedButton(
        onClick = onBook,
        colors = ButtonDefaults.elevatedButtonColors(
          containerColor = SalonBrass,
          contentColor = SalonInk
        ),
        shape = RoundedCornerShape(4.dp),
        modifier = Modifier
          .fillMaxWidth()
          .height(44.dp)
          .testTag("btn_book_service_${service.number}")
      ) {
        Icon(
          imageVector = Icons.Default.CalendarMonth,
          contentDescription = null,
          tint = SalonInk,
          modifier = Modifier.size(16.dp)
        )
        Spacer(modifier = Modifier.width(6.dp))
        Text(
          text = "BOOK APPOINTMENT (${service.price})",
          fontSize = 12.sp,
          fontWeight = FontWeight.Bold,
          letterSpacing = 0.5.sp
        )
      }
    }
  }
}
