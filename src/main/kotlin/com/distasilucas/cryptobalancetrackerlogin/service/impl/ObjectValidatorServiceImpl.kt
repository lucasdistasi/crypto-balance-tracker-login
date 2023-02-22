package com.distasilucas.cryptobalancetrackerlogin.service.impl

import com.distasilucas.cryptobalancetrackerlogin.exception.ValidationException
import com.distasilucas.cryptobalancetrackerlogin.model.UserDTO
import com.distasilucas.cryptobalancetrackerlogin.service.ObjectValidatorService
import jakarta.validation.ConstraintViolation
import jakarta.validation.Validation
import org.springframework.stereotype.Service

@Service
class ObjectValidatorServiceImpl : ObjectValidatorService<UserDTO> {

    private final val validator = Validation.buildDefaultValidatorFactory().validator

    override fun validate(t: UserDTO) {
        val constraintViolations: Set<ConstraintViolation<UserDTO>>  = validator.validate(t)

        if (constraintViolations.isNotEmpty()) {
            val errors = constraintViolations
                .map { it.message }
                .toSet()

            throw ValidationException(errors, "Invalid data")
        }
    }
}