package com.distasilucas.cryptobalancetrackerlogin.service.impl

import com.distasilucas.cryptobalancetrackerlogin.entity.UserEntity
import com.distasilucas.cryptobalancetrackerlogin.exception.InvalidCredentialsException
import com.distasilucas.cryptobalancetrackerlogin.exception.ValidationException
import com.distasilucas.cryptobalancetrackerlogin.model.JwtTokenResponse
import com.distasilucas.cryptobalancetrackerlogin.model.UserDTO
import com.distasilucas.cryptobalancetrackerlogin.service.AuthenticationService
import com.distasilucas.cryptobalancetrackerlogin.service.JwtService
import com.distasilucas.cryptobalancetrackerlogin.service.ObjectValidatorService
import mu.KotlinLogging
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.AuthenticationException
import org.springframework.stereotype.Service

@Service
class AuthenticationServiceImpl(
    val jwtService: JwtService,
    val authenticationManager: AuthenticationManager,
    val validator: ObjectValidatorService<UserDTO>
) : AuthenticationService {

    private val logger = KotlinLogging.logger {}

    override fun authenticate(userDTO: UserDTO): JwtTokenResponse {
        validator.validate(userDTO)
        logger.info("User ${userDTO.username} is trying to authenticate")

        val userName = userDTO.username ?: throw ValidationException(setOf("Username cannot be null"), "Invalid data")

        try {
            val userAuthenticationToken = UsernamePasswordAuthenticationToken(userName, userDTO.password)
            val authenticate = authenticationManager.authenticate(userAuthenticationToken)
            val token = jwtService.generateToken(authenticate.principal as UserEntity)

            return JwtTokenResponse(token)
        } catch (ex: AuthenticationException) {
            throw InvalidCredentialsException("Invalid credentials")
        }
    }
}