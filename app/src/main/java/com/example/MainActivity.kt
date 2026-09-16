package com.example

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.PhoneAndroid
import androidx.compose.material.icons.filled.RocketLaunch
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedFilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import com.example.components.AppointmentsDialog
import com.example.components.BarberPoleStripeRule
import com.example.components.BarbersSection
import com.example.components.BookingModalDialog
import com.example.components.FinalCtaSection
import com.example.components.HeroSection
import com.example.components.LocationSection
import com.example.components.PublishGuideDialog
import com.example.components.ReviewsSection
import com.example.components.SalonFooter
import com.example.components.SalonNavBar
import com.example.components.SalonSection
import com.example.components.ServicesSection
import com.example.components.TrustStrip
import com.example.components.WebsiteWebView
import com.example.components.WriteReviewDialog
import com.example.data.SalonData
import com.example.ui.theme.SalonBrassBright
import com.example.ui.theme.SalonInkSoft
import com.example.ui.theme.SalonPaper
import com.example.ui.theme.SalonPaperDim
import com.example.ui.theme.CheapAndBestTheme
import com.example.ui.theme.SalonBrass
import com.example.ui.theme.SalonInk
import com.example.ui.theme.SalonRed
import kotlinx.coroutines.launch
import java.net.URLEncoder

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContent {
      CheapAndBestTheme {
        SalonApp()
      }
    }
  }
}

