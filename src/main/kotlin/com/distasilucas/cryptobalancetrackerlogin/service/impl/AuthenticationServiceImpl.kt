package com.distasilucas.cryptobalancetrackerlogin.service.impl

import com.distasilucas.cryptobalancetrackerlogin.model.JwtTokenResponse
import com.distasilucas.cryptobalancetrackerlogin.model.UserDTO
import com.distasilucas.cryptobalancetrackerlogin.service.AuthenticationService
import com.distasilucas.cryptobalancetrackerlogin.service.JwtService
import com.distasilucas.cryptobalancetrackerlogin.service.UserService
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.stereotype.Service

@Service
class AuthenticationServiceImpl(
    val userService: UserService,
    val jwtService: JwtService,
    val authenticationManager: AuthenticationManager
) : AuthenticationService {

    override fun authenticate(userDTO: UserDTO): JwtTokenResponse {
        val userName = userDTO.username
        val userAuthenticationToken = UsernamePasswordAuthenticationToken(userName, userDTO.password)

        authenticationManager.authenticate(userAuthenticationToken)

        val userEntity = userService.findByUsername(userName)
        val token = jwtService.generateToken(userEntity)

        return JwtTokenResponse(token)
    }
}