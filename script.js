// Cheap & Best Men's Salon - Interactive Script
document.addEventListener('DOMContentLoaded', () => {
  // Update current year dynamically
  const yearEl = document.getElementById('currentYear');
  if (yearEl) {
    yearEl.textContent = new Date().getFullYear();
  }

  // Mobile menu toggle
  const mobileToggle = document.getElementById('mobileMenuToggle');
  const navLinks = document.getElementById('navLinks');
  if (mobileToggle && navLinks) {
    mobileToggle.addEventListener('click', () => {
      navLinks.classList.toggle('mobile-active');
    });

    // Close menu when clicking nav item
    navLinks.querySelectorAll('a').forEach(link => {
      link.addEventListener('click', () => {
        navLinks.classList.remove('mobile-active');
      });
    });
  }

  // Star Rating Picker
  const starPicker = document.getElementById('starPicker');
  const ratingInput = document.getElementById('reviewRatingVal');
  if (starPicker && ratingInput) {
    const stars = starPicker.querySelectorAll('.pick-star');
    stars.forEach(star => {
      star.addEventListener('click', () => {
        const val = parseInt(star.getAttribute('data-val'), 10);
        ratingInput.value = val;
        stars.forEach(s => {
          const sVal = parseInt(s.getAttribute('data-val'), 10);
          if (sVal <= val) {
            s.classList.add('active');
          } else {
            s.classList.remove('active');
          }
        });
      });
    });
  }
});

// Toast notification helper
function showToast(message) {
  const toast = document.getElementById('toast');
  if (!toast) return;
  toast.textContent = message;
  toast.classList.add('show');
  setTimeout(() => {
    toast.classList.remove('show');
  }, 3500);
}

// Copy address to clipboard
function copyAddress() {
  const addressText = "Cheap & Best Men's Salon, No. 201/1A, Chamaraja Mohalla, Prashantha Nagara, Bogadi 2nd Stage, TK Layout, Mysuru, Karnataka 570026";
  if (navigator.clipboard && navigator.clipboard.writeText) {
    navigator.clipboard.writeText(addressText).then(() => {
      showToast("Address copied to clipboard!");
    }).catch(() => {
      fallbackCopy(addressText);
    });
  } else {
    fallbackCopy(addressText);
  }
}

function fallbackCopy(text) {
  const textArea = document.createElement("textarea");
  textArea.value = text;
  document.body.appendChild(textArea);
  textArea.select();
  try {
    document.execCommand('copy');
    showToast("Address copied to clipboard!");
  } catch (err) {
    showToast("Address: Bogadi 2nd Stage, TK Layout, Mysuru");
  }
  document.body.removeChild(textArea);
}

// Booking Modal Logic
function openBookingModal(preSelectedService, preSelectedBarber) {
  const modal = document.getElementById('bookingModal');
  if (!modal) return;

  if (preSelectedService) {
    const radio = document.querySelector(`input[name="service"][value*="${preSelectedService}"]`);
    if (radio) radio.checked = true;
  }

  if (preSelectedBarber) {
    const radio = document.querySelector(`input[name="barber"][value="${preSelectedBarber}"]`);
    if (radio) radio.checked = true;
  }

  modal.classList.add('active');
  document.body.style.overflow = 'hidden';
}

function closeBookingModal() {
  const modal = document.getElementById('bookingModal');
  if (modal) {
    modal.classList.remove('active');
    document.body.style.overflow = '';
  }
}

function handleBookingSubmit(e) {
  e.preventDefault();
  const service = document.querySelector('input[name="service"]:checked')?.value || 'Haircut';
  const barber = document.querySelector('input[name="barber"]:checked')?.value || 'Any Available';
  const day = document.querySelector('input[name="day"]:checked')?.value || 'Today';
  const time = document.querySelector('input[name="time"]:checked')?.value || 'Anytime';
  const name = document.getElementById('custName')?.value.trim() || 'Valued Customer';
  const phone = document.getElementById('custPhone')?.value.trim() || '';

  const message = `Hello Cheap & Best Salon, I would like to book an appointment:%0A%0A` +
    `✂️ *Service*: ${encodeURIComponent(service)}%0A` +
    `👤 *Preferred Barber*: ${encodeURIComponent(barber)}%0A` +
    `📅 *Day & Time*: ${encodeURIComponent(day)} at ${encodeURIComponent(time)}%0A` +
    `🧔 *Customer Name*: ${encodeURIComponent(name)}%0A` +
    `📞 *Phone*: ${encodeURIComponent(phone)}%0A%0A` +
    `Please confirm if this slot is available at the Bogadi 2nd Stage branch. Thank you!`;

  closeBookingModal();
  showToast("Opening WhatsApp to confirm your appointment...");

  setTimeout(() => {
    window.open(`https://wa.me/919901817155?text=${message}`, '_blank');
  }, 500);
}

// Review Modal Logic
function openReviewModal() {
  const modal = document.getElementById('reviewModal');
  if (modal) {
    modal.classList.add('active');
    document.body.style.overflow = 'hidden';
  }
}

function closeReviewModal() {
  const modal = document.getElementById('reviewModal');
  if (modal) {
    modal.classList.remove('active');
    document.body.style.overflow = '';
  }
}

function handleReviewSubmit(e) {
  e.preventDefault();
  const name = document.getElementById('reviewAuthor')?.value.trim() || 'Anonymous Regular';
  const text = document.getElementById('reviewText')?.value.trim() || '';
  const rating = parseInt(document.getElementById('reviewRatingVal')?.value || '5', 10);

  if (!text) return;

  const starsStr = '★'.repeat(rating) + '☆'.repeat(5 - rating);

  // Add review card to container
  const container = document.getElementById('reviewsContainer');
  if (container) {
    const card = document.createElement('div');
    card.className = 'review-card';
    card.style.animation = 'fadeIn 0.5s ease-out';
    card.innerHTML = `
      <div class="review-line"></div>
      <div class="review-content">
        <div class="review-stars-header">
          <span class="stars">${starsStr}</span>
          <span class="verified-tag">
            <svg width="12" height="12" viewBox="0 0 24 24" fill="currentColor"><path d="M9 16.17L4.83 12l-1.42 1.41L9 19 21 7l-1.41-1.41z"/></svg>
            Just Added
          </span>
        </div>
        <p class="review-quote">&ldquo;${escapeHtml(text)}&rdquo;</p>
        <div class="review-author-row">
          <strong class="author-name">${escapeHtml(name)}</strong>
          <span class="author-meta">Verified Guest · Bogadi 2nd Stage</span>
        </div>
      </div>
    `;
    container.prepend(card);
  }

  closeReviewModal();
  showToast("Thank you! Your review has been recorded.");

  // Reset form
  document.getElementById('reviewForm')?.reset();
}

function escapeHtml(str) {
  return str.replace(/[&<>'"]/g, tag => ({
    '&': '&amp;',
    '<': '&lt;',
    '>': '&gt;',
    "'": '&#39;',
    '"': '&quot;'
  }[tag] || tag));
}

// Close modals when clicking backdrop
window.addEventListener('click', (e) => {
  if (e.target.classList.contains('modal-backdrop')) {
    closeBookingModal();
    closeReviewModal();
  }
});