@Composable
fun SalonApp(viewModel: SalonViewModel = viewModel()) {
  val context = LocalContext.current
  val listState = rememberLazyListState()
  val coroutineScope = rememberCoroutineScope()
  val snackbarHostState = remember { SnackbarHostState() }

  val activeSection by viewModel.activeSection.collectAsState()
  val reviews by viewModel.reviews.collectAsState()
  val appointments by viewModel.appointments.collectAsState()
  val isBookingOpen by viewModel.isBookingDialogOpen.collectAsState()
  val isReviewOpen by viewModel.isReviewDialogOpen.collectAsState()
  val isAppointmentsOpen by viewModel.isAppointmentsDialogOpen.collectAsState()
  val selectedService by viewModel.selectedService.collectAsState()
  val selectedBarber by viewModel.selectedBarber.collectAsState()

  val isWebsiteMode = remember { mutableStateOf(false) }
  val showPublishGuide = remember { mutableStateOf(false) }

  // Helper Intent Handlers
  fun dialSalonPhone() {
    try {
      val intent = Intent(Intent.ACTION_DIAL).apply {
        data = Uri.parse(SalonData.PHONE_CALL_URI)
      }
      context.startActivity(intent)
    } catch (e: Exception) {
      Toast.makeText(context, "Call: ${SalonData.PHONE_DISPLAY}", Toast.LENGTH_LONG).show()
    }
  }

  fun openWhatsApp(message: String = "Hi, I'd like to book an appointment at Cheap and Best Men's Salon") {
    try {
      val encodedMsg = URLEncoder.encode(message, "UTF-8")
      val uri = Uri.parse("https://wa.me/${SalonData.WHATSAPP_NUMBER}?text=$encodedMsg")
      val intent = Intent(Intent.ACTION_VIEW, uri)
      context.startActivity(intent)
    } catch (e: Exception) {
      Toast.makeText(context, "WhatsApp: ${SalonData.PHONE_DISPLAY}", Toast.LENGTH_LONG).show()
    }
  }

  fun openGoogleMaps() {
    try {
      val mapUri = Uri.parse("geo:0,0?q=" + Uri.encode("Cheap and Best Men's Salon Bogadi 2nd Stage Mysuru"))
      val mapIntent = Intent(Intent.ACTION_VIEW, mapUri)
      if (mapIntent.resolveActivity(context.packageManager) != null) {
        context.startActivity(mapIntent)
      } else {
        context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(SalonData.MAPS_QUERY_URL)))
      }
    } catch (e: Exception) {
      context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(SalonData.MAPS_QUERY_URL)))
    }
  }

  fun openSocialWebLink(url: String) {
    try {
      val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
      context.startActivity(intent)
    } catch (e: Exception) {
      Toast.makeText(context, "Could not open link: $url", Toast.LENGTH_SHORT).show()
    }
  }

  fun copyAddressToClipboard() {
    val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
    val clip = ClipData.newPlainText("Salon Address", SalonData.ADDRESS)
    clipboard.setPrimaryClip(clip)
    coroutineScope.launch {
      snackbarHostState.showSnackbar("Address copied to clipboard! Ready to paste.")
    }
  }

  // Section Index mapping for smooth scrolling
  // Indices:
  // 0: Hero
  // 1: Stripe Rule
  // 2: Trust Strip
  // 3: Services Section
  // 4: Stripe Rule
  // 5: Barbers Section
  // 6: Reviews Section
  // 7: Stripe Rule
  // 8: Location Section
  // 9: Final CTA Section
  // 10: Footer
  fun scrollToSection(section: SalonSection) {
    viewModel.setActiveSection(section)
    val targetIndex = when (section) {
      SalonSection.HERO -> 0
      SalonSection.SERVICES -> 3
      SalonSection.BARBERS -> 5
      SalonSection.REVIEWS -> 6
      SalonSection.LOCATION -> 8
      SalonSection.BOOKING -> {
        viewModel.openBooking()
        3
      }
    }
    coroutineScope.launch {
      listState.animateScrollToItem(targetIndex)
    }
  }

  Scaffold(
    modifier = Modifier.fillMaxSize(),
    containerColor = SalonInk,
    topBar = {
      Column {
        // Mode Switcher & Publish Guide Banner
        Row(
          modifier = Modifier
            .fillMaxWidth()
            .background(SalonInkSoft)
            .padding(horizontal = 12.dp, vertical = 6.dp),
          verticalAlignment = Alignment.CenterVertically,
          horizontalArrangement = Arrangement.SpaceBetween
        ) {
          Row(verticalAlignment = Alignment.CenterVertically) {
            ElevatedFilterChip(
              selected = isWebsiteMode.value,
              onClick = { isWebsiteMode.value = true },
              label = {
                Row(verticalAlignment = Alignment.CenterVertically) {
                  Icon(
                    imageVector = Icons.Default.Language,
                    contentDescription = null,
                    modifier = Modifier.size(14.dp)
                  )
                  Spacer(modifier = Modifier.width(4.dp))
                  Text(
                    text = "Web Site",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold
                  )
                }
              },
              colors = FilterChipDefaults.elevatedFilterChipColors(
                selectedContainerColor = SalonBrass,
                selectedLabelColor = SalonInk,
                selectedLeadingIconColor = SalonInk,
                containerColor = SalonInk,
                labelColor = SalonPaperDim
              ),
              modifier = Modifier.testTag("tab_website_mode")
            )

            Spacer(modifier = Modifier.width(6.dp))

            ElevatedFilterChip(
              selected = !isWebsiteMode.value,
              onClick = { isWebsiteMode.value = false },
              label = {
                Row(verticalAlignment = Alignment.CenterVertically) {
                  Icon(
                    imageVector = Icons.Default.PhoneAndroid,
                    contentDescription = null,
                    modifier = Modifier.size(14.dp)
                  )
                  Spacer(modifier = Modifier.width(4.dp))
                  Text(
                    text = "App Mode",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold
                  )
                }
              },
              colors = FilterChipDefaults.elevatedFilterChipColors(
                selectedContainerColor = SalonBrass,
                selectedLabelColor = SalonInk,
                selectedLeadingIconColor = SalonInk,
                containerColor = SalonInk,
                labelColor = SalonPaperDim
              ),
              modifier = Modifier.testTag("tab_native_mode")
            )
          }

          OutlinedButton(
            onClick = { showPublishGuide.value = true },
            colors = ButtonDefaults.outlinedButtonColors(contentColor = SalonBrassBright),
            modifier = Modifier
              .height(32.dp)
              .testTag("btn_open_publish_guide")
          ) {
            Icon(
              imageVector = Icons.Default.RocketLaunch,
              contentDescription = null,
              tint = SalonBrassBright,
              modifier = Modifier.size(13.dp)
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
              text = "How to Publish",
              fontSize = 10.sp,
              fontWeight = FontWeight.Bold
            )
          }
        }

        if (!isWebsiteMode.value) {
          SalonNavBar(
            activeSection = activeSection,
            onSectionSelected = { section -> scrollToSection(section) },
            onCallNow = { dialSalonPhone() }
          )
        }
      }
    },
    snackbarHost = {
      SnackbarHost(hostState = snackbarHostState)
    },
    floatingActionButton = {
      if (!isWebsiteMode.value) {
        ExtendedFloatingActionButton(
          onClick = {
            if (appointments.isNotEmpty()) {
              viewModel.openAppointmentsDialog()
            } else {
              viewModel.openBooking()
            }
          },
          containerColor = SalonBrass,
          contentColor = SalonInk,
          shape = RoundedCornerShape(8.dp),
          modifier = Modifier.testTag("fab_booking_action")
        ) {
          if (appointments.isNotEmpty()) {
            BadgedBox(
              badge = {
                Badge(containerColor = SalonRed) {
                  Text(
                    text = "${appointments.size}",
                    color = Color.White,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold
                  )
                }
              }
            ) {
              Icon(
                imageVector = Icons.Default.CalendarMonth,
                contentDescription = "My Appointments",
                tint = SalonInk,
                modifier = Modifier.size(20.dp)
              )
            }
            Text(
              text = "  MY BOOKINGS (${appointments.size})",
              fontWeight = FontWeight.Bold,
              fontSize = 12.sp,
              letterSpacing = 0.5.sp
            )
          } else {
            Icon(
              imageVector = Icons.Default.CalendarMonth,
              contentDescription = "Book Appointment",
              tint = SalonInk,
              modifier = Modifier.size(20.dp)
            )
            Text(
              text = "  RESERVE CHAIR",
              fontWeight = FontWeight.Bold,
              fontSize = 12.sp,
              letterSpacing = 0.5.sp
            )
          }
        }
      }
    }
  ) { innerPadding ->
    Box(
      modifier = Modifier
        .fillMaxSize()
        .background(SalonInk)
        .padding(innerPadding)
    ) {
      if (isWebsiteMode.value) {
        WebsiteWebView()
      } else {
        LazyColumn(
          state = listState,
          modifier = Modifier
            .fillMaxSize()
            .testTag("main_salon_scroll_list")
        ) {
          // Item 0: Hero Section
          item(key = "hero") {
            HeroSection(
              onBookAppointment = { viewModel.openBooking() },
              onWhatsAppDirect = { openWhatsApp("Hi, I'd like to book an appointment at Cheap and Best Men's Salon, Bogadi 2nd Stage") },
              onCallNow = { dialSalonPhone() },
              onGetDirections = { openGoogleMaps() }
            )
          }

          // Item 1: Barber Stripe Rule
          item(key = "stripe_rule_1") {
            BarberPoleStripeRule()
          }

          // Item 2: Trust Strip
          item(key = "trust_strip") {
            TrustStrip()
          }

          // Item 3: Services Section
          item(key = "services") {
            ServicesSection(
              onSelectServiceForBooking = { service ->
                viewModel.openBooking(service = service)
              }
            )
          }

          // Item 4: Barber Stripe Rule
          item(key = "stripe_rule_2") {
            BarberPoleStripeRule()
          }

          // Item 5: Barbers Section
          item(key = "barbers") {
            BarbersSection(
              onSelectBarberForBooking = { barber ->
                viewModel.openBooking(barber = barber)
              }
            )
          }

          // Item 6: Reviews Section
          item(key = "reviews") {
            ReviewsSection(
              reviews = reviews,
              onOpenWriteReview = { viewModel.openReviewDialog() }
            )
          }

          // Item 7: Barber Stripe Rule
          item(key = "stripe_rule_3") {
            BarberPoleStripeRule()
          }

          // Item 8: Location Section
          item(key = "location") {
            LocationSection(
              onOpenMap = { openGoogleMaps() },
              onCallPhone = { dialSalonPhone() },
              onCopyAddress = { copyAddressToClipboard() }
            )
          }

          // Item 9: Final CTA Section
          item(key = "final_cta") {
            FinalCtaSection(
              onBookWhatsApp = { openWhatsApp("Hi Cheap and Best, I'd like to book a chair today at Bogadi 2nd Stage") },
              onCallSalon = { dialSalonPhone() }
            )
          }

          // Item 10: Comprehensive Footer (Responsive navigation, Social links, Contact info, Copyright updates)
          item(key = "footer") {
            SalonFooter(
              onNavigateSection = { section -> scrollToSection(section) },
              onOpenSocialLink = { social -> openSocialWebLink(social.url) },
              onCallPhone = { dialSalonPhone() },
              onOpenWhatsApp = { openWhatsApp("Hi, inquiring from Cheap and Best app") },
              onOpenMap = { openGoogleMaps() }
            )
          }
        }
      }
    }
  }

  if (showPublishGuide.value) {
    PublishGuideDialog(
      onDismiss = { showPublishGuide.value = false }
    )
  }

  // Dialogs
  if (isBookingOpen) {
    BookingModalDialog(
      initialService = selectedService,
      initialBarber = selectedBarber,
      onDismiss = { viewModel.closeBooking() },
      onConfirmWhatsApp = { s, b, d, t, name, phone ->
        val msg = "Hi Cheap & Best Salon, I'd like to book an appointment for $s with $b on $d at $t. Name: $name, Phone: $phone."
        openWhatsApp(msg)
      },
      onSaveInApp = { appt ->
        viewModel.addAppointment(appt)
        coroutineScope.launch {
          snackbarHostState.showSnackbar("Appointment saved! ${appt.serviceName} on ${appt.date} at ${appt.timeSlot}.")
        }
      }
    )
  }

  if (isReviewOpen) {
    WriteReviewDialog(
      onDismiss = { viewModel.closeReviewDialog() },
      onSubmitReview = { rev ->
        viewModel.addReview(rev)
        coroutineScope.launch {
          snackbarHostState.showSnackbar("Thank you, ${rev.author}! Your review was published.")
        }
      }
    )
  }

  if (isAppointmentsOpen) {
    AppointmentsDialog(
      appointments = appointments,
      onCancelAppointment = { id ->
        viewModel.cancelAppointment(id)
        coroutineScope.launch {
          snackbarHostState.showSnackbar("Appointment cancelled.")
        }
      },
      onDismiss = { viewModel.closeAppointmentsDialog() }
    )
  }
}
