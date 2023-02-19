package com.distasilucas.cryptobalancetrackerlogin.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class LoginController {

    @GetMapping("/ping")
    fun login() : String {
        return "pong"
    }

}