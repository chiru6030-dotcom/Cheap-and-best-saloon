package com.example

import androidx.lifecycle.ViewModel
import com.example.components.SalonSection
import com.example.data.Appointment
import com.example.data.BarberItem
import com.example.data.ReviewItem
import com.example.data.SalonData
import com.example.data.ServiceItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class SalonViewModel : ViewModel() {

  private val _activeSection = MutableStateFlow(SalonSection.HERO)
  val activeSection: StateFlow<SalonSection> = _activeSection.asStateFlow()

  private val _reviews = MutableStateFlow(SalonData.initialReviews)
  val reviews: StateFlow<List<ReviewItem>> = _reviews.asStateFlow()

  private val _appointments = MutableStateFlow<List<Appointment>>(emptyList())
  val appointments: StateFlow<List<Appointment>> = _appointments.asStateFlow()

  private val _isBookingDialogOpen = MutableStateFlow(false)
  val isBookingDialogOpen: StateFlow<Boolean> = _isBookingDialogOpen.asStateFlow()

  private val _isReviewDialogOpen = MutableStateFlow(false)
  val isReviewDialogOpen: StateFlow<Boolean> = _isReviewDialogOpen.asStateFlow()

  private val _isAppointmentsDialogOpen = MutableStateFlow(false)
  val isAppointmentsDialogOpen: StateFlow<Boolean> = _isAppointmentsDialogOpen.asStateFlow()

  private val _selectedService = MutableStateFlow<ServiceItem?>(null)
  val selectedService: StateFlow<ServiceItem?> = _selectedService.asStateFlow()

  private val _selectedBarber = MutableStateFlow<BarberItem?>(null)
  val selectedBarber: StateFlow<BarberItem?> = _selectedBarber.asStateFlow()

  fun setActiveSection(section: SalonSection) {
    _activeSection.value = section
  }

  fun openBooking(service: ServiceItem? = null, barber: BarberItem? = null) {
    _selectedService.value = service
    _selectedBarber.value = barber
    _isBookingDialogOpen.value = true
  }

  fun closeBooking() {
    _isBookingDialogOpen.value = false
    _selectedService.value = null
    _selectedBarber.value = null
  }

  fun openReviewDialog() {
    _isReviewDialogOpen.value = true
  }

  fun closeReviewDialog() {
    _isReviewDialogOpen.value = false
  }

  fun openAppointmentsDialog() {
    _isAppointmentsDialogOpen.value = true
  }

  fun closeAppointmentsDialog() {
    _isAppointmentsDialogOpen.value = false
  }

  fun addReview(review: ReviewItem) {
    _reviews.update { current -> listOf(review) + current }
    _isReviewDialogOpen.value = false
  }

  fun addAppointment(appointment: Appointment) {
    _appointments.update { current -> listOf(appointment) + current }
    closeBooking()
  }

  fun cancelAppointment(appointmentId: String) {
    _appointments.update { current -> current.filterNot { it.id == appointmentId } }
  }
}
