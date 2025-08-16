package com.luizlemetech.forum.model

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.ManyToMany
import org.springframework.security.core.GrantedAuthority

@Entity
data class Role(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private val id: Long,
    private val nome: String
) : GrantedAuthority {
    override fun getAuthority() = nome

    @ManyToMany(mappedBy = "roles")
    private val usuarios: MutableList<Usuario> = mutableListOf()
}
