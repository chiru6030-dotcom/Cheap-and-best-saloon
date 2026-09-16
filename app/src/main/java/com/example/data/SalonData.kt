package com.example.data

data class ServiceItem(
  val id: String,
  val number: String,
  val title: String,
  val description: String,
  val price: String,
  val duration: String,
  val iconName: String
)

data class BarberItem(
  val id: String,
  val name: String,
  val specialty: String,
  val tag: String,
  val quote: String,
  val experience: String,
  val rating: Double = 5.0
)

data class ReviewItem(
  val id: String,
  val author: String,
  val meta: String,
  val quote: String,
  val stars: Int = 5,
  val verified: Boolean = true
)

data class SocialLink(
  val platform: String,
  val handle: String,
  val url: String,
  val iconType: String
)

data class Appointment(
  val id: String,
  val serviceName: String,
  val barberName: String,
  val date: String,
  val timeSlot: String,
  val customerName: String,
  val customerPhone: String,
  val status: String = "Confirmed"
)

object SalonData {
  const val SALON_NAME = "Cheap & Best Men's Salon"
  const val SALON_TAGLINE = "Sharp cuts, fair prices, and a shop that treats you right"
  const val SALON_SUBTITLE = "A neighbourhood barbershop in Bogadi 2nd Stage where regulars come back for the haircut and stay for the welcome. Clean chairs, friendly hands, honest pricing — every single time."
  const val RATING = "5.0"
  const val REVIEW_COUNT = "65"
  const val PHONE_DISPLAY = "099018 17155"
  const val PHONE_CALL_URI = "tel:+919901817155"
  const val WHATSAPP_NUMBER = "919901817155"
  const val ADDRESS = "No. 201/1A, Chamaraja Mohalla, Prashantha Nagara, Bogadi 2nd Stage, TK Layout, Mysuru, Karnataka 570026"
  const val PLUS_CODE = "8J26+XC Mysuru, Karnataka"
  const val HOURS = "Open daily · 8:30 am – 10:00 pm"
  const val MAPS_QUERY_URL = "https://www.google.com/maps/search/?api=1&query=Cheap+and+Best+Men%27s+Salon+Bogadi+2nd+Stage+Mysuru"

  val trustStats = listOf(
    Pair("5", "reviews mention the friendly staff by name"),
    Pair("65", "five-star reviews on Google, zero below"),
    Pair("10pm", "open late so a haircut never has to wait till the weekend"),
    Pair("₹", "honest, affordable pricing customers keep pointing out")
  )

  val services = listOf(
    ServiceItem(
      id = "s1",
      number = "01",
      title = "Haircuts",
      description = "Classic and modern styles, clipper or scissor work, finished with a proper clean-up around the neck and ears.",
      price = "₹100",
      duration = "30 mins",
      iconName = "scissors"
    ),
    ServiceItem(
      id = "s2",
      number = "02",
      title = "Beard styling",
      description = "Shaping, trimming and line-ups that hold their edge, from a light tidy-up to a full redesign.",
      price = "₹50",
      duration = "20 mins",
      iconName = "beard"
    ),
    ServiceItem(
      id = "s3",
      number = "03",
      title = "Wash & grooming",
      description = "Hair wash and cleansing service customers specifically call out as gentle and thorough — not rushed.",
      price = "₹70",
      duration = "25 mins",
      iconName = "wash"
    ),
    ServiceItem(
      id = "s4",
      number = "04",
      title = "Bridal & wedding styling",
      description = "Groom-day grooming packages — the shop customers now specifically recommend for weddings.",
      price = "₹449",
      duration = "60 mins",
      iconName = "wedding"
    ),
    ServiceItem(
      id = "s5",
      number = "05",
      title = "Head Massage & Oil Treatment",
      description = "Deep soothing scalp relaxation with nourishing Ayurvedic herbal oil to relieve tension and stress.",
      price = "₹150",
      duration = "30 mins",
      iconName = "spa"
    ),
    ServiceItem(
      id = "s6",
      number = "06",
      title = "Royal Hot Towel Shave",
      description = "Warm lather, precision straight-blade shave, aromatic hot towel compress, and calming aftershave balm.",
      price = "₹80",
      duration = "25 mins",
      iconName = "shave"
    )
  )

  val barbers = listOf(
    BarberItem(
      id = "b1",
      name = "Thanish",
      specialty = "Classic Fades & Modern Cuts",
      tag = "Haircuts",
      quote = "\"The guy named Thanish, he do the haircut very well\" — called out by name for a haircut customers described as one of their best experiences yet.",
      experience = "6+ Years Exp",
      rating = 5.0
    ),
    BarberItem(
      id = "b2",
      name = "Shanu",
      specialty = "Hair Styling & Texture",
      tag = "Hair styling",
      quote = "Named directly by a regular as \"the best hair dresser,\" praised alongside the shop's overall friendly, reliable service.",
      experience = "8+ Years Exp",
      rating = 5.0
    ),
    BarberItem(
      id = "b3",
      name = "Kasim",
      specialty = "Beard Sculpting & Grooming",
      tag = "Grooming",
      quote = "Thanked by name for good service at an affordable price — the exact combination this shop was built around.",
      experience = "5+ Years Exp",
      rating = 5.0
    )
  )

  val initialReviews = listOf(
    ReviewItem(
      id = "r1",
      author = "Praveen N V",
      meta = "Local Guide · 4 reviews · 3 photos",
      quote = "\"Hands down the best men's salon in town! Super clean, great vibe, and top-notch attention to detail. Always leave looking sharp.\"",
      stars = 5
    ),
    ReviewItem(
      id = "r2",
      author = "Nagananda Hebbar",
      meta = "Local Guide · 20 reviews",
      quote = "\"Great salon! It's budget-friendly and offers good service. The place is neat, clean, and well-maintained. The staff are polite.\"",
      stars = 5
    ),
    ReviewItem(
      id = "r3",
      author = "Monish Arya",
      meta = "1 review · 1 photo",
      quote = "\"Best and affordable haircut shop near me — the staff are friendly and well maintained and hygienic. Good service.\"",
      stars = 5
    ),
    ReviewItem(
      id = "r4",
      author = "Suhas Bharadwaj",
      meta = "Local Guide · 15 reviews",
      quote = "\"Best spot in Bogadi 2nd Stage. Zero unnecessary upselling, just precision styling with polite barbers. Open until 10 PM is a blessing.\"",
      stars = 5
    )
  )

  val socialLinks = listOf(
    SocialLink(
      platform = "WhatsApp",
      handle = "+91 99018 17155",
      url = "https://wa.me/919901817155?text=Hi%2C%20I%27d%20like%20to%20book%20an%20appointment",
      iconType = "whatsapp"
    ),
    SocialLink(
      platform = "Instagram",
      handle = "@cheapandbest_mysuru",
      url = "https://www.instagram.com/cheapandbest_mysuru",
      iconType = "instagram"
    ),
    SocialLink(
      platform = "Facebook",
      handle = "Cheap & Best Salon Mysuru",
      url = "https://www.facebook.com/cheapandbestsalonmysuru",
      iconType = "facebook"
    ),
    SocialLink(
      platform = "Google Maps",
      handle = "65 Five-Star Reviews",
      url = MAPS_QUERY_URL,
      iconType = "maps"
    ),
    SocialLink(
      platform = "YouTube",
      handle = "Grooming & Styles",
      url = "https://www.youtube.com/@cheapandbestmysuru",
      iconType = "youtube"
    )
  )
}
