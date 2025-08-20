package com.luizlemetech.forum.security

import com.fasterxml.jackson.databind.ObjectMapper
import com.luizlemetech.forum.config.JWTUtil
import com.luizlemetech.forum.model.Credentials
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

class JWTLoginFilter(
    private val authManager: AuthenticationManager,
    private val jwtUtil: JWTUtil,
    private val objectMapper: ObjectMapper = ObjectMapper()
) : UsernamePasswordAuthenticationFilter() {

    override fun attemptAuthentication(request: HttpServletRequest, response: HttpServletResponse): Authentication {
        val creds = objectMapper.readValue(request.inputStream, Credentials::class.java)
        val token = UsernamePasswordAuthenticationToken(creds.username, creds.password)
        return authManager.authenticate(token)
    }

    override fun successfulAuthentication(
        request: HttpServletRequest,
        response: HttpServletResponse,
        chain: FilterChain,
        authResult: Authentication
    ) {
        val user = authResult.principal as UserDetails
        val token = jwtUtil.generateToken(user.username, user.authorities)

        response.addHeader("Authorization", "Bearer $token")
        response.contentType = "application/json"
        response.writer.write("""{"token":"$token"}""")
    }
}