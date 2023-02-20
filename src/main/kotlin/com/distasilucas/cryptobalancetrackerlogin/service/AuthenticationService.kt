package com.distasilucas.cryptobalancetrackerlogin.service

import com.distasilucas.cryptobalancetrackerlogin.model.JwtTokenResponse
import com.distasilucas.cryptobalancetrackerlogin.model.UserDTO

interface AuthenticationService {

    fun authenticate(userDTO: UserDTO): JwtTokenResponse
}