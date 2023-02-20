package com.distasilucas.cryptobalancetrackerlogin.entity

import com.distasilucas.cryptobalancetrackerlogin.model.Role
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.time.LocalDateTime
import java.util.*

@Document("Users")
data class UserEntity(
    @Indexed(unique = true)
    private var username: String,
    private var password: String,
    private var role: Role,
    private var createdAt: LocalDateTime?=LocalDateTime.now(),

    @Id
    private var userId: String?=UUID.randomUUID().toString()
) : UserDetails {
    override fun getAuthorities(): List<SimpleGrantedAuthority> {
        return listOf(SimpleGrantedAuthority(role.name))
    }

    override fun getPassword(): String {
        return password
    }

    override fun getUsername(): String {
        return username
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return true
    }
}