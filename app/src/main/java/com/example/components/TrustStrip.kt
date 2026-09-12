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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.SalonData
import com.example.ui.theme.SalonBrassBright
import com.example.ui.theme.SalonInkBorder
import com.example.ui.theme.SalonInkSoft
import com.example.ui.theme.SalonPaperDim

@Composable
fun TrustStrip(modifier: Modifier = Modifier) {
  Box(
    modifier = modifier
      .fillMaxWidth()
      .background(SalonInkSoft)
      .padding(vertical = 24.dp, horizontal = 16.dp)
      .testTag("trust_strip_section")
  ) {
    Column(
      modifier = Modifier.fillMaxWidth(),
      verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
      val stats = SalonData.trustStats
      // Two rows of 2 columns each for mobile responsiveness
      for (i in stats.indices step 2) {
        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
          TrustMetricCard(
            count = stats[i].first,
            label = stats[i].second,
            modifier = Modifier.weight(1f)
          )
          if (i + 1 < stats.size) {
            TrustMetricCard(
              count = stats[i + 1].first,
              label = stats[i + 1].second,
              modifier = Modifier.weight(1f)
            )
          }
        }
      }
    }
  }
}

@Composable
private fun TrustMetricCard(
  count: String,
  label: String,
  modifier: Modifier = Modifier
) {
  Box(
    modifier = modifier
      .clip(RoundedCornerShape(6.dp))
      .border(1.dp, SalonInkBorder, RoundedCornerShape(6.dp))
      .background(SalonInkSoft)
      .padding(14.dp)
  ) {
    Column(
      horizontalAlignment = Alignment.Start
    ) {
      Text(
        text = count,
        color = SalonBrassBright,
        fontSize = 28.sp,
        fontWeight = FontWeight.Bold,
        letterSpacing = 0.5.sp
      )
      Spacer(modifier = Modifier.height(6.dp))
      Text(
        text = label,
        color = SalonPaperDim,
        fontSize = 12.sp,
        lineHeight = 17.sp
      )
    }
  }
}
