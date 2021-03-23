package com.luteh.movieapp

import android.content.Context
import android.content.DialogInterface
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Message
import android.view.View
import android.view.WindowManager
import android.webkit.*
import android.webkit.WebView.WebViewTransport
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.webkit.WebViewAssetLoader
import com.luteh.core.common.delegates.viewBinding
import com.luteh.movieapp.databinding.ActivityPracticeBinding
import timber.log.Timber
import java.net.URLDecoder


class PracticeActivity : AppCompatActivity() {

    private val binding by viewBinding<ActivityPracticeBinding>()

    private lateinit var webViewPopUp: WebView
    private lateinit var builder: AlertDialog
    private lateinit var yourWebClient: WebViewClient

//    private val userAgent = System.getProperty("http.agent")
    private val userAgent = "Mozilla/5.0 (Linux; Android 4.1.1; Galaxy Nexus Build/JRO03C) AppleWebKit/535.19 (KHTML, like Gecko) Chrome/18.0.1025.166 Mobile Safari/535.19"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        onInit()
    }

    private fun onInit() {
        val webViewAssetLoader = WebViewAssetLoader.Builder()
            .setHttpAllowed(true)
            .setDomain("10.0.2.2:8080")
            .addPathHandler(
                "/auth/resources/0vee3/common/keycloak/web_modules/@fortawesome/fontawesome-free/css/icons/",
                WebViewAssetLoader.AssetsPathHandler(applicationContext)
            )
            .build()

        with(binding) {
            yourWebClient = object : WebViewClient() {
                override fun onReceivedHttpAuthRequest(
                    view: WebView?,
                    handler: HttpAuthHandler?,
                    host: String?,
                    realm: String?
                ) {
                    Timber.d("handle message ${handler?.obtainMessage()} | ${handler?.obtainMessage()?.target}")
                    super.onReceivedHttpAuthRequest(view, handler, host, realm)
                }

                override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
//                    Timber.d("shouldOverrideUrlLoading: url-> $url")
//                    return false
                    return super.shouldOverrideUrlLoading(view, url)
                }

                override fun shouldOverrideUrlLoading(
                    view: WebView?,
                    request: WebResourceRequest?
                ): Boolean {
                    Timber.d("shouldOverrideUrlLoading ${request?.url}")
                    return super.shouldOverrideUrlLoading(view, request)
                }

                override fun onLoadResource(view: WebView?, url: String?) {
                    super.onLoadResource(view, url)
                    Timber.d("onLoadResource $url")
                    val index = url!!.indexOf("FORM?")
                    Timber.d("onLoadResource: index-> $index")
                    if (index != -1) {
                        val d: String = URLDecoder.decode(url.substring(index + 5))
                        Timber.d("onLoadResource: d-> $d")
                    }
                }

                override fun onPageFinished(view: WebView?, url: String?) {
                    Timber.d("onPageFinished: url-> $url")
                    val uri = Uri.parse(url)
//                    Timber.d("onPageFinished: uri-> $uri")
//                    Timber.d("onPageFinished: uri code-> ${uri.getQueryParameter("code")}")
//                    Timber.d("onPageFinished: uri pathSegments-> ${uri.pathSegments}")
//                    Timber.d("onPageFinished: uri queryParameterNames-> ${uri.queryParameterNames}")
//                    Timber.d("onPageFinished: uri encodedQuery-> ${uri.encodedQuery}")
//                    Timber.d("onPageFinished: uri contains-> ${url?.contains("code=")}")
//                    Timber.d("onPageFinished: uri split-> ${url?.split("code=")}")

//                    view?.loadUrl("javascript:console.log(document.body.getElementsByTagName('pre')[0].innerHTML);");

//                    wvGoogleSignin.loadUrl("javascript:HtmlViewer.showHTML" +
//                            "('<html>'+document.getElementsByTagName('html')[0].innerHTML+'</html>');")
                    super.onPageFinished(view, url)
                }

                override fun shouldInterceptRequest(
                    view: WebView?,
                    request: WebResourceRequest?
                ): WebResourceResponse? {
//                    Timber.d("request url: ${request?.url}")
//                    Timber.d("authority: ${request?.url?.authority}")
//                    Timber.d("path: ${request?.url?.path}")
//                    val interceptedWebRequest = webViewAssetLoader.shouldInterceptRequest(request!!.url)
//                    Timber.d("interceptedWebRequest: $interceptedWebRequest")
//                    interceptedWebRequest?.let {
////                        if (request.url.toString().endsWith("js", true)) {
//                            it.mimeType = "text/javascript"
////                        }
//                    }
//                    Timber.d("interceptedWebRequest changed: $interceptedWebRequest")

//                    return interceptedWebRequest
                    return super.shouldInterceptRequest(view, request)
                }
            }



            wvGoogleSignin.apply {

                // Enable Cookies
                CookieManager.getInstance().setAcceptCookie(true);
                if(android.os.Build.VERSION.SDK_INT >= 21)
                    CookieManager.getInstance().setAcceptThirdPartyCookies(this, true);

                settings.javaScriptEnabled = true
                settings.userAgentString = userAgent
                settings.javaScriptCanOpenWindowsAutomatically = true
                settings.setSupportMultipleWindows(true)
                webViewClient = yourWebClient
                webChromeClient = CustomChromeClient()
                addJavascriptInterface(WebAppInterface(), "Android")
                loadUrl("http://10.0.2.2:8080/auth/realms/master/account/")

                // WebView Tweaks
                settings.setRenderPriority(WebSettings.RenderPriority.HIGH);
                settings.setCacheMode(WebSettings.LOAD_DEFAULT);
                settings.setAppCacheEnabled(true);
                settings.setDomStorageEnabled(true);
                settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
                settings.setUseWideViewPort(true);
                settings.setSaveFormData(true);
                settings.setEnableSmoothTransition(true);
                settings.setAllowFileAccessFromFileURLs(true);
                settings.setAllowUniversalAccessFromFileURLs(true);
                setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY)

            }
        }
    }

    inner class CustomChromeClient : WebChromeClient() {


        override fun onCreateWindow(
            view: WebView?,
            isDialog: Boolean,
            isUserGesture: Boolean,
            resultMsg: Message?
        ): Boolean {
            webViewPopUp = WebView(applicationContext)
            webViewPopUp.setVerticalScrollBarEnabled(false)
            webViewPopUp.setHorizontalScrollBarEnabled(false)
            webViewPopUp.setWebChromeClient(CustomChromeClient())
            webViewPopUp.webViewClient = yourWebClient
            webViewPopUp.getSettings().setJavaScriptEnabled(true)
            webViewPopUp.getSettings().setSaveFormData(true)
            webViewPopUp.getSettings().setEnableSmoothTransition(true)
            webViewPopUp.getSettings()
                .setUserAgentString(userAgent)

            // pop the  webview with alert dialog
            builder = AlertDialog.Builder(this@PracticeActivity).create()
            builder.setTitle("")
            builder.setView(webViewPopUp)
            builder.setButton(
                DialogInterface.BUTTON_NEGATIVE,
                "Close",
                DialogInterface.OnClickListener { dialog, id ->
                    webViewPopUp.destroy()
                    dialog.dismiss()
                })
            builder.show()
            builder.getWindow()!!
                .clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE or WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM)
            val cookieManager: CookieManager = CookieManager.getInstance()
            cookieManager.setAcceptCookie(true)
            if (Build.VERSION.SDK_INT >= 21) {
                cookieManager.setAcceptThirdPartyCookies(webViewPopUp, true)
                cookieManager.setAcceptThirdPartyCookies(binding.wvGoogleSignin, true)
            }
            val transport = resultMsg?.obj as WebViewTransport
            transport.webView = webViewPopUp
            resultMsg.sendToTarget()
            return true
        }



        override fun onConsoleMessage(consoleMessage: ConsoleMessage?): Boolean {
            /* process JSON */
            Timber.d("Console message ${consoleMessage?.message()}")
//            consoleMessage?.message()
            return true;
        }

        override fun onCloseWindow(window: WebView) {
            //Toast.makeText(contextPop,"onCloseWindow called",Toast.LENGTH_SHORT).show();
            Timber.d("start close window")
            try {
                webViewPopUp.destroy()
            } catch (e: Exception) {
                Timber.d("Destroyed with Error ${e.stackTrace.toString()}")
            }
            try {
                builder.dismiss()
            } catch (e: Exception) {
                Timber.d("Dismissed with Error ${e.stackTrace.toString()}")
            }
        }
    }

    /** Instantiate the interface and set the context  */
    inner class WebAppInterface() {

        /** Show a toast from the web page  */
        @JavascriptInterface
        fun sendToken(toast: String) {
            Timber.d("javascript interface $toast")
        }
    }

    override fun onBackPressed() {
        if (binding.wvGoogleSignin.canGoBack()) {
            binding.wvGoogleSignin.goBack()
            return
        }
        super.onBackPressed()
    }
}



