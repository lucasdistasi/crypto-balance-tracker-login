package com.distasilucas.cryptobalancetrackerlogin.service.impl

import com.distasilucas.cryptobalancetrackerlogin.service.JwtService
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import java.util.*
import javax.crypto.SecretKey

@Service
class JwtServiceImpl(
    @Value("\${jwt.signing-key}")
    private val KEY: String
) : JwtService {

    override fun extractUsername(token: String): String = extractClaim(token, Claims::getSubject)

    override fun isTokenValid(token: String, userDetails: UserDetails) =
        extractUsername(token) == userDetails.username

    override fun generateToken(userDetails: UserDetails): String = generateToken(HashMap(), userDetails)

    override fun generateToken(extraClaims: Map<String, Any>, userDetails: UserDetails): String {
        return Jwts.builder()
            .claims(extraClaims)
            .subject(userDetails.username)
            .issuedAt(Date(System.currentTimeMillis()))
            .expiration(Date(System.currentTimeMillis() + 1000 * 60 * 24))
            .signWith(getSigningKey(), Jwts.SIG.HS256)
            .compact()
    }

    private fun <T : Any> extractClaim(token: String, resolver: (Claims) -> T): T {
        val claims = extractClaims(token)

        return resolver.invoke(claims)
    }

    private fun extractClaims(token: String): Claims {
        return Jwts.parser()
            .verifyWith(getSigningKey())
            .build()
            .parseSignedClaims(token)
            .payload
    }

    private fun getSigningKey(): SecretKey {
        val jwtBytes: ByteArray = Decoders.BASE64.decode(KEY)

        return Keys.hmacShaKeyFor(jwtBytes)
    }
}