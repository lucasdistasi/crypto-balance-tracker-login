package com.distasilucas.cryptobalancetrackerlogin.model

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

class UserDTO(

    @field:NotNull(message = "Username cannot be null")
    @field:NotBlank(message = "Username cannot be blank")
    val username: String?,

    @field:NotNull(message = "Password cannot be null")
    @field:NotBlank(message = "Password cannot be blank")
    val password: String?
)