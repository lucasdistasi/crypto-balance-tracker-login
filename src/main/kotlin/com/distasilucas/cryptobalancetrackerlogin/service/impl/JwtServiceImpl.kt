package com.distasilucas.cryptobalancetrackerlogin.service.impl

import com.distasilucas.cryptobalancetrackerlogin.service.JwtService
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import java.security.Key
import java.util.*
import kotlin.collections.HashMap

@Service
class JwtServiceImpl : JwtService {

    @Value("\${jwt.signing-key}")
    private final val KEY: String? = null

    override fun extractUsername(token: String): String = extractClaim(token, Claims::getSubject)

    override fun isTokenValid(token: String, userDetails: UserDetails) =
        extractUsername(token) == userDetails.username &&
                isTokenNonExpired(token)

    override fun generateToken(userDetails: UserDetails): String = generateToken(HashMap(), userDetails)

    override fun generateToken(extraClaims: Map<String, Any>, userDetails: UserDetails): String {
        return Jwts.builder()
            .setClaims(extraClaims)
            .setSubject(userDetails.username)
            .setIssuedAt(Date(System.currentTimeMillis()))
            .setExpiration(Date(System.currentTimeMillis() + 1000 * 60 * 24))
            .signWith(getSigningKey(), SignatureAlgorithm.HS256)
            .compact()
    }

    private fun isTokenNonExpired(token: String) = extractClaim(token, Claims::getExpiration).after(Date())

    private fun <T : Any> extractClaim(token: String, resolver: (Claims) -> T): T {
        val claims = extractClaims(token)

        return resolver.invoke(claims)
    }

    private fun extractClaims(token: String): Claims {
        return Jwts.parserBuilder()
            .setSigningKey(getSigningKey())
            .build()
            .parseClaimsJws(token)
            .body
    }

    private fun getSigningKey(): Key {
        val jwtBytes: ByteArray = Decoders.BASE64.decode(KEY)

        return Keys.hmacShaKeyFor(jwtBytes)
    }
}