package com.distasilucas.cryptobalancetrackerlogin.config

import com.distasilucas.cryptobalancetrackerlogin.service.JwtService
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.util.StringUtils
import org.springframework.web.filter.OncePerRequestFilter

@Component
class JwtAuthFilter(val jwtService: JwtService, val userDetailsService: UserDetailsService) : OncePerRequestFilter() {

    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, filterChain: FilterChain) {
        val authHeader: String = request.getHeader("Authorization")

        if (StringUtils.hasText(authHeader) || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response)
            return
        }

        val jwtToken = authHeader.substring(7)
        val userName = jwtService.extractJwtToken(jwtToken)

        if (StringUtils.hasText(userName) && isNotAlreadyAuthenticated()) {
            val userDetails: UserDetails = this.userDetailsService.loadUserByUsername(userName)

            if (jwtService.isTokenValid(jwtToken, userDetails)) {
                val authToken = UsernamePasswordAuthenticationToken(userDetails, null, userDetails.authorities)
                val authDetails = WebAuthenticationDetailsSource().buildDetails(request)
                authToken.details = authDetails
                SecurityContextHolder.getContext().authentication = authToken
            }
        }

        filterChain.doFilter(request, response)
    }

    private fun isNotAlreadyAuthenticated(): Boolean {
        return null == SecurityContextHolder.getContext().authentication
    }
}