package com.luteh.iam.data.remote.response

import com.google.gson.annotations.SerializedName
import com.luteh.iam.data.local.entity.KeycloakTokenEntity
import com.luteh.iam.domain.model.KeycloakToken

data class KeycloackTokenResponse(
    @SerializedName("access_token")
    val accessToken: String, // eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJKeXVQSnpXV1hJWm9SZkNGdlNiUDFraEZLczI3aXU1emFBMTVqXzJkOWJNIn0.eyJleHAiOjE2MTY1MDA3ODksImlhdCI6MTYxNjUwMDQ4OSwiYXV0aF90aW1lIjoxNjE2NTAwNDcwLCJqdGkiOiI5NzU5YjdkNi01ZDU4LTRmMDMtOTg0Yi05Mjg2ZTYxYmExYjIiLCJpc3MiOiJodHRwOi8vMTAuMC4yLjI6ODA4MC9hdXRoL3JlYWxtcy9teXJlYWxtIiwic3ViIjoiMDBjYTBmZmQtYTlkZC00OWU4LTkyY2MtMTk1NGM5YjQzOTEwIiwidHlwIjoiQmVhcmVyIiwiYXpwIjoiYWNjb3VudCIsInNlc3Npb25fc3RhdGUiOiI5YmQ4NTg0MC0yY2ExLTQ2NzQtOGEwNi03Y2M3Njg2YTQ3YzQiLCJhY3IiOiIxIiwicmVzb3VyY2VfYWNjZXNzIjp7ImFjY291bnQiOnsicm9sZXMiOlsibWFuYWdlLWFjY291bnQiLCJtYW5hZ2UtYWNjb3VudC1saW5rcyIsInZpZXctcHJvZmlsZSJdfX0sInNjb3BlIjoib3BlbmlkIHByb2ZpbGUgZW1haWwiLCJlbWFpbF92ZXJpZmllZCI6ZmFsc2UsInByZWZlcnJlZF91c2VybmFtZSI6IjA4MTIzNDU2NzgifQ.NPUc6_qMHy9kouM0thBZnGcjYgCx15CphwB2Fxm_QUFIoynFNzp1oY-SbGWz32qnQkZdunG_YmXeVc87hD5AR-5k2KMfMuLBA0hcbJ2px1p3TLvQN26uBB-7bfEUPYbQqKs1bBqaPmwQemu7KqjHuSEB4JiePSdcHBROsJ-_GWGDNl5N2VPW9eTgFaXInHA7PrD2-1ZgHWWp3i6Ot0LrpAWKoZe52Td8SXHMQWyFMe9qJtF9I24oqHvo76ku_AZtyBdEYKdiCBxsIe6xo4q84KQTQDV0J8twVuY7qMag9xG-bHADzeeBHRX0RCC55aFgqGDLJax4hvguUvv7d0WiNw
    @SerializedName("expires_in")
    val expiresIn: Int, // 300
    @SerializedName("refresh_expires_in")
    val refreshExpiresIn: Int, // 1800
    @SerializedName("refresh_token")
    val refreshToken: String, // eyJhbGciOiJIUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICI2OTdhNTg5Ni05YmQ2LTQ5YzAtYmUzNC1hMDMzNzEwMTA0NDkifQ.eyJleHAiOjE2MTY1MDIyODksImlhdCI6MTYxNjUwMDQ4OSwianRpIjoiOGZkMDA3MzktNTIzYS00NGRhLTljNzgtMDdkODM1ZWZkNWE1IiwiaXNzIjoiaHR0cDovLzEwLjAuMi4yOjgwODAvYXV0aC9yZWFsbXMvbXlyZWFsbSIsImF1ZCI6Imh0dHA6Ly8xMC4wLjIuMjo4MDgwL2F1dGgvcmVhbG1zL215cmVhbG0iLCJzdWIiOiIwMGNhMGZmZC1hOWRkLTQ5ZTgtOTJjYy0xOTU0YzliNDM5MTAiLCJ0eXAiOiJSZWZyZXNoIiwiYXpwIjoiYWNjb3VudCIsInNlc3Npb25fc3RhdGUiOiI5YmQ4NTg0MC0yY2ExLTQ2NzQtOGEwNi03Y2M3Njg2YTQ3YzQiLCJzY29wZSI6Im9wZW5pZCBwcm9maWxlIGVtYWlsIn0.vbfnb5JYDsCPmEfoZbgFthBJxR1P5u77Q6XXvVcQhz0
    @SerializedName("token_type")
    val tokenType: String, // Bearer
    @SerializedName("id_token")
    val idToken: String, // eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJKeXVQSnpXV1hJWm9SZkNGdlNiUDFraEZLczI3aXU1emFBMTVqXzJkOWJNIn0.eyJleHAiOjE2MTY1MDA3ODksImlhdCI6MTYxNjUwMDQ4OSwiYXV0aF90aW1lIjoxNjE2NTAwNDcwLCJqdGkiOiJiYjAzNjk1Yi02NDI0LTRmZGItOTMzZi1iZGNlYWU3NzhmZWQiLCJpc3MiOiJodHRwOi8vMTAuMC4yLjI6ODA4MC9hdXRoL3JlYWxtcy9teXJlYWxtIiwiYXVkIjoiYWNjb3VudCIsInN1YiI6IjAwY2EwZmZkLWE5ZGQtNDllOC05MmNjLTE5NTRjOWI0MzkxMCIsInR5cCI6IklEIiwiYXpwIjoiYWNjb3VudCIsInNlc3Npb25fc3RhdGUiOiI5YmQ4NTg0MC0yY2ExLTQ2NzQtOGEwNi03Y2M3Njg2YTQ3YzQiLCJhdF9oYXNoIjoiUDR2VkN2ZnNmNktETHhiWVBtcmhBQSIsImFjciI6IjEiLCJlbWFpbF92ZXJpZmllZCI6ZmFsc2UsInByZWZlcnJlZF91c2VybmFtZSI6IjA4MTIzNDU2NzgifQ.biFNDrfIZiqzYT3hsbM5ks4Yfeex2o58UGX2f2suawGflqlblGzRvMRF9BsHO52361c22b-zzwV8n-btbVk4OFi92olZ87N4KJfxncBVrYRLT6x3OhQyoIeFBva4n0lQPo0rvkM7dQfm3_NiyeGfR-SzzoSvekxme0TYKf4BSk9lTnshFrFSq87cd6DgxI63KoQ5Ek0E4iQik-HT-eoF0vQLv_JiccpxS7Lx-Bbjl3rZfiKOBR7eJFS31IKnW3KGArM5rHFOhdxph9L-P3bFysYgiYt_3dEsIPNJ6fVB7XT2gQ_tp-2D3vhXdBlRjhU_AICqFoaYnht3_HtcTHZnbA
    @SerializedName("not-before-policy")
    val notBeforePolicy: Int, // 0
    @SerializedName("session_state")
    val sessionState: String, // 9bd85840-2ca1-4674-8a06-7cc7686a47c4
    @SerializedName("scope")
    val scope: String // openid profile email
) {
    fun toDomain() = KeycloakToken(
        accessToken,
        expiresIn,
        refreshExpiresIn,
        refreshToken,
        tokenType,
        idToken,
        notBeforePolicy,
        sessionState,
        scope
    )

    fun toEntity() = KeycloakTokenEntity(
        accessToken,
        expiresIn,
        refreshExpiresIn,
        refreshToken,
        tokenType,
        idToken,
        notBeforePolicy,
        sessionState,
        scope
    )
}