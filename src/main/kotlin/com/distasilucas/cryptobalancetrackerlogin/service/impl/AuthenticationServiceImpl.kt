package com.distasilucas.cryptobalancetrackerlogin.service.impl

import com.distasilucas.cryptobalancetrackerlogin.exception.InvalidCredentialsException
import com.distasilucas.cryptobalancetrackerlogin.exception.ValidationException
import com.distasilucas.cryptobalancetrackerlogin.model.JwtTokenResponse
import com.distasilucas.cryptobalancetrackerlogin.model.UserDTO
import com.distasilucas.cryptobalancetrackerlogin.service.AuthenticationService
import com.distasilucas.cryptobalancetrackerlogin.service.JwtService
import com.distasilucas.cryptobalancetrackerlogin.service.ObjectValidatorService
import com.distasilucas.cryptobalancetrackerlogin.service.UserService
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.AuthenticationException
import org.springframework.stereotype.Service

@Service
class AuthenticationServiceImpl(
    val userService: UserService,
    val jwtService: JwtService,
    val authenticationManager: AuthenticationManager,
    val validator: ObjectValidatorService<UserDTO>
) : AuthenticationService {

    override fun authenticate(userDTO: UserDTO): JwtTokenResponse {
        validator.validate(userDTO)
        val userName = userDTO.username ?: throw ValidationException(setOf("Username cannot be null"), "Invalid data")
        val userAuthenticationToken = UsernamePasswordAuthenticationToken(userName, userDTO.password)

        try {
            authenticationManager.authenticate(userAuthenticationToken)

            val userEntity = userService.findByUsername(userName)
            val token = jwtService.generateToken(userEntity)

            return JwtTokenResponse(token)
        } catch (ex: AuthenticationException) {
            throw InvalidCredentialsException("Invalid credentials")
        }
    }
}