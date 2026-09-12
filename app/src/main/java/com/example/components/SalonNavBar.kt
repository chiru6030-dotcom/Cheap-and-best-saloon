package com.example.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.ContentCut
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.SalonBrass
import com.example.ui.theme.SalonBrassBright
import com.example.ui.theme.SalonInk
import com.example.ui.theme.SalonInkBorder
import com.example.ui.theme.SalonInkCard
import com.example.ui.theme.SalonInkSoft
import com.example.ui.theme.SalonPaper
import com.example.ui.theme.SalonPaperDim

enum class SalonSection(val label: String, val icon: ImageVector) {
  HERO("Chair", Icons.Default.Home),
  SERVICES("Services", Icons.Default.ContentCut),
  BARBERS("Barbers", Icons.Default.People),
  REVIEWS("Reviews (5.0)", Icons.Default.Star),
  LOCATION("Location", Icons.Default.LocationOn),
  BOOKING("Book Slot", Icons.Default.CalendarMonth)
}

@Composable
fun SalonNavBar(
  activeSection: SalonSection,
  onSectionSelected: (SalonSection) -> Unit,
  onCallNow: () -> Unit,
  modifier: Modifier = Modifier
) {
  Column(
    modifier = modifier
      .fillMaxWidth()
      .background(
        Brush.verticalGradient(
          colors = listOf(
            SalonInk,
            SalonInk.copy(alpha = 0.98f),
            SalonInkSoft.copy(alpha = 0.95f)
          )
        )
      )
      .statusBarsPadding()
      .testTag("salon_top_nav_bar")
  ) {
    // Brand Header Row
    Row(
      modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 16.dp, vertical = 10.dp),
      horizontalArrangement = Arrangement.SpaceBetween,
      verticalAlignment = Alignment.CenterVertically
    ) {
      Column(
        modifier = Modifier
          .clickable(role = Role.Button) { onSectionSelected(SalonSection.HERO) }
          .semantics { contentDescription = "Cheap and Best Men's Salon Home" }
      ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
          Text(
            text = "CHEAP",
            color = SalonPaper,
            fontWeight = FontWeight.Bold,
            fontSize = 17.sp,
            letterSpacing = 0.8.sp
          )
          Text(
            text = " & ",
            color = SalonBrassBright,
            fontWeight = FontWeight.Bold,
            fontSize = 17.sp
          )
          Text(
            text = "BEST",
            color = SalonPaper,
            fontWeight = FontWeight.Bold,
            fontSize = 17.sp,
            letterSpacing = 0.8.sp
          )
        }
        Text(
          text = "MEN'S SALON · BOGADI 2ND STAGE",
          color = SalonBrassBright,
          fontWeight = FontWeight.Medium,
          fontSize = 10.sp,
          letterSpacing = 0.6.sp
        )
      }

      // Quick Call Action
      OutlinedButton(
        onClick = onCallNow,
        colors = ButtonDefaults.outlinedButtonColors(
          contentColor = SalonPaper,
          containerColor = SalonInkSoft
        ),
        border = ButtonDefaults.outlinedButtonBorder.copy(brush = Brush.horizontalGradient(listOf(SalonBrassBright, SalonBrass))),
        shape = RoundedCornerShape(4.dp),
        modifier = Modifier
          .height(44.dp)
          .testTag("nav_call_button")
      ) {
        Icon(
          imageVector = Icons.Default.Phone,
          contentDescription = "Call salon at 099018 17155",
          tint = SalonBrassBright,
          modifier = Modifier.size(16.dp)
        )
        Spacer(modifier = Modifier.width(6.dp))
        Text(
          text = "CALL NOW",
          fontSize = 12.sp,
          fontWeight = FontWeight.Bold,
          letterSpacing = 0.5.sp,
          color = SalonPaper
        )
      }
    }

    // Accessible Horizontal Navigation Chips for smooth jumping
    val scrollState = rememberScrollState()
    Row(
      modifier = Modifier
        .fillMaxWidth()
        .horizontalScroll(scrollState)
        .padding(horizontal = 12.dp, vertical = 6.dp),
      horizontalArrangement = Arrangement.spacedBy(8.dp),
      verticalAlignment = Alignment.CenterVertically
    ) {
      SalonSection.values().forEach { section ->
        val isSelected = activeSection == section
        val chipBackground = if (isSelected) SalonBrass else SalonInkCard
        val chipTextColor = if (isSelected) SalonInk else SalonPaperDim
        val chipIconColor = if (isSelected) SalonInk else SalonBrassBright

        Box(
          modifier = Modifier
            .height(38.dp)
            .clip(RoundedCornerShape(6.dp))
            .background(chipBackground)
            .border(
              width = 1.dp,
              color = if (isSelected) SalonBrassBright else SalonInkBorder,
              shape = RoundedCornerShape(6.dp)
            )
            .clickable(
              role = Role.Tab,
              onClickLabel = "Navigate to ${section.label}"
            ) {
              onSectionSelected(section)
            }
            .padding(horizontal = 12.dp, vertical = 6.dp)
            .testTag("nav_tab_${section.name.lowercase()}"),
          contentAlignment = Alignment.Center
        ) {
          Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(6.dp)
          ) {
            Icon(
              imageVector = section.icon,
              contentDescription = null,
              tint = chipIconColor,
              modifier = Modifier.size(15.dp)
            )
            Text(
              text = section.label,
              color = chipTextColor,
              fontSize = 12.sp,
              fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium
            )
          }
        }
      }
    }

    // Bottom subtle line
    Box(
      modifier = Modifier
        .fillMaxWidth()
        .height(1.dp)
        .background(SalonInkBorder)
    )
  }
}
