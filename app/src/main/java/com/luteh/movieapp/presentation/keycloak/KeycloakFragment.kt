package com.luteh.movieapp.presentation.keycloak

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.webkit.*
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.luteh.core.common.base.BaseFragment
import com.luteh.core.common.delegates.viewBinding
import com.luteh.core.data.Result
import com.luteh.main.MainActivity
import com.luteh.movieapp.R
import com.luteh.movieapp.databinding.FragmentKeycloakBinding
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import java.util.*

@AndroidEntryPoint
class KeycloakFragment : BaseFragment(R.layout.fragment_keycloak) {

    private val vm by viewModels<KeycloakViewModel>()
    private val binding by viewBinding<FragmentKeycloakBinding>()
    private val args by navArgs<KeycloakFragmentArgs>()

    private val userAgent =
        "Mozilla/5.0 (Linux; Android 4.1.1; Galaxy Nexus Build/JRO03C) AppleWebKit/535.19 (KHTML, like Gecko) Chrome/18.0.1025.166 Mobile Safari/535.19"

    override fun onInit(savedInstanceState: Bundle?) {
        initKeycloakWebView()

        observeLiveData()
    }

    private fun initKeycloakWebView() {
        val queryLoginHint = args.username
        val queryResponseType = "code"
        val queryScope = "openid"
        val queryClientId = if (args.isUsernameExist) "account" else "register-user"
        val queryRedirectUri = "http://10.0.2.2:8080/auth/realms/myrealm/account/login-redirect"
        val queryState = UUID.randomUUID()
        val baseUrl = "http://10.0.2.2:8080/auth/realms/myrealm/protocol/openid-connect/auth?"

        vm.setClientId(args.isUsernameExist)
        vm.setRedirectUri(queryRedirectUri)

        val fullUrl = StringBuilder().apply {
            append(baseUrl)
            append("client_id=$queryClientId")
            append("&redirect_uri=$queryRedirectUri")
            append("&state=$queryState")
            append("&response_type=$queryResponseType")
            append("&scope=$queryScope")
            if (args.isUsernameExist) {
                append("&login_hint=$queryLoginHint")
            }
        }.toString()

        Timber.d("fullUrl-> $fullUrl")

        with(binding) {
            wvKeycloak.apply {
                settings.javaScriptEnabled = true
                settings.userAgentString = userAgent
                webViewClient = CustomWebViewClient()
                webChromeClient = CustomChromeClient()
                loadUrl(fullUrl)
                scrollBarStyle = View.SCROLLBARS_INSIDE_OVERLAY
            }
        }
    }

    private fun observeLiveData() {
        vm.keycloakTokenLiveData.observe(this, {
            Timber.d("keycloakTokenLiveData: $it")
            when (it) {
                is Result.Loading -> {

                }
                is Result.Success -> {
                    Toast.makeText(
                        requireContext(),
                        "Your id token is ${it.data.idToken}",
                        Toast.LENGTH_LONG
                    )
                        .show()

                    navigateToMainScreen()
                }
                is Result.Empty -> {

                }
                is Result.Error -> {

                }
            }
        })
    }

    private fun navigateToMainScreen() {
        startActivity(Intent(requireContext(), MainActivity::class.java))
        requireActivity().finish()
    }

    inner class CustomWebViewClient : WebViewClient() {
        override fun shouldOverrideUrlLoading(
            view: WebView?,
            request: WebResourceRequest?
        ): Boolean {
            vm.interceptUrl(request?.url)
            return super.shouldOverrideUrlLoading(view, request)
        }
    }

    inner class CustomChromeClient : WebChromeClient() {
        override fun onConsoleMessage(consoleMessage: ConsoleMessage?): Boolean {
            Timber.d("Console message ${consoleMessage?.message()}")
            return true;
        }
    }
}