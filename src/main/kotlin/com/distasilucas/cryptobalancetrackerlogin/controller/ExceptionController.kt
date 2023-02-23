package com.distasilucas.cryptobalancetrackerlogin.controller

import com.distasilucas.cryptobalancetrackerlogin.exception.InvalidCredentialsException
import com.distasilucas.cryptobalancetrackerlogin.exception.ValidationException
import com.distasilucas.cryptobalancetrackerlogin.model.ErrorMessage
import com.distasilucas.cryptobalancetrackerlogin.model.ErrorResponse
import mu.KotlinLogging
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ExceptionController {

    private val logger = KotlinLogging.logger {}

    @ExceptionHandler
    fun handleInvalidCredentialsException(ex: InvalidCredentialsException): ResponseEntity<ErrorResponse> {
        logger.info(ex) { "An InvalidCredentialsException has occurred: $ex" }

        val error = ErrorMessage(ex.message)
        val errorResponse = ErrorResponse(HttpStatus.BAD_REQUEST.value(), listOf(error))

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(errorResponse)
    }

    @ExceptionHandler
    fun handleValidationException(ex: ValidationException): ResponseEntity<ErrorResponse> {
        logger.info(ex) { "A ValidationException has occurred: $ex" }

        val errors = ex.errors
            .map { ErrorMessage(it) }
            .toList()

        val errorResponse = ErrorResponse(HttpStatus.BAD_REQUEST.value(), errors)

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(errorResponse)
    }

    @ExceptionHandler
    fun handleException(ex: Exception): ResponseEntity<ErrorResponse> {
        logger.info(ex) { "An unhandled Exception has occurred: $ex" }

        val error = ErrorMessage(ex.message)
        val errorResponse = ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), listOf(error))

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(errorResponse)
    }
}