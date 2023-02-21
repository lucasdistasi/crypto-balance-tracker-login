package com.distasilucas.cryptobalancetrackerlogin.controller

import com.distasilucas.cryptobalancetrackerlogin.exception.InvalidCredentialsException
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.http.HttpStatus

class ExceptionControllerTest {

    private val exceptionController = ExceptionController()

    @Test
    fun shouldHandleInvalidCredentialsException() {
        val invalidCredentialsException = InvalidCredentialsException("Invalid credentials")

        val exception = exceptionController.handleInvalidCredentialsException(invalidCredentialsException)

        assertAll(
            { assertEquals(HttpStatus.BAD_REQUEST.value(), exception.body?.statusCode) },
            { assertEquals(1, exception.body?.errors?.size) },
            { assertEquals(invalidCredentialsException.message, exception.body?.errors?.get(0)?.error) },
            { assertNotNull(exception.body?.timeStamp) }
        )
    }
    @Test
    fun shouldHandleException() {
        val someException = Exception("Some exception")

        val exception = exceptionController.handleException(someException)

        assertAll(
            { assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), exception.body?.statusCode) },
            { assertEquals(1, exception.body?.errors?.size) },
            { assertEquals(someException.message, exception.body?.errors?.get(0)?.error) },
            { assertNotNull(exception.body?.timeStamp) }
        )
    }
}