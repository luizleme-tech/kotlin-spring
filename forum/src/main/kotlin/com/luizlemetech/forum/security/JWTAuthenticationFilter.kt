package com.luizlemetech.forum.security

import com.luizlemetech.forum.config.JWTUtil
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.web.filter.OncePerRequestFilter

class JWTAuthenticationFilter(
    private val jwtUtil: JWTUtil
) : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val header = request.getHeader("Authorization")
        val token = extractBearerToken(header)

        if (token != null && jwtUtil.isValid(token)) {
            val username = jwtUtil.getUsername(token)
            if (!username.isNullOrBlank() && SecurityContextHolder.getContext().authentication == null) {
                val auth = UsernamePasswordAuthenticationToken(username, null, emptyList())
                auth.details = WebAuthenticationDetailsSource().buildDetails(request)
                SecurityContextHolder.getContext().authentication = auth
            }
        }

        filterChain.doFilter(request, response)
    }

    private fun extractBearerToken(header: String?): String? {
        if (header.isNullOrBlank()) return null
        if (!header.startsWith("Bearer ")) return null
        val token = header.substring("Bearer ".length).trim()
        return if (token.count { it == '.' } == 2) token else token
    }
}