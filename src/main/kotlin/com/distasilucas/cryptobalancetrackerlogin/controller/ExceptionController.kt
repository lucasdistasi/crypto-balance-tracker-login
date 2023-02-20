package com.distasilucas.cryptobalancetrackerlogin.controller

import com.distasilucas.cryptobalancetrackerlogin.exception.InvalidCredentialsException
import com.distasilucas.cryptobalancetrackerlogin.model.ErrorMessage
import com.distasilucas.cryptobalancetrackerlogin.model.ErrorResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ExceptionController {

    @ExceptionHandler
    fun handleInvalidCredentialsException(ex: InvalidCredentialsException) : ResponseEntity<ErrorResponse> {
        val error = ErrorMessage(ex.message)
        val errorResponse = ErrorResponse(HttpStatus.BAD_REQUEST.value(), listOf(error))

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(errorResponse)
    }

    @ExceptionHandler
    fun handleException(ex: Exception) : ResponseEntity<ErrorResponse> {
        val error = ErrorMessage(ex.message)
        val errorResponse = ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), listOf(error))

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(errorResponse)
    }
}