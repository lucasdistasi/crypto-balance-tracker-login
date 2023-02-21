package com.distasilucas.cryptobalancetrackerlogin.service

import org.springframework.security.core.userdetails.UserDetails

interface JwtService {

    fun extractUsername(token: String): String
    fun isTokenValid(token: String, userDetails: UserDetails): Boolean
    fun generateToken(userDetails: UserDetails): String
    fun generateToken(extraClaims: Map<String, Any>, userDetails: UserDetails): String
}