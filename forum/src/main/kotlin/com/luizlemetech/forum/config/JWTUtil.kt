package com.luizlemetech.forum.config

import com.luizlemetech.forum.service.UsuarioService
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.stereotype.Component
import java.util.Date
import javax.crypto.SecretKey

@Component
class JWTUtil(
    @Value("\${jwt.secret}")
    private val secret: String,
    @Value("\${jwt.expiration}")
    private val expirationMillis: Long
) {

    private val key: SecretKey = Keys.hmacShaKeyFor(secret.toByteArray())

    fun generateToken(username: String, authorities: Collection<GrantedAuthority>): String {
        val now = Date()
        val exp = Date(now.time + expirationMillis)
        var roles = authorities.map { it.authority }

        return Jwts.builder()
            .subject(username)
            .claim("roles", roles)
            .issuedAt(now)
            .expiration(exp)
            .signWith(key)
            .compact()
    }

    fun isValid(token: String?): Boolean {
        if (token.isNullOrBlank()) return false
        return try {
            Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
            true
        } catch (_: Exception) {
            false
        }
    }

    fun getUsername(token: String?): String? {
        if (token.isNullOrBlank()) return null
        return try {
            Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .payload
                .subject
        } catch (_: Exception) {
            null
        }
    }

    fun getAuthorities(token: String?): List<GrantedAuthority> {
        if (token.isNullOrBlank()) return emptyList()
        return try {
            val claims = Jwts.parser().verifyWith(key).build().parseSignedClaims(token).payload
            val raw = claims["roles"]
            val names: List<String> = when (raw) {
                is List<*> -> raw.flatMap { item ->
                    when (item) {
                        is String -> listOf(item)
                        is Map<*, *> -> listOfNotNull(item["authority"] as? String)
                        else -> emptyList()
                    }
                }
                is String -> listOf(raw)
                else -> emptyList()
            }
            names.map { SimpleGrantedAuthority(it) }
        } catch (_: Exception) {
            emptyList()
        }
    }

}