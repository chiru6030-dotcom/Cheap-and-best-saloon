package com.example.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.Chat
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material.icons.filled.VideoLibrary
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
import com.example.data.SocialLink
import com.example.ui.theme.SalonBrass
import com.example.ui.theme.SalonBrassBright
import com.example.ui.theme.SalonGold
import com.example.ui.theme.SalonInk
import com.example.ui.theme.SalonInkBorder
import com.example.ui.theme.SalonInkCard
import com.example.ui.theme.SalonPaper
import com.example.ui.theme.SalonPaperDim
import com.example.ui.theme.SalonPaperMuted
import java.util.Calendar

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun SalonFooter(
  onNavigateSection: (SalonSection) -> Unit,
  onOpenSocialLink: (SocialLink) -> Unit,
  onCallPhone: () -> Unit,
  onOpenWhatsApp: () -> Unit,
  onOpenMap: () -> Unit,
  modifier: Modifier = Modifier
) {
  val currentYear = Calendar.getInstance().get(Calendar.YEAR)

  Column(
    modifier = modifier
      .fillMaxWidth()
      .background(SalonInk)
      .padding(horizontal = 20.dp, vertical = 32.dp)
      .navigationBarsPadding()
      .testTag("salon_footer")
  ) {
    // Top border stripe accent
    BarberPoleStripeRule()

    Spacer(modifier = Modifier.height(28.dp))

    // Brand Identification
    Text(
      text = SalonData.SALON_NAME.uppercase(),
      color = SalonPaper,
      fontSize = 20.sp,
      fontWeight = FontWeight.Bold,
      letterSpacing = 0.8.sp
    )
    Spacer(modifier = Modifier.height(6.dp))
    Text(
      text = "Bogadi 2nd Stage, Mysuru, Karnataka 570026",
      color = SalonBrassBright,
      fontSize = 13.sp,
      fontWeight = FontWeight.Medium
    )
    Spacer(modifier = Modifier.height(8.dp))
    Row(
      verticalAlignment = Alignment.CenterVertically,
      horizontalArrangement = Arrangement.spacedBy(6.dp)
    ) {
      Icon(
        imageVector = Icons.Default.Star,
        contentDescription = null,
        tint = SalonGold,
        modifier = Modifier.size(15.dp)
      )
      Text(
        text = "5.0 rating · 65 verified Google reviews · Open until 10:00 PM daily",
        color = SalonPaperDim,
        fontSize = 12.sp
      )
    }

    Spacer(modifier = Modifier.height(28.dp))

    // SECTION 1: QUICK NAVIGATION FOR BETTER ACCESSIBILITY
    Text(
      text = "QUICK NAVIGATION",
      color = SalonBrassBright,
      fontSize = 12.sp,
      fontWeight = FontWeight.Bold,
      letterSpacing = 1.sp
    )
    Spacer(modifier = Modifier.height(12.dp))

    FlowRow(
      modifier = Modifier.fillMaxWidth(),
      horizontalArrangement = Arrangement.spacedBy(8.dp),
      verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
      SalonSection.values().forEach { section ->
        Box(
          modifier = Modifier
            .clip(RoundedCornerShape(4.dp))
            .background(SalonInkCard)
            .border(1.dp, SalonInkBorder, RoundedCornerShape(4.dp))
            .clickable(
              role = Role.Button,
              onClickLabel = "Scroll to ${section.label}"
            ) {
              onNavigateSection(section)
            }
            .padding(horizontal = 12.dp, vertical = 7.dp)
            .testTag("footer_nav_${section.name.lowercase()}")
        ) {
          Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(6.dp)
          ) {
            Icon(
              imageVector = section.icon,
              contentDescription = null,
              tint = SalonBrassBright,
              modifier = Modifier.size(13.dp)
            )
            Text(
              text = section.label,
              color = SalonPaper,
              fontSize = 12.sp,
              fontWeight = FontWeight.Medium
            )
          }
        }
      }
    }

    Spacer(modifier = Modifier.height(28.dp))

    // SECTION 2: CONTACT INFO
    Text(
      text = "CONTACT INFO & HOURS",
      color = SalonBrassBright,
      fontSize = 12.sp,
      fontWeight = FontWeight.Bold,
      letterSpacing = 1.sp
    )
    Spacer(modifier = Modifier.height(12.dp))

    Column(
      modifier = Modifier
        .fillMaxWidth()
        .clip(RoundedCornerShape(6.dp))
        .background(SalonInkCard)
        .border(1.dp, SalonInkBorder, RoundedCornerShape(6.dp))
        .padding(16.dp),
      verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
      // Clickable Phone
      Row(
        modifier = Modifier
          .fillMaxWidth()
          .clickable(role = Role.Button, onClick = onCallPhone)
          .semantics { contentDescription = "Call salon phone 099018 17155" },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp)
      ) {
        Icon(
          imageVector = Icons.Default.Phone,
          contentDescription = null,
          tint = SalonBrassBright,
          modifier = Modifier.size(18.dp)
        )
        Column(modifier = Modifier.weight(1f)) {
          Text(text = "Phone (Appointments & Walk-ins)", color = SalonPaperDim, fontSize = 11.sp)
          Text(text = SalonData.PHONE_DISPLAY, color = SalonPaper, fontSize = 14.sp, fontWeight = FontWeight.Bold)
        }
        Box(
          modifier = Modifier
            .clip(RoundedCornerShape(4.dp))
            .background(SalonBrass.copy(alpha = 0.2f))
            .border(1.dp, SalonBrass, RoundedCornerShape(4.dp))
            .padding(horizontal = 8.dp, vertical = 4.dp)
        ) {
          Text(text = "Call", color = SalonBrassBright, fontSize = 11.sp, fontWeight = FontWeight.Bold)
        }
      }

      Box(modifier = Modifier.fillMaxWidth().height(1.dp).background(SalonInkBorder))

      // Clickable WhatsApp
      Row(
        modifier = Modifier
          .fillMaxWidth()
          .clickable(role = Role.Button, onClick = onOpenWhatsApp)
          .semantics { contentDescription = "Chat on WhatsApp with salon" },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp)
      ) {
        Icon(
          imageVector = Icons.Default.Chat,
          contentDescription = null,
          tint = SalonBrassBright,
          modifier = Modifier.size(18.dp)
        )
        Column(modifier = Modifier.weight(1f)) {
          Text(text = "WhatsApp Booking", color = SalonPaperDim, fontSize = 11.sp)
          Text(text = "+91 99018 17155", color = SalonPaper, fontSize = 14.sp, fontWeight = FontWeight.Bold)
        }
        Box(
          modifier = Modifier
            .clip(RoundedCornerShape(4.dp))
            .background(SalonBrass.copy(alpha = 0.2f))
            .border(1.dp, SalonBrass, RoundedCornerShape(4.dp))
            .padding(horizontal = 8.dp, vertical = 4.dp)
        ) {
          Text(text = "Chat", color = SalonBrassBright, fontSize = 11.sp, fontWeight = FontWeight.Bold)
        }
      }

      Box(modifier = Modifier.fillMaxWidth().height(1.dp).background(SalonInkBorder))

      // Hours
      Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp)
      ) {
        Icon(
          imageVector = Icons.Default.AccessTime,
          contentDescription = null,
          tint = SalonBrassBright,
          modifier = Modifier.size(18.dp)
        )
        Column {
          Text(text = "Working Hours", color = SalonPaperDim, fontSize = 11.sp)
          Text(text = "Monday – Sunday: 8:30 AM – 10:00 PM", color = SalonPaper, fontSize = 13.sp, fontWeight = FontWeight.Medium)
        }
      }

      Box(modifier = Modifier.fillMaxWidth().height(1.dp).background(SalonInkBorder))

      // Address & Map Trigger
      Row(
        modifier = Modifier
          .fillMaxWidth()
          .clickable(role = Role.Button, onClick = onOpenMap)
          .semantics { contentDescription = "Open address in Google Maps" },
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.spacedBy(10.dp)
      ) {
        Icon(
          imageVector = Icons.Default.LocationOn,
          contentDescription = null,
          tint = SalonBrassBright,
          modifier = Modifier.size(18.dp).padding(top = 2.dp)
        )
        Column(modifier = Modifier.weight(1f)) {
          Text(text = "Address", color = SalonPaperDim, fontSize = 11.sp)
          Text(
            text = SalonData.ADDRESS,
            color = SalonPaper,
            fontSize = 13.sp,
            lineHeight = 18.sp
          )
        }
        Box(
          modifier = Modifier
            .clip(RoundedCornerShape(4.dp))
            .background(SalonBrass.copy(alpha = 0.2f))
            .border(1.dp, SalonBrass, RoundedCornerShape(4.dp))
            .padding(horizontal = 8.dp, vertical = 4.dp)
        ) {
          Text(text = "Map", color = SalonBrassBright, fontSize = 11.sp, fontWeight = FontWeight.Bold)
        }
      }
    }

    Spacer(modifier = Modifier.height(28.dp))

    // SECTION 3: SOCIAL MEDIA LINKS
    Text(
      text = "FOLLOW & CONNECT ON SOCIAL MEDIA",
      color = SalonBrassBright,
      fontSize = 12.sp,
      fontWeight = FontWeight.Bold,
      letterSpacing = 1.sp
    )
    Spacer(modifier = Modifier.height(12.dp))

    FlowRow(
      modifier = Modifier.fillMaxWidth(),
      horizontalArrangement = Arrangement.spacedBy(10.dp),
      verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
      SalonData.socialLinks.forEach { social ->
        SocialButton(
          social = social,
          onClick = { onOpenSocialLink(social) }
        )
      }
    }

    Spacer(modifier = Modifier.height(32.dp))

    // SECTION 4: COPYRIGHT UPDATES & LEGAL
    Box(
      modifier = Modifier
        .fillMaxWidth()
        .height(1.dp)
        .background(SalonInkBorder)
    )

    Spacer(modifier = Modifier.height(20.dp))

    Column(
      modifier = Modifier.fillMaxWidth(),
      horizontalAlignment = Alignment.CenterHorizontally
    ) {
      Text(
        text = "© $currentYear Cheap & Best Men's Salon. All rights reserved.",
        color = SalonPaperDim,
        fontSize = 12.sp,
        fontWeight = FontWeight.Medium
      )
      Spacer(modifier = Modifier.height(4.dp))
      Text(
        text = "Bogadi 2nd Stage, Mysuru, Karnataka 570026 · Updated for $currentYear",
        color = SalonPaperMuted,
        fontSize = 11.sp
      )
      Spacer(modifier = Modifier.height(6.dp))
      Text(
        text = "Clean Chairs · Friendly Hands · Honest Pricing · Always",
        color = SalonBrassBright,
        fontSize = 10.sp,
        fontWeight = FontWeight.SemiBold,
        letterSpacing = 0.5.sp
      )
    }
  }
}

