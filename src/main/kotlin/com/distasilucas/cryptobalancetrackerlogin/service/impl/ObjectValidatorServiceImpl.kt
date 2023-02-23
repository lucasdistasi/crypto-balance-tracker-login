package com.distasilucas.cryptobalancetrackerlogin.service.impl

import com.distasilucas.cryptobalancetrackerlogin.exception.ValidationException
import com.distasilucas.cryptobalancetrackerlogin.service.ObjectValidatorService
import jakarta.validation.ConstraintViolation
import jakarta.validation.Validation
import org.springframework.stereotype.Service

@Service
class ObjectValidatorServiceImpl<T> : ObjectValidatorService<T> {

    private final val validator = Validation.buildDefaultValidatorFactory().validator

    override fun validate(t: T) {
        val constraintViolations: Set<ConstraintViolation<T>>  = validator.validate(t)

        if (constraintViolations.isNotEmpty()) {
            val errors = constraintViolations
                .map { it.message }
                .toSet()

            throw ValidationException(errors, "Invalid data")
        }
    }
}