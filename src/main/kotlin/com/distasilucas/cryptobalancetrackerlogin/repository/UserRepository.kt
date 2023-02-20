package com.distasilucas.cryptobalancetrackerlogin.repository

import com.distasilucas.cryptobalancetrackerlogin.entity.UserEntity
import org.springframework.data.mongodb.repository.MongoRepository
import java.util.*

interface UserRepository : MongoRepository<UserEntity, String> {

    fun findByUsername(userName: String): Optional<UserEntity>
}