package com.distasilucas.cryptobalancetrackerlogin.service.impl

import com.distasilucas.cryptobalancetrackerlogin.entity.UserEntity
import com.distasilucas.cryptobalancetrackerlogin.model.Role
import com.distasilucas.cryptobalancetrackerlogin.repository.UserRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.api.assertThrows
import org.springframework.security.core.userdetails.UsernameNotFoundException
import java.util.*

class UserServiceImplTest {

    private val userRepositoryMock = mockk<UserRepository>()

    private val userService = UserServiceImpl(userRepositoryMock)

    @Test
    fun shouldFindByUsername() {
        val userEntity = UserEntity("username", "password", Role.ROLE_USER)

        every { userService.findByUsername("admin") } returns userEntity

        val user = userService.findByUsername("admin")

        assertAll(
            { assertEquals(userEntity.username, user.username) },
            { assertEquals(userEntity.password, user.password) },
            { assertEquals(userEntity.authorities, user.authorities) },
            { verify { userRepositoryMock.findByUsername("admin") } }
        )
    }

    @Test
    fun `should throw UsernameNotFoundException when username is not found`() {
        every { userRepositoryMock.findByUsername("admin") } returns Optional.empty()

        val exception = assertThrows<UsernameNotFoundException> { userService.findByUsername("admin") }

        assertEquals("Username not found", exception.message)
    }
}