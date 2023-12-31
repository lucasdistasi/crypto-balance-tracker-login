package com.distasilucas.cryptobalancetrackerlogin.service.impl

import com.distasilucas.cryptobalancetrackerlogin.entity.UserEntity
import com.distasilucas.cryptobalancetrackerlogin.exception.InvalidCredentialsException
import com.distasilucas.cryptobalancetrackerlogin.exception.ValidationException
import com.distasilucas.cryptobalancetrackerlogin.model.Role
import com.distasilucas.cryptobalancetrackerlogin.model.UserDTO
import com.distasilucas.cryptobalancetrackerlogin.service.JwtService
import com.distasilucas.cryptobalancetrackerlogin.service.UserService
import io.mockk.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.api.assertThrows
import org.springframework.security.authentication.AccountExpiredException
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken

class AuthenticationServiceImplTest {

    private val userServiceMock = mockk<UserService>()

    private val jwtServiceMock = mockk<JwtService>()

    private val authenticationManagerMock = mockk<AuthenticationManager>()

    private val objectValidatorServiceMock = mockk<ObjectValidatorServiceImpl<UserDTO>>()

    private val authenticationService =
        AuthenticationServiceImpl(jwtServiceMock, authenticationManagerMock, objectValidatorServiceMock)

    @Test
    fun shouldAuthenticate() {
        val userDTO = UserDTO("username", "password")
        val user = UserEntity("username", "password", Role.ROLE_USER)
        val userAuthenticationToken = UsernamePasswordAuthenticationToken(userDTO.username, userDTO.password)
        val authToken = UsernamePasswordAuthenticationToken(user, "credentials")

        every { objectValidatorServiceMock.validate(userDTO) } just runs
        every { authenticationManagerMock.authenticate(userAuthenticationToken) } returns authToken
        every { userServiceMock.findByUsername("username") } returns user
        every { jwtServiceMock.generateToken(user) } returns "token123"

        val authenticate = authenticationService.authenticate(userDTO)

        assertAll(
            { assertEquals("token123", authenticate.token) }
        )
    }

    @Test
    fun `should throw ValidationException when username is null`() {
        val userDTO = UserDTO(null, "password")

        justRun { objectValidatorServiceMock.validate(userDTO) }

        val exception = assertThrows<ValidationException> { authenticationService.authenticate(userDTO) }

        assertEquals("Invalid data", exception.message)
        assertEquals(1, exception.errors.size)
        assertEquals("Username cannot be null", exception.errors.first())
    }

    @Test
    fun shouldThrowInvalidCredentialsExceptionWhenAuthenticate() {
        val userDTO = UserDTO("username", "password")
        val userAuthenticationToken = UsernamePasswordAuthenticationToken(userDTO.username, userDTO.password)

        every { objectValidatorServiceMock.validate(userDTO) } just runs
        every { authenticationManagerMock.authenticate(userAuthenticationToken) }.throws(AccountExpiredException(""))

        val exception = assertThrows<InvalidCredentialsException> {
            authenticationService.authenticate(userDTO)
        }

        assertAll(
            { assertEquals("Invalid credentials", exception.message) },
            { verify(exactly = 0) { userServiceMock.findByUsername(any()) } },
            { verify(exactly = 0) { jwtServiceMock.generateToken(any()) } }
        )
    }
}