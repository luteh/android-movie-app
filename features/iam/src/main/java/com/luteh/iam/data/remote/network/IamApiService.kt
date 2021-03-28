package com.luteh.iam.data.remote.network

import com.luteh.iam.data.remote.response.KeycloackTokenResponse
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface IamApiService {
    @POST("realms/myrealm/protocol/openid-connect/token")
    @FormUrlEncoded
    suspend fun obtainKeycloackToken(
        @Field("client_id") clientId: String,
        @Field("client_secret") clientSecret: String,
        @Field("grant_type") grantType: String = "authorization_code",
        @Field("code") code: String,
        @Field("redirect_uri") redirectUri: String
    ): KeycloackTokenResponse?

    @POST("realms/myrealm/protocol/openid-connect/logout")
    @FormUrlEncoded
    suspend fun logOut(
        @Field("client_id") clientId: String,
        @Field("client_secret") clientSecret: String,
        @Field("refresh_token") grantType: String
    ): Response<Any?>
}