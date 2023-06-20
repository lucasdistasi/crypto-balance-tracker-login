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
    private var userId: String = UUID.randomUUID().toString()
) : UserDetails {

    override fun getAuthorities() = listOf(SimpleGrantedAuthority(role.name))

    override fun getPassword() = password

    override fun getUsername() = username

    override fun isAccountNonExpired() = true

    override fun isAccountNonLocked() = true

    override fun isCredentialsNonExpired() = true

    override fun isEnabled() = true
}