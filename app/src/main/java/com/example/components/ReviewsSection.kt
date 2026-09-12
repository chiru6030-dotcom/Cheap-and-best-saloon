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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.RateReview
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
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
import com.example.data.ReviewItem
import com.example.ui.theme.SalonBrass
import com.example.ui.theme.SalonBrassBright
import com.example.ui.theme.SalonGold
import com.example.ui.theme.SalonInkBorder
import com.example.ui.theme.SalonInkCard
import com.example.ui.theme.SalonPaper
import com.example.ui.theme.SalonPaperDim
import com.example.ui.theme.SalonRed

@Composable
fun ReviewsSection(
  reviews: List<ReviewItem>,
  onOpenWriteReview: () -> Unit,
  modifier: Modifier = Modifier
) {
  Column(
    modifier = modifier
      .fillMaxWidth()
      .padding(horizontal = 20.dp, vertical = 36.dp)
      .testTag("reviews_section")
  ) {
    Text(
      text = "FROM THE CHAIR NEXT TO YOU",
      color = SalonBrassBright,
      fontSize = 12.sp,
      fontWeight = FontWeight.Bold,
      letterSpacing = 1.2.sp
    )
    Spacer(modifier = Modifier.height(6.dp))
    Text(
      text = "Sixty-five reviews, one rating",
      color = SalonPaper,
      fontSize = 26.sp,
      fontWeight = FontWeight.Bold,
      letterSpacing = 0.5.sp
    )
    Spacer(modifier = Modifier.height(10.dp))

    Row(
      verticalAlignment = Alignment.CenterVertically,
      horizontalArrangement = Arrangement.spacedBy(6.dp)
    ) {
      repeat(5) {
        Icon(
          imageVector = Icons.Default.Star,
          contentDescription = null,
          tint = SalonGold,
          modifier = Modifier.size(18.dp)
        )
      }
      Spacer(modifier = Modifier.width(4.dp))
      Text(
        text = "5.0 on Google Maps",
        color = SalonPaper,
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp
      )
    }

    Spacer(modifier = Modifier.height(8.dp))
    Text(
      text = "Every one of them five stars. Here's a handful, straight from verified customers in Bogadi 2nd Stage.",
      color = SalonPaperDim,
      fontSize = 14.sp,
      lineHeight = 21.sp
    )
    Spacer(modifier = Modifier.height(20.dp))

    // List of reviews
    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
      reviews.forEach { review ->
        ReviewCard(review = review)
      }
    }

    Spacer(modifier = Modifier.height(20.dp))

    // Write a review action
    OutlinedButton(
      onClick = onOpenWriteReview,
      colors = ButtonDefaults.outlinedButtonColors(
        contentColor = SalonPaper,
        containerColor = SalonInkCard
      ),
      border = ButtonDefaults.outlinedButtonBorder.copy(brush = androidx.compose.ui.graphics.SolidColor(SalonBrass)),
      shape = RoundedCornerShape(4.dp),
      modifier = Modifier
        .fillMaxWidth()
        .height(46.dp)
        .testTag("btn_write_review")
    ) {
      Icon(
        imageVector = Icons.Default.RateReview,
        contentDescription = null,
        tint = SalonBrassBright,
        modifier = Modifier.size(18.dp)
      )
      Spacer(modifier = Modifier.width(8.dp))
      Text(
        text = "SHARE YOUR EXPERIENCE / WRITE REVIEW",
        fontSize = 11.sp,
        fontWeight = FontWeight.Bold,
        letterSpacing = 0.6.sp,
        color = SalonPaper
      )
    }
  }
}

@Composable
private fun ReviewCard(review: ReviewItem) {
  Box(
    modifier = Modifier
      .fillMaxWidth()
      .clip(RoundedCornerShape(6.dp))
      .background(SalonInkCard)
      .border(1.dp, SalonInkBorder, RoundedCornerShape(6.dp))
      .testTag("review_item_${review.id}")
  ) {
    Row(modifier = Modifier.fillMaxWidth()) {
      // Red left border line (matching web .t-card)
      Box(
        modifier = Modifier
          .width(4.dp)
          .height(110.dp)
          .background(SalonRed)
      )

      Column(
        modifier = Modifier
          .weight(1f)
          .padding(18.dp)
      ) {
        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.SpaceBetween,
          verticalAlignment = Alignment.CenterVertically
        ) {
          Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(2.dp)
          ) {
            repeat(review.stars) {
              Icon(
                imageVector = Icons.Default.Star,
                contentDescription = null,
                tint = SalonGold,
                modifier = Modifier.size(14.dp)
              )
            }
          }

          if (review.verified) {
            Row(
              verticalAlignment = Alignment.CenterVertically,
              horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
              Icon(
                imageVector = Icons.Default.CheckCircle,
                contentDescription = "Verified Visit",
                tint = SalonBrassBright,
                modifier = Modifier.size(13.dp)
              )
              Text(
                text = "Verified",
                color = SalonBrassBright,
                fontSize = 11.sp,
                fontWeight = FontWeight.Medium
              )
            }
          }
        }

        Spacer(modifier = Modifier.height(10.dp))

        Text(
          text = review.quote,
          color = SalonPaper,
          fontSize = 14.sp,
          lineHeight = 21.sp,
          fontWeight = FontWeight.Normal
        )

        Spacer(modifier = Modifier.height(14.dp))

        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.SpaceBetween,
          verticalAlignment = Alignment.CenterVertically
        ) {
          Text(
            text = review.author,
            color = SalonBrassBright,
            fontWeight = FontWeight.Bold,
            fontSize = 13.sp
          )
          Text(
            text = review.meta,
            color = SalonPaperDim,
            fontSize = 11.sp
          )
        }
      }
    }
  }
}
