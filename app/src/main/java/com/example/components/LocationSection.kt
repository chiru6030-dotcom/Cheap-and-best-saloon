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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Map
import androidx.compose.material.icons.filled.Navigation
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
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
import com.example.ui.theme.SalonBrass
import com.example.ui.theme.SalonBrassBright
import com.example.ui.theme.SalonInk
import com.example.ui.theme.SalonInkBorder
import com.example.ui.theme.SalonInkCard
import com.example.ui.theme.SalonInkSoft
import com.example.ui.theme.SalonPaper
import com.example.ui.theme.SalonPaperDim
import com.example.ui.theme.SalonRed

@Composable
fun LocationSection(
  onOpenMap: () -> Unit,
  onCallPhone: () -> Unit,
  onCopyAddress: () -> Unit,
  modifier: Modifier = Modifier
) {
  Box(
    modifier = modifier
      .fillMaxWidth()
      .background(SalonInkSoft)
      .padding(horizontal = 20.dp, vertical = 36.dp)
      .testTag("location_section")
  ) {
    Column {
      Text(
        text = "FIND US",
        color = SalonBrassBright,
        fontSize = 12.sp,
        fontWeight = FontWeight.Bold,
        letterSpacing = 1.2.sp
      )
      Spacer(modifier = Modifier.height(6.dp))
      Text(
        text = "Walk in, or call ahead",
        color = SalonPaper,
        fontSize = 26.sp,
        fontWeight = FontWeight.Bold,
        letterSpacing = 0.5.sp
      )
      Spacer(modifier = Modifier.height(20.dp))

      // Location Key-Value List
      Column(
        modifier = Modifier
          .fillMaxWidth()
          .clip(RoundedCornerShape(8.dp))
          .background(SalonInk)
          .border(1.dp, SalonInkBorder, RoundedCornerShape(8.dp))
          .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
      ) {
        LocationItemRow(
          icon = Icons.Default.Place,
          label = "ADDRESS",
          value = SalonData.ADDRESS,
          onClick = onCopyAddress,
          actionLabel = "Copy"
        )
        Box(modifier = Modifier.fillMaxWidth().height(1.dp).background(SalonInkBorder))

        LocationItemRow(
          icon = Icons.Default.AccessTime,
          label = "HOURS",
          value = "Open daily · Closes 10 pm (8:30 am – 10:00 pm)"
        )
        Box(modifier = Modifier.fillMaxWidth().height(1.dp).background(SalonInkBorder))

        LocationItemRow(
          icon = Icons.Default.Phone,
          label = "PHONE",
          value = SalonData.PHONE_DISPLAY,
          onClick = onCallPhone,
          actionLabel = "Call"
        )
        Box(modifier = Modifier.fillMaxWidth().height(1.dp).background(SalonInkBorder))

        LocationItemRow(
          icon = Icons.Default.Navigation,
          label = "PLUS CODE",
          value = SalonData.PLUS_CODE
        )
      }

      Spacer(modifier = Modifier.height(20.dp))

      // Interactive Map Card (matching HTML map-card)
      Box(
        modifier = Modifier
          .fillMaxWidth()
          .clip(RoundedCornerShape(8.dp))
          .background(SalonInkCard)
          .border(1.dp, SalonInkBorder, RoundedCornerShape(8.dp))
          .padding(22.dp)
          .testTag("interactive_map_card")
      ) {
        Column {
          // Teardrop Pin
          Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
          ) {
            Box(
              modifier = Modifier
                .size(44.dp)
                .clip(CircleShape)
                .background(SalonRed),
              contentAlignment = Alignment.Center
            ) {
              Icon(
                imageVector = Icons.Default.LocationOn,
                contentDescription = "Salon Pin",
                tint = SalonPaper,
                modifier = Modifier.size(24.dp)
              )
            }

            Column {
              Text(
                text = "Bogadi 2nd Stage, Mysuru",
                color = SalonPaper,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
              )
              Text(
                text = "TK Layout, Prashantha Nagara",
                color = SalonBrassBright,
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium
              )
            }
          }

          Spacer(modifier = Modifier.height(14.dp))

          Text(
            text = "Tucked into TK Layout, a short walk from Prashantha Nagara — easy to find, easier to become a regular at.",
            color = SalonPaperDim,
            fontSize = 13.sp,
            lineHeight = 19.sp
          )

          Spacer(modifier = Modifier.height(18.dp))

          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
          ) {
            ElevatedButton(
              onClick = onOpenMap,
              colors = ButtonDefaults.elevatedButtonColors(
                containerColor = SalonBrass,
                contentColor = SalonInk
              ),
              shape = RoundedCornerShape(4.dp),
              modifier = Modifier
                .weight(1f)
                .height(44.dp)
                .testTag("btn_open_google_maps")
            ) {
              Icon(
                imageVector = Icons.Default.Map,
                contentDescription = null,
                tint = SalonInk,
                modifier = Modifier.size(16.dp)
              )
              Spacer(modifier = Modifier.width(6.dp))
              Text(
                text = "GOOGLE MAPS",
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 0.5.sp
              )
            }

            OutlinedButton(
              onClick = onCopyAddress,
              colors = ButtonDefaults.outlinedButtonColors(
                contentColor = SalonPaper,
                containerColor = SalonInk
              ),
              border = ButtonDefaults.outlinedButtonBorder.copy(brush = androidx.compose.ui.graphics.SolidColor(SalonInkBorder)),
              shape = RoundedCornerShape(4.dp),
              modifier = Modifier
                .height(44.dp)
                .testTag("btn_copy_address")
            ) {
              Icon(
                imageVector = Icons.Default.ContentCopy,
                contentDescription = "Copy address",
                tint = SalonBrassBright,
                modifier = Modifier.size(16.dp)
              )
              Spacer(modifier = Modifier.width(6.dp))
              Text(
                text = "COPY",
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                color = SalonPaper
              )
            }
          }
        }
      }
    }
  }
}

@Composable
private fun LocationItemRow(
  icon: ImageVector,
  label: String,
  value: String,
  onClick: (() -> Unit)? = null,
  actionLabel: String? = null
) {
  Row(
    modifier = Modifier
      .fillMaxWidth()
      .then(
        if (onClick != null) {
          Modifier.clickable(role = Role.Button, onClick = onClick)
        } else Modifier
      )
      .semantics { contentDescription = "$label: $value" },
    verticalAlignment = Alignment.Top,
    horizontalArrangement = Arrangement.spacedBy(12.dp)
  ) {
    Icon(
      imageVector = icon,
      contentDescription = null,
      tint = SalonBrassBright,
      modifier = Modifier
        .size(20.dp)
        .padding(top = 2.dp)
    )

    Column(modifier = Modifier.weight(1f)) {
      Text(
        text = label,
        color = SalonBrassBright,
        fontWeight = FontWeight.Bold,
        fontSize = 11.sp,
        letterSpacing = 0.6.sp
      )
      Spacer(modifier = Modifier.height(3.dp))
      Text(
        text = value,
        color = SalonPaper,
        fontSize = 13.sp,
        lineHeight = 18.sp
      )
    }

    if (actionLabel != null && onClick != null) {
      Box(
        modifier = Modifier
          .clip(RoundedCornerShape(4.dp))
          .background(SalonInkBorder.copy(alpha = 0.6f))
          .padding(horizontal = 8.dp, vertical = 4.dp)
      ) {
        Text(
          text = actionLabel,
          color = SalonBrassBright,
          fontSize = 10.sp,
          fontWeight = FontWeight.Bold
        )
      }
    }
  }
}
