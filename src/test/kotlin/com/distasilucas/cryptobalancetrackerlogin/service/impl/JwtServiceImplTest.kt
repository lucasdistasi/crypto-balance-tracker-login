package com.distasilucas.cryptobalancetrackerlogin.service.impl

import com.distasilucas.cryptobalancetrackerlogin.entity.UserEntity
import com.distasilucas.cryptobalancetrackerlogin.model.Role
import com.distasilucas.cryptobalancetrackerlogin.service.JwtService
import io.jsonwebtoken.ExpiredJwtException
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class JwtServiceImplTest {

    private val jwtService: JwtService = JwtServiceImpl(
        KEY = "2A462D4A404F635266556A126E3272357538782F413F4428472B4B6250645312"
    )

    @Test
    fun `should extract username`() {
        val username = jwtService.extractUsername("eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJsdWNhcyJ9.66r5eKWjO6ETYAJKj64X1Cyb9m5bEx8id2Dwzb_c_Bc")

        assertEquals("lucas", username)
    }

    @Test
    fun `should validate token`() {
        val isTokenValid = jwtService.isTokenValid(
            token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJsdWNhcyIsImV4cCI6Ijg1NTAyNDEyMDAifQ.lh3rgnOBlR2CyVXf9nG7rYWZ6UNTKxMgj8pQeJpS-vc",
            userDetails = UserEntity(
                username = "lucas",
                password = "password",
                role = Role.ROLE_ADMIN
            )
        )

        assertTrue(isTokenValid)
    }

    @Test
    fun `should return false when token has invalid username`() {
        val isTokenValid = jwtService.isTokenValid(
            token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJsdWNhcyIsImV4cCI6Ijg1NTAyNDEyMDAifQ.lh3rgnOBlR2CyVXf9nG7rYWZ6UNTKxMgj8pQeJpS-vc",
            userDetails = UserEntity(
                username = "username",
                password = "password",
                role = Role.ROLE_ADMIN
            )
        )

        assertFalse(isTokenValid)
    }

    @Test
    fun `should throw ExpiredJwtException when token is expired`() {
        val exception = assertThrows<ExpiredJwtException> { jwtService.isTokenValid(
            token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJsdWNhcyIsImV4cCI6IjE2OTEwNjg4MjIifQ.exmvA4rFtAaJSU-UryLXXCHQeT-RgsyLPEej_bXwRpk",
            userDetails = UserEntity(
                username = "lucas",
                password = "password",
                role = Role.ROLE_ADMIN
            )
        ) }

        exception.message?.let { assertTrue(it.contains("JWT expired")) }
    }

    @Test
    fun `should generate token`() {
        val userDetails = UserEntity(
            username = "lucas",
            password = "password",
            role = Role.ROLE_ADMIN
        )

        val token = jwtService.generateToken(userDetails)
        val user = token.split(".")[0]

        assertTrue(token.isNotBlank())
        assertEquals("eyJhbGciOiJIUzI1NiJ9", user)
    }
}