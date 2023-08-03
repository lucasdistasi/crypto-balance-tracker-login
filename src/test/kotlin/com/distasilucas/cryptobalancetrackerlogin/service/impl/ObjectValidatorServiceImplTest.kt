package com.distasilucas.cryptobalancetrackerlogin.service.impl

import com.distasilucas.cryptobalancetrackerlogin.exception.ValidationException
import com.distasilucas.cryptobalancetrackerlogin.model.UserDTO
import com.distasilucas.cryptobalancetrackerlogin.service.ObjectValidatorService
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class ObjectValidatorServiceImplTest {

    private val userValidator: ObjectValidatorService<UserDTO> = ObjectValidatorServiceImpl()

    @Test
    fun `should validate UserDTO`() {
        userValidator.validate(
            UserDTO(
                username = "lucas",
                password = "password"
            )
        )
    }

    @Test
    fun `should throw exception if username is null`() {
        val exception = assertThrows<ValidationException> {
            userValidator.validate(
                UserDTO(
                    username = null,
                    password = "password"
                )
            )
        }

        assertEquals("Invalid data", exception.message)
        assertThat(exception.errors).containsExactlyInAnyOrder(
            "Username cannot be null",
            "Username cannot be blank"
        )
    }

    @Test
    fun `should throw exception if password is null`() {
        val exception = assertThrows<ValidationException> {
            userValidator.validate(
                UserDTO(
                    username = "lucas",
                    password = null
                )
            )
        }

        assertEquals("Invalid data", exception.message)
        assertThat(exception.errors).containsExactlyInAnyOrder(
            "Password cannot be null",
            "Password cannot be blank"
        )
    }
}