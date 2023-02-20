package com.distasilucas.cryptobalancetrackerlogin.controller

import com.distasilucas.cryptobalancetrackerlogin.model.JwtTokenResponse
import com.distasilucas.cryptobalancetrackerlogin.model.UserDTO
import com.distasilucas.cryptobalancetrackerlogin.service.AuthenticationService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/login")
class LoginController(val authenticationService: AuthenticationService) {

    @PostMapping
    fun login(@RequestBody userDTO: UserDTO) : ResponseEntity<JwtTokenResponse> {
        val jwtTokenResponse = authenticationService.authenticate(userDTO)

        return ResponseEntity.ok(jwtTokenResponse)
    }

}