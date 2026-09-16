package com.example.components

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.Public
import androidx.compose.material.icons.filled.UploadFile
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.ui.theme.SalonBrass
import com.example.ui.theme.SalonBrassBright
import com.example.ui.theme.SalonInk
import com.example.ui.theme.SalonInkCard
import com.example.ui.theme.SalonInkSoft
import com.example.ui.theme.SalonPaper
import com.example.ui.theme.SalonPaperDim

@Composable
fun PublishGuideDialog(
  onDismiss: () -> Unit
) {
  val context = LocalContext.current
  val copiedState = remember { mutableStateOf(false) }

  val deployInstructions = """
Cheap & Best Men's Salon - Website Publishing Guide
---------------------------------------------------
Your standalone website files are generated in:
📁 /website/ (index.html, style.css, script.js)
📁 / (index.html, style.css, script.js)

QUICK DEPLOYMENT OPTIONS:
1. Netlify Drop (Instant & Free)
   - In AI Studio settings, click "Export as ZIP"
   - Open https://app.netlify.com/drop
   - Drag & drop the 'website' folder
   - Site is immediately live with free SSL!

2. Vercel
   - In terminal: cd website && npx vercel

3. GitHub Pages
   - Push repository to GitHub
   - Go to Repo Settings -> Pages -> Source: Deploy from branch (main) / (root)

4. Firebase Hosting
   - firebase init hosting -> select 'website' or root -> firebase deploy
  """.trimIndent()

  Dialog(
    onDismissRequest = onDismiss,
    properties = DialogProperties(usePlatformDefaultWidth = false)
  ) {
    Card(
      modifier = Modifier
        .fillMaxWidth(0.95f)
        .padding(16.dp)
        .testTag("dialog_publish_guide"),
      shape = RoundedCornerShape(16.dp),
      colors = CardDefaults.cardColors(containerColor = SalonInkSoft),
      border = CardDefaults.outlinedCardBorder().copy(brush = androidx.compose.ui.graphics.SolidColor(SalonBrass))
    ) {
      Column(
        modifier = Modifier
          .fillMaxWidth()
          .padding(20.dp)
          .verticalScroll(rememberScrollState())
      ) {
        // Header
        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.SpaceBetween,
          verticalAlignment = Alignment.CenterVertically
        ) {
          Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
              imageVector = Icons.Default.Public,
              contentDescription = null,
              tint = SalonBrass,
              modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
              text = "WEBSITE PUBLISHING GUIDE",
              fontSize = 18.sp,
              fontWeight = FontWeight.Bold,
              color = SalonBrass,
              letterSpacing = 1.sp
            )
          }

          IconButton(
            onClick = onDismiss,
            modifier = Modifier.testTag("btn_close_publish_dialog")
          ) {
            Icon(
              imageVector = Icons.Default.Close,
              contentDescription = "Close",
              tint = SalonPaperDim
            )
          }
        }

        Text(
          text = "Your website is built and ready for the internet! Follow these 3 simple steps to put it live on your own custom domain or free hosting.",
          fontSize = 13.sp,
          color = SalonPaperDim,
          lineHeight = 18.sp,
          modifier = Modifier.padding(vertical = 12.dp)
        )

        // Step 1
        PublishStepItem(
          stepNumber = "1",
          title = "Download / Export Website Files",
          description = "In the AI Studio top bar or settings, click 'Export as ZIP' or push to GitHub. The complete standalone website files are located in the '/website' folder (index.html, style.css, script.js)."
        )

        // Step 2
        PublishStepItem(
          stepNumber = "2",
          title = "Choose a Free Hosting Platform",
          description = "• Netlify Drop: Simply drag the 'website' folder into app.netlify.com/drop.\n• GitHub Pages: Host for free directly from your GitHub repository.\n• Vercel: Connect your GitHub repository or use 'npx vercel'.\n• Cloudflare Pages or Firebase Hosting."
        )

        // Step 3
        PublishStepItem(
          stepNumber = "3",
          title = "Custom Domain & WhatsApp Live",
          description = "Your booking buttons, WhatsApp appointment generator, Google Maps coordinates (Bogadi 2nd Stage, Mysuru), and phone dialers (+91 99018 17155) are pre-configured to work immediately once deployed!"
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Copy button
        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
          OutlinedButton(
            onClick = {
              val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
              clipboard.setPrimaryClip(ClipData.newPlainText("Website Publish Instructions", deployInstructions))
              copiedState.value = true
            },
            modifier = Modifier
              .weight(1f)
              .testTag("btn_copy_publish_steps"),
            colors = ButtonDefaults.outlinedButtonColors(contentColor = SalonBrass)
          ) {
            Icon(
              imageVector = if (copiedState.value) Icons.Default.CheckCircle else Icons.Default.ContentCopy,
              contentDescription = null,
              tint = if (copiedState.value) Color(0xFF4CAF50) else SalonBrass,
              modifier = Modifier.size(16.dp)
            )
            Spacer(modifier = Modifier.width(6.dp))
            Text(
              text = if (copiedState.value) "Copied Steps!" else "Copy Instructions",
              fontSize = 12.sp,
              fontWeight = FontWeight.SemiBold
            )
          }

          Button(
            onClick = onDismiss,
            modifier = Modifier
              .weight(1f)
              .testTag("btn_done_publish_dialog"),
            colors = ButtonDefaults.buttonColors(
              containerColor = SalonBrass,
              contentColor = SalonInk
            ),
            shape = RoundedCornerShape(8.dp)
          ) {
            Text(
              text = "Got It!",
              fontSize = 13.sp,
              fontWeight = FontWeight.Bold
            )
          }
        }
      }
    }
  }
}

@Composable
private fun PublishStepItem(
  stepNumber: String,
  title: String,
  description: String
) {
  Row(
    modifier = Modifier
      .fillMaxWidth()
      .padding(vertical = 8.dp)
      .background(SalonInkCard, RoundedCornerShape(10.dp))
      .border(1.dp, Color(0xFF33383F), RoundedCornerShape(10.dp))
      .padding(14.dp),
    verticalAlignment = Alignment.Top
  ) {
    Box(
      modifier = Modifier
        .size(28.dp)
        .background(SalonBrass, RoundedCornerShape(14.dp)),
      contentAlignment = Alignment.Center
    ) {
      Text(
        text = stepNumber,
        fontWeight = FontWeight.Bold,
        color = SalonInk,
        fontSize = 14.sp
      )
    }

    Spacer(modifier = Modifier.width(12.dp))

    Column(modifier = Modifier.weight(1f)) {
      Text(
        text = title,
        fontWeight = FontWeight.Bold,
        color = SalonPaper,
        fontSize = 14.sp
      )
      Spacer(modifier = Modifier.height(4.dp))
      Text(
        text = description,
        color = SalonPaperDim,
        fontSize = 12.sp,
        lineHeight = 17.sp
      )
    }
  }
}
