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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Chat
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.data.Appointment
import com.example.data.BarberItem
import com.example.data.ReviewItem
import com.example.data.SalonData
import com.example.data.ServiceItem
import com.example.ui.theme.SalonBrass
import com.example.ui.theme.SalonBrassBright
import com.example.ui.theme.SalonGold
import com.example.ui.theme.SalonInk
import com.example.ui.theme.SalonInkBorder
import com.example.ui.theme.SalonInkCard
import com.example.ui.theme.SalonInkSoft
import com.example.ui.theme.SalonPaper
import com.example.ui.theme.SalonPaperDim
import java.util.UUID

@Composable
fun BookingModalDialog(
  initialService: ServiceItem?,
  initialBarber: BarberItem?,
  onDismiss: () -> Unit,
  onConfirmWhatsApp: (service: String, barber: String, date: String, time: String, name: String, phone: String) -> Unit,
  onSaveInApp: (Appointment) -> Unit
) {
  var selectedService by remember { mutableStateOf(initialService?.title ?: SalonData.services.first().title) }
  var selectedBarber by remember { mutableStateOf(initialBarber?.name ?: "Any Available Stylist") }
  var selectedDate by remember { mutableStateOf("Today") }
  var selectedTime by remember { mutableStateOf("4:00 PM") }
  var customerName by remember { mutableStateOf("") }
  var customerPhone by remember { mutableStateOf("") }

  val dateOptions = listOf("Today", "Tomorrow", "Weekend", "Select Later")
  val timeOptions = listOf("10:00 AM", "12:00 PM", "2:30 PM", "4:00 PM", "6:30 PM", "8:30 PM")
  val barberOptions = listOf("Any Available Stylist", "Thanish", "Shanu", "Kasim")

  Dialog(onDismissRequest = onDismiss) {
    Box(
      modifier = Modifier
        .fillMaxWidth()
        .clip(RoundedCornerShape(12.dp))
        .background(SalonInkSoft)
        .border(1.dp, SalonBrass.copy(alpha = 0.5f), RoundedCornerShape(12.dp))
        .padding(20.dp)
        .testTag("booking_modal_dialog")
    ) {
      Column(
        modifier = Modifier
          .fillMaxWidth()
          .verticalScroll(rememberScrollState())
      ) {
        // Header
        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.SpaceBetween,
          verticalAlignment = Alignment.CenterVertically
        ) {
          Column {
            Text(
              text = "BOOK APPOINTMENT",
              color = SalonBrassBright,
              fontSize = 12.sp,
              fontWeight = FontWeight.Bold,
              letterSpacing = 1.sp
            )
            Text(
              text = "Skip the wait in Bogadi",
              color = SalonPaper,
              fontSize = 18.sp,
              fontWeight = FontWeight.Bold
            )
          }
          IconButton(
            onClick = onDismiss,
            modifier = Modifier.size(32.dp)
          ) {
            Icon(imageVector = Icons.Default.Close, contentDescription = "Close dialog", tint = SalonPaperDim)
          }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // 1. Choose Service
        Text(text = "1. CHOOSE SERVICE", color = SalonBrassBright, fontSize = 11.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(6.dp))
        Row(
          modifier = Modifier
            .fillMaxWidth()
            .horizontalScroll(rememberScrollState()),
          horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
          SalonData.services.forEach { s ->
            val isSelected = selectedService == s.title
            Box(
              modifier = Modifier
                .clip(RoundedCornerShape(6.dp))
                .background(if (isSelected) SalonBrass else SalonInkCard)
                .border(1.dp, if (isSelected) SalonBrassBright else SalonInkBorder, RoundedCornerShape(6.dp))
                .clickable(role = Role.RadioButton) { selectedService = s.title }
                .padding(horizontal = 10.dp, vertical = 6.dp)
            ) {
              Text(
                text = "${s.title} (${s.price})",
                color = if (isSelected) SalonInk else SalonPaper,
                fontSize = 12.sp,
                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
              )
            }
          }
        }

        Spacer(modifier = Modifier.height(14.dp))

        // 2. Choose Barber
        Text(text = "2. PREFERRED BARBER", color = SalonBrassBright, fontSize = 11.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(6.dp))
        Row(
          modifier = Modifier
            .fillMaxWidth()
            .horizontalScroll(rememberScrollState()),
          horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
          barberOptions.forEach { b ->
            val isSelected = selectedBarber == b
            Box(
              modifier = Modifier
                .clip(RoundedCornerShape(6.dp))
                .background(if (isSelected) SalonBrass else SalonInkCard)
                .border(1.dp, if (isSelected) SalonBrassBright else SalonInkBorder, RoundedCornerShape(6.dp))
                .clickable(role = Role.RadioButton) { selectedBarber = b }
                .padding(horizontal = 10.dp, vertical = 6.dp)
            ) {
              Text(
                text = b,
                color = if (isSelected) SalonInk else SalonPaper,
                fontSize = 12.sp,
                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
              )
            }
          }
        }

        Spacer(modifier = Modifier.height(14.dp))

        // 3. Choose Date & Time
        Text(text = "3. DATE & TIME", color = SalonBrassBright, fontSize = 11.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(6.dp))
        Row(
          modifier = Modifier
            .fillMaxWidth()
            .horizontalScroll(rememberScrollState()),
          horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
          dateOptions.forEach { d ->
            val isSelected = selectedDate == d
            Box(
              modifier = Modifier
                .clip(RoundedCornerShape(4.dp))
                .background(if (isSelected) SalonBrass else SalonInkCard)
                .border(1.dp, if (isSelected) SalonBrassBright else SalonInkBorder, RoundedCornerShape(4.dp))
                .clickable(role = Role.RadioButton) { selectedDate = d }
                .padding(horizontal = 8.dp, vertical = 5.dp)
            ) {
              Text(
                text = d,
                color = if (isSelected) SalonInk else SalonPaper,
                fontSize = 11.sp,
                fontWeight = FontWeight.Medium
              )
            }
          }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Row(
          modifier = Modifier
            .fillMaxWidth()
            .horizontalScroll(rememberScrollState()),
          horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
          timeOptions.forEach { t ->
            val isSelected = selectedTime == t
            Box(
              modifier = Modifier
                .clip(RoundedCornerShape(4.dp))
                .background(if (isSelected) SalonBrass else SalonInkCard)
                .border(1.dp, if (isSelected) SalonBrassBright else SalonInkBorder, RoundedCornerShape(4.dp))
                .clickable(role = Role.RadioButton) { selectedTime = t }
                .padding(horizontal = 8.dp, vertical = 5.dp)
            ) {
              Text(
                text = t,
                color = if (isSelected) SalonInk else SalonPaper,
                fontSize = 11.sp,
                fontWeight = FontWeight.Medium
              )
            }
          }
        }

        Spacer(modifier = Modifier.height(14.dp))

        // 4. Contact Inputs
        Text(text = "4. YOUR DETAILS", color = SalonBrassBright, fontSize = 11.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(6.dp))

        OutlinedTextField(
          value = customerName,
          onValueChange = { customerName = it },
          label = { Text("Your Name", color = SalonPaperDim) },
          singleLine = true,
          modifier = Modifier
            .fillMaxWidth()
            .testTag("input_customer_name"),
          colors = OutlinedTextFieldDefaults.colors(
            focusedTextColor = SalonPaper,
            unfocusedTextColor = SalonPaper,
            focusedBorderColor = SalonBrass,
            unfocusedBorderColor = SalonInkBorder
          )
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
          value = customerPhone,
          onValueChange = { customerPhone = it },
          label = { Text("Phone Number", color = SalonPaperDim) },
          singleLine = true,
          modifier = Modifier
            .fillMaxWidth()
            .testTag("input_customer_phone"),
          colors = OutlinedTextFieldDefaults.colors(
            focusedTextColor = SalonPaper,
            unfocusedTextColor = SalonPaper,
            focusedBorderColor = SalonBrass,
            unfocusedBorderColor = SalonInkBorder
          )
        )

        Spacer(modifier = Modifier.height(20.dp))

        // Actions: WhatsApp & In-App Save
        ElevatedButton(
          onClick = {
            val name = if (customerName.isNotBlank()) customerName else "Customer"
            val phone = if (customerPhone.isNotBlank()) customerPhone else "Not provided"
            onConfirmWhatsApp(selectedService, selectedBarber, selectedDate, selectedTime, name, phone)
            onSaveInApp(
              Appointment(
                id = UUID.randomUUID().toString(),
                serviceName = selectedService,
                barberName = selectedBarber,
                date = selectedDate,
                timeSlot = selectedTime,
                customerName = name,
                customerPhone = phone
              )
            )
          },
          colors = ButtonDefaults.elevatedButtonColors(
            containerColor = SalonBrass,
            contentColor = SalonInk
          ),
          shape = RoundedCornerShape(4.dp),
          modifier = Modifier
            .fillMaxWidth()
            .height(46.dp)
            .testTag("btn_confirm_whatsapp_booking")
        ) {
          Icon(imageVector = Icons.Default.Chat, contentDescription = null, tint = SalonInk, modifier = Modifier.size(16.dp))
          Spacer(modifier = Modifier.width(6.dp))
          Text(text = "BOOK VIA WHATSAPP (INSTANT)", fontSize = 12.sp, fontWeight = FontWeight.Bold)
        }

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedButton(
          onClick = {
            val name = if (customerName.isNotBlank()) customerName else "Customer"
            val phone = if (customerPhone.isNotBlank()) customerPhone else "Not provided"
            onSaveInApp(
              Appointment(
                id = UUID.randomUUID().toString(),
                serviceName = selectedService,
                barberName = selectedBarber,
                date = selectedDate,
                timeSlot = selectedTime,
                customerName = name,
                customerPhone = phone
              )
            )
          },
          colors = ButtonDefaults.outlinedButtonColors(
            contentColor = SalonPaper,
            containerColor = SalonInkCard
          ),
          border = ButtonDefaults.outlinedButtonBorder.copy(brush = androidx.compose.ui.graphics.SolidColor(SalonInkBorder)),
          shape = RoundedCornerShape(4.dp),
          modifier = Modifier
            .fillMaxWidth()
            .height(44.dp)
            .testTag("btn_save_appointment_locally")
        ) {
          Icon(imageVector = Icons.Default.Check, contentDescription = null, tint = SalonBrassBright, modifier = Modifier.size(16.dp))
          Spacer(modifier = Modifier.width(6.dp))
          Text(text = "CONFIRM & SAVE IN APP", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = SalonPaper)
        }
      }
    }
  }
}

@Composable
fun WriteReviewDialog(
  onDismiss: () -> Unit,
  onSubmitReview: (ReviewItem) -> Unit
) {
  var authorName by remember { mutableStateOf("") }
  var rating by remember { mutableStateOf(5) }
  var reviewComment by remember { mutableStateOf("") }

  AlertDialog(
    onDismissRequest = onDismiss,
    containerColor = SalonInkSoft,
    title = {
      Text(
        text = "Write a Review",
        color = SalonPaper,
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp
      )
    },
    text = {
      Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(12.dp)
      ) {
        Text(
          text = "Help your neighbours in Bogadi know what to expect.",
          color = SalonPaperDim,
          fontSize = 12.sp
        )

        // Star selection
        Row(
          horizontalArrangement = Arrangement.spacedBy(4.dp),
          verticalAlignment = Alignment.CenterVertically
        ) {
          for (i in 1..5) {
            Icon(
              imageVector = Icons.Default.Star,
              contentDescription = "Rating $i stars",
              tint = if (i <= rating) SalonGold else SalonInkBorder,
              modifier = Modifier
                .size(28.dp)
                .clickable(role = Role.Button) { rating = i }
            )
          }
        }

        OutlinedTextField(
          value = authorName,
          onValueChange = { authorName = it },
          label = { Text("Your Name", color = SalonPaperDim) },
          singleLine = true,
          modifier = Modifier.fillMaxWidth(),
          colors = OutlinedTextFieldDefaults.colors(
            focusedTextColor = SalonPaper,
            unfocusedTextColor = SalonPaper,
            focusedBorderColor = SalonBrass,
            unfocusedBorderColor = SalonInkBorder
          )
        )

        OutlinedTextField(
          value = reviewComment,
          onValueChange = { reviewComment = it },
          label = { Text("Your Experience", color = SalonPaperDim) },
          modifier = Modifier
            .fillMaxWidth()
            .height(100.dp),
          colors = OutlinedTextFieldDefaults.colors(
            focusedTextColor = SalonPaper,
            unfocusedTextColor = SalonPaper,
            focusedBorderColor = SalonBrass,
            unfocusedBorderColor = SalonInkBorder
          )
        )
      }
    },
    confirmButton = {
      ElevatedButton(
        onClick = {
          if (reviewComment.isNotBlank()) {
            val item = ReviewItem(
              id = UUID.randomUUID().toString(),
              author = if (authorName.isNotBlank()) authorName else "Mysuru Regular",
              meta = "Verified customer · Just now",
              quote = "\"${reviewComment.trim()}\"",
              stars = rating,
              verified = true
            )
            onSubmitReview(item)
          }
        },
        colors = ButtonDefaults.elevatedButtonColors(
          containerColor = SalonBrass,
          contentColor = SalonInk
        ),
        shape = RoundedCornerShape(4.dp)
      ) {
        Text("SUBMIT REVIEW", fontWeight = FontWeight.Bold, fontSize = 12.sp)
      }
    },
    dismissButton = {
      OutlinedButton(
        onClick = onDismiss,
        colors = ButtonDefaults.outlinedButtonColors(contentColor = SalonPaper),
        shape = RoundedCornerShape(4.dp)
      ) {
        Text("CANCEL", color = SalonPaperDim, fontSize = 12.sp)
      }
    }
  )
}
