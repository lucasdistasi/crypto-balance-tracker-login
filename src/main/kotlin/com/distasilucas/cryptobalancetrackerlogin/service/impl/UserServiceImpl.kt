package com.distasilucas.cryptobalancetrackerlogin.service.impl

import com.distasilucas.cryptobalancetrackerlogin.entity.UserEntity
import com.distasilucas.cryptobalancetrackerlogin.repository.UserRepository
import com.distasilucas.cryptobalancetrackerlogin.service.UserService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(val userRepository: UserRepository) : UserService {
    override fun findByUsername(userName: String): UserEntity {
        return userRepository.findByUsername(userName)
            .orElseThrow { UsernameNotFoundException("Username not found") }
    }
}