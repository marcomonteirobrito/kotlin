package com.marco.ProjetoMarco.service

import com.marco.ProjetoMarco.config.JwtProperties
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.stereotype.Service
import java.util.Date

@Service
class TokenService(
    jwtProperties: JwtProperties
) {
    private val secretKey = Keys.hmacShaKeyFor(
        jwtProperties.key.toByteArray()
    )

    fun generate(
        expirationDate: Date,
        additionalClaims: Map<String, Any> = emptyMap(),
        username: String
    ): String =
        Jwts.builder()
            .claims()
            .subject(username)
            .issuedAt(Date(System.currentTimeMillis()))
            .expiration(expirationDate)
            .add(additionalClaims)
            .and()
            .signWith(secretKey)
            .compact()

    fun extractEmail(token: String): String? =
        getAllClaims(token).subject

    fun isExpired(token: String): Boolean = getAllClaims(token)
        .expiration
        .before(Date(System.currentTimeMillis()))

    fun isValid(token: String, username: String): Boolean {
        val email = extractEmail(token)

        return username == email && !isExpired(token)
    }

    private fun getAllClaims(token: String): Claims {
        val parser = Jwts.parser()
            .verifyWith(secretKey)
            .build()

        return parser
            .parseSignedClaims(token).payload
    }
}