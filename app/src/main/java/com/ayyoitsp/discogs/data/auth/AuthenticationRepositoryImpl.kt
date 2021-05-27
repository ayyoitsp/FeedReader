///*
// * Copyright © 2021 Peter Hsu. All rights reserved.
// */
//package com.ayyoitsp.techtest.data.auth
//
//import android.util.Log
//import com.ayyoitsp.techtest.AppConfig
//import com.google.api.client.auth.oauth.*
//import com.google.api.client.http.GenericUrl
//import com.google.api.client.http.HttpRequestFactory
//import com.google.api.client.http.HttpResponse
//import com.google.api.client.http.javanet.NetHttpTransport
//
//
//class AuthenticationRepositoryImpl(val appConfig: AppConfig) {
//
//    fun getToken() {
//        val hmacSigner = OAuthHmacSigner().apply {
//            clientSharedSecret = appConfig.discogsConsumerSecret
//        }
//        // Get Temporary Token
//        val getTemporaryToken = OAuthGetTemporaryToken(appConfig.requestTokenUrl).apply {
//            signer = hmacSigner
//            consumerKey = appConfig.discogsConsumerKey
//            transport = NetHttpTransport()
//        }
//
//        val temporaryTokenResponse = getTemporaryToken.execute()
//        // Build Authenticate URL
//        val accessTempToken = OAuthAuthorizeTemporaryTokenUrl(appConfig.authorizeUrl).apply {
//            temporaryToken = temporaryTokenResponse.token
//        }
//
//        val authUrl = accessTempToken.build()
//
//        // Redirect to Authenticate URL in order to get Verifier Code
//        Log.e("****", "auth url $authUrl")
//
//        // Get Access Token using Temporary token and Verifier Code
//        val getAccessToken = OAuthGetAccessToken(appConfig.accessTokenUrl).apply {
//            signer = hmacSigner
//            temporaryToken = temporaryTokenResponse.token
//            transport = NetHttpTransport()
//            verifier = "VERIFIER_CODE"
//            consumerKey = appConfig.discogsConsumerKey
//        }
//
//        val accessTokenResponse = getAccessToken.execute()
//
//        hmacSigner.tokenSharedSecret = accessTokenResponse.tokenSecret
//
//        // Build OAuthParameters in order to use them while accessing the resource
//        val oauthParameters = OAuthParameters().apply {
//
//        }
//        oauthParameters.signer = signer
//        oauthParameters.consumerKey = OAuth2ClientCredentials.CONSUMER_KEY
//        oauthParameters.token = accessTokenResponse.token
//        oauthParameters.verifier = "VERIFIER_CODE"
//
//        // Use OAuthParameters to access the desired Resource URL
//
//        // Use OAuthParameters to access the desired Resource URL
//        val requestFactory: HttpRequestFactory =
//            NetHttpTransport().createRequestFactory(oauthParameters)
//        val genericUrl = GenericUrl("RESOURCE_URL")
//        val response: HttpResponse = requestFactory.buildGetRequest(genericUrl).execute()
//        System.out.println(response.parseAsString())
//    }
//}