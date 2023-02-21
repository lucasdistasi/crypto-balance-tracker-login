package com.distasilucas.cryptobalancetrackerlogin.controller

import com.distasilucas.cryptobalancetrackerlogin.model.JwtTokenResponse
import com.distasilucas.cryptobalancetrackerlogin.model.UserDTO
import com.distasilucas.cryptobalancetrackerlogin.service.AuthenticationService
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class LoginControllerTest {

    private val authenticationServiceMock = mockk<AuthenticationService>()

    private val loginController = LoginController(authenticationServiceMock)

    @Test
    fun shouldLogin() {
        val userDTO = UserDTO("admin", "admin")
        val token = JwtTokenResponse("token")

        every { authenticationServiceMock.authenticate(userDTO) } returns token

        val responseEntity = loginController.login(userDTO)

        assertEquals(token.token, responseEntity.body?.token)
    }


}