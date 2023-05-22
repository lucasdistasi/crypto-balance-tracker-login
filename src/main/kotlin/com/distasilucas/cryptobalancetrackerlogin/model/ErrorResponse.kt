package com.distasilucas.cryptobalancetrackerlogin.model

import java.time.LocalDateTime

class ErrorResponse(
    val statusCode: Int,
    val errors: List<ErrorMessage>,
    val timeStamp: LocalDateTime? = LocalDateTime.now()
)