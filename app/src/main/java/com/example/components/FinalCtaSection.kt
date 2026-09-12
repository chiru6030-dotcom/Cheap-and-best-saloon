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
import androidx.compose.material.icons.filled.Chat
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.SalonBrass
import com.example.ui.theme.SalonBrassBright
import com.example.ui.theme.SalonInk
import com.example.ui.theme.SalonInkSoft
import com.example.ui.theme.SalonPaper
import com.example.ui.theme.SalonPaperDim

@Composable
fun FinalCtaSection(
  onBookWhatsApp: () -> Unit,
  onCallSalon: () -> Unit,
  modifier: Modifier = Modifier
) {
  Box(
    modifier = modifier
      .fillMaxWidth()
      .background(SalonInkSoft)
      .padding(horizontal = 24.dp, vertical = 44.dp)
      .testTag("final_cta_section")
  ) {
    Column(
      modifier = Modifier.fillMaxWidth(),
      horizontalAlignment = Alignment.CenterHorizontally
    ) {
      Text(
        text = "Your next haircut is a message away",
        color = SalonPaper,
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center,
        letterSpacing = 0.4.sp
      )
      Spacer(modifier = Modifier.height(10.dp))
      Text(
        text = "Skip the wait — send a WhatsApp message and we'll get you a chair.",
        color = SalonPaperDim,
        fontSize = 14.sp,
        textAlign = TextAlign.Center,
        lineHeight = 21.sp
      )
      Spacer(modifier = Modifier.height(24.dp))

      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
      ) {
        ElevatedButton(
          onClick = onBookWhatsApp,
          colors = ButtonDefaults.elevatedButtonColors(
            containerColor = SalonBrass,
            contentColor = SalonInk
          ),
          shape = RoundedCornerShape(4.dp),
          modifier = Modifier
            .weight(1f)
            .height(48.dp)
            .testTag("btn_final_cta_whatsapp")
        ) {
          Icon(
            imageVector = Icons.Default.Chat,
            contentDescription = null,
            tint = SalonInk,
            modifier = Modifier.size(16.dp)
          )
          Spacer(modifier = Modifier.width(6.dp))
          Text(
            text = "BOOK ON WHATSAPP",
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = 0.5.sp
          )
        }

        OutlinedButton(
          onClick = onCallSalon,
          colors = ButtonDefaults.outlinedButtonColors(
            contentColor = SalonPaper,
            containerColor = SalonInk
          ),
          border = ButtonDefaults.outlinedButtonBorder.copy(brush = androidx.compose.ui.graphics.SolidColor(SalonBrassBright)),
          shape = RoundedCornerShape(4.dp),
          modifier = Modifier
            .weight(1f)
            .height(48.dp)
            .testTag("btn_final_cta_call")
        ) {
          Icon(
            imageVector = Icons.Default.Phone,
            contentDescription = null,
            tint = SalonBrassBright,
            modifier = Modifier.size(16.dp)
          )
          Spacer(modifier = Modifier.width(6.dp))
          Text(
            text = "CALL 099018 17155",
            fontSize = 10.sp,
            fontWeight = FontWeight.Bold,
            color = SalonPaper
          )
        }
      }
    }
  }
}
