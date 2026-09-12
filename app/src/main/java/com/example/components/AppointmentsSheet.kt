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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import com.example.data.Appointment
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
fun AppointmentsDialog(
  appointments: List<Appointment>,
  onCancelAppointment: (String) -> Unit,
  onDismiss: () -> Unit
) {
  AlertDialog(
    onDismissRequest = onDismiss,
    containerColor = SalonInkSoft,
    title = {
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Column {
          Text(
            text = "My Appointments",
            color = SalonPaper,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp
          )
          Text(
            text = "${appointments.size} booked session(s)",
            color = SalonBrassBright,
            fontSize = 12.sp
          )
        }
        IconButton(onClick = onDismiss) {
          Icon(imageVector = Icons.Default.Close, contentDescription = "Close", tint = SalonPaperDim)
        }
      }
    },
    text = {
      if (appointments.isEmpty()) {
        Box(
          modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 24.dp),
          contentAlignment = Alignment.Center
        ) {
          Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Icon(
              imageVector = Icons.Default.CalendarMonth,
              contentDescription = null,
              tint = SalonBrassBright,
              modifier = Modifier.size(36.dp)
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = "No appointments booked yet", color = SalonPaper, fontWeight = FontWeight.Bold, fontSize = 14.sp)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = "Choose a service to reserve your chair at Cheap & Best", color = SalonPaperDim, fontSize = 12.sp)
          }
        }
      } else {
        LazyColumn(
          modifier = Modifier.fillMaxWidth(),
          verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
          items(appointments, key = { it.id }) { appt ->
            AppointmentItemCard(
              appointment = appt,
              onCancel = { onCancelAppointment(appt.id) }
            )
          }
        }
      }
    },
    confirmButton = {
      OutlinedButton(
        onClick = onDismiss,
        colors = ButtonDefaults.outlinedButtonColors(contentColor = SalonPaper),
        shape = RoundedCornerShape(4.dp)
      ) {
        Text("CLOSE", color = SalonPaper, fontSize = 12.sp, fontWeight = FontWeight.Bold)
      }
    }
  )
}

@Composable
private fun AppointmentItemCard(
  appointment: Appointment,
  onCancel: () -> Unit
) {
  Box(
    modifier = Modifier
      .fillMaxWidth()
      .clip(RoundedCornerShape(6.dp))
      .background(SalonInkCard)
      .border(1.dp, SalonInkBorder, RoundedCornerShape(6.dp))
      .padding(12.dp)
      .testTag("appointment_card_${appointment.id}")
  ) {
    Column {
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Text(
          text = appointment.serviceName,
          color = SalonPaper,
          fontWeight = FontWeight.Bold,
          fontSize = 14.sp
        )
        Row(
          verticalAlignment = Alignment.CenterVertically,
          horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
          Icon(
            imageVector = Icons.Default.CheckCircle,
            contentDescription = null,
            tint = SalonBrassBright,
            modifier = Modifier.size(12.dp)
          )
          Text(
            text = appointment.status,
            color = SalonBrassBright,
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold
          )
        }
      }

      Spacer(modifier = Modifier.height(6.dp))

      Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(6.dp)
      ) {
        Icon(imageVector = Icons.Default.Person, contentDescription = null, tint = SalonPaperDim, modifier = Modifier.size(13.dp))
        Text(text = "Barber: ${appointment.barberName}", color = SalonPaperDim, fontSize = 12.sp)
      }

      Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(6.dp)
      ) {
        Icon(imageVector = Icons.Default.Schedule, contentDescription = null, tint = SalonPaperDim, modifier = Modifier.size(13.dp))
        Text(text = "${appointment.date} · ${appointment.timeSlot}", color = SalonPaperDim, fontSize = 12.sp)
      }

      Spacer(modifier = Modifier.height(8.dp))

      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.End
      ) {
        OutlinedButton(
          onClick = onCancel,
          colors = ButtonDefaults.outlinedButtonColors(
            contentColor = SalonRed,
            containerColor = SalonInk
          ),
          border = ButtonDefaults.outlinedButtonBorder.copy(brush = androidx.compose.ui.graphics.SolidColor(SalonRed.copy(alpha = 0.5f))),
          shape = RoundedCornerShape(4.dp),
          modifier = Modifier.height(32.dp)
        ) {
          Icon(imageVector = Icons.Default.Cancel, contentDescription = null, tint = SalonRed, modifier = Modifier.size(12.dp))
          Spacer(modifier = Modifier.width(4.dp))
          Text(text = "CANCEL", color = SalonRed, fontSize = 10.sp, fontWeight = FontWeight.Bold)
        }
      }
    }
  }
}
