package com.example.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val SalonColorScheme = darkColorScheme(
  primary = SalonBrass,
  onPrimary = SalonInk,
  primaryContainer = SalonBrassDark,
  onPrimaryContainer = SalonPaper,
  secondary = SalonBrassBright,
  onSecondary = SalonInk,
  tertiary = SalonSteel,
  onTertiary = SalonPaper,
  background = SalonInk,
  onBackground = SalonPaper,
  surface = SalonInkSoft,
  onSurface = SalonPaper,
  surfaceVariant = SalonInkCard,
  onSurfaceVariant = SalonPaperDim,
  outline = SalonInkBorder,
  outlineVariant = SalonSteel,
  error = SalonRed,
  onError = Color.White
)

@Composable
fun CheapAndBestTheme(
  content: @Composable () -> Unit
) {
  MaterialTheme(
    colorScheme = SalonColorScheme,
    typography = Typography,
    content = content
  )
}

