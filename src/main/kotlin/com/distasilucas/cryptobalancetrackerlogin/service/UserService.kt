package com.distasilucas.cryptobalancetrackerlogin.service

import com.distasilucas.cryptobalancetrackerlogin.entity.UserEntity

interface UserService {

    fun findByUsername(userName: String): UserEntity
}