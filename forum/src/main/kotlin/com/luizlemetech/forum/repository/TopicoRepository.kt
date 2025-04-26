package com.luizlemetech.forum.repository

import com.luizlemetech.forum.model.Topico
import org.springframework.data.jpa.repository.JpaRepository

interface TopicoRepository: JpaRepository<Topico, Long> {

    fun findByCursoNome(nomeCurso: String): List<Topico>
}