@Composable
private fun SocialButton(
  social: SocialLink,
  onClick: () -> Unit
) {
  val icon: ImageVector = when (social.iconType) {
    "whatsapp" -> Icons.Default.Chat
    "instagram" -> Icons.Default.Share
    "facebook" -> Icons.Default.ThumbUp
    "youtube" -> Icons.Default.VideoLibrary
    "maps" -> Icons.Default.LocationOn
    else -> Icons.Default.Language
  }

  Box(
    modifier = Modifier
      .clip(RoundedCornerShape(6.dp))
      .background(SalonInkCard)
      .border(1.dp, SalonInkBorder, RoundedCornerShape(6.dp))
      .clickable(
        role = Role.Button,
        onClick = onClick,
        onClickLabel = "Open ${social.platform}"
      )
      .padding(horizontal = 14.dp, vertical = 9.dp)
      .testTag("social_link_${social.platform.lowercase().replace(" ", "_")}")
      .semantics { contentDescription = "${social.platform}: ${social.handle}" }
  ) {
    Row(
      verticalAlignment = Alignment.CenterVertically,
      horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
      Box(
        modifier = Modifier
          .size(26.dp)
          .clip(CircleShape)
          .background(SalonBrass.copy(alpha = 0.2f)),
        contentAlignment = Alignment.Center
      ) {
        Icon(
          imageVector = icon,
          contentDescription = null,
          tint = SalonBrassBright,
          modifier = Modifier.size(14.dp)
        )
      }
      Column {
        Text(
          text = social.platform,
          color = SalonPaper,
          fontSize = 12.sp,
          fontWeight = FontWeight.Bold
        )
        Text(
          text = social.handle,
          color = SalonPaperDim,
          fontSize = 10.sp
        )
      }
    }
  }
}
