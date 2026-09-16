package com.example.components

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.view.View
import android.webkit.RenderProcessGoneDetail
import android.webkit.WebChromeClient
import android.webkit.WebResourceRequest
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.viewinterop.AndroidView

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun WebsiteWebView(
  modifier: Modifier = Modifier
) {
  val context = LocalContext.current

  AndroidView(
    factory = { ctx ->
      WebView(ctx).apply {
        // Explicitly set software layer type to avoid Mesa /dev/dri/renderNode error in container environments
        setLayerType(View.LAYER_TYPE_SOFTWARE, null)

        settings.apply {
          javaScriptEnabled = true
          domStorageEnabled = true
          useWideViewPort = true
          loadWithOverviewMode = true
          allowFileAccess = true
          builtInZoomControls = false
          displayZoomControls = false
          cacheMode = WebSettings.LOAD_DEFAULT
        }

        webViewClient = object : WebViewClient() {
          override fun onRenderProcessGone(view: WebView?, detail: RenderProcessGoneDetail?): Boolean {
            return true
          }

          override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
            val url = request?.url?.toString() ?: return false
            if (url.startsWith("tel:") ||
                url.startsWith("https://wa.me") ||
                url.startsWith("https://www.google.com/maps") ||
                url.startsWith("mailto:") ||
                url.startsWith("https://www.instagram.com") ||
                url.startsWith("https://www.facebook.com") ||
                url.startsWith("https://www.youtube.com")
            ) {
              try {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                ctx.startActivity(intent)
              } catch (e: Exception) {
                // Ignore if external handler is absent
              }
              return true
            }
            return false
          }
        }

        webChromeClient = WebChromeClient()
        loadUrl("file:///android_asset/website/index.html")
      }
    },
    modifier = modifier
      .fillMaxSize()
      .testTag("website_webview_preview")
  )
}
