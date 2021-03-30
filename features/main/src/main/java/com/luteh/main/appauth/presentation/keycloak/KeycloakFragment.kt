package com.luteh.main.appauth.presentation.keycloak

import android.os.Bundle
import android.view.View
import android.webkit.*
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.luteh.core.common.base.BaseFragment
import com.luteh.core.common.delegates.viewBinding
import com.luteh.core.common.extensions.Features
import com.luteh.core.common.extensions.gone
import com.luteh.core.common.extensions.navigateTo
import com.luteh.core.data.Result
import com.luteh.main.R
import com.luteh.main.databinding.FragmentKeycloakBinding
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
//        val queryResponseMode = "fragment"
        val queryResponseType = "code"
        val queryScope = "openid"
//        val queryClientId = if (args.isUsernameExist) IamRemoteDataSource.CLIENT_ID_ACCOUNT_CONSOLE else IamRemoteDataSource.CLIENT_ID_REGISTER_USER
        val queryRedirectUri = "http://10.0.2.2:8080/auth/realms/myrealm/account/login-redirect"
//        val queryRedirectUri = "http%3A%2F%2F10.0.2.2%3A8080%2Fauth%2Frealms%2Fmyrealm%2Faccount%2F%23%2Fpersonal-info"
        val queryState = UUID.randomUUID()
//        val queryNonce = UUID.randomUUID()
//        val queryCodeChallenge = UUID.randomUUID()
//        val queryCodeChallengeMethod = "S256"
        val baseUrl = "http://10.0.2.2:8080/auth/realms/myrealm/protocol/openid-connect/auth?"

        vm.setClientId(args.isUsernameExist)
        vm.setRedirectUri(queryRedirectUri)

        val fullUrl = StringBuilder().apply {
            append(baseUrl)
            append("client_id=${vm.clientId}")
            append("&redirect_uri=$queryRedirectUri")
            append("&state=$queryState")
//            append("&response_mode=$queryResponseMode")
            append("&response_type=$queryResponseType")
            append("&scope=$queryScope")
//            append("&nonce=$queryNonce")
//            append("&code_challenge=$queryCodeChallenge")
//            append("&code_challenge_method=$queryCodeChallengeMethod")
            if (args.isUsernameExist) {
                append("&login_hint=$queryLoginHint")
            }
        }.toString()

//        val fullUrl = "http://10.0.2.2:8080/auth/realms/myrealm/account"

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
                    onLoadingObtainToken()
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
                    Timber.d("keycloakTokenLiveData: Error-> ${it.throwable.message}")
                }
            }
        })
    }

    private fun onLoadingObtainToken() {
        binding.wvKeycloak.gone()
    }

    private fun navigateToMainScreen() {
        navigateTo(Features.MAIN)
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