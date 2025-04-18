package com.luizlemetech.forum.repository

import com.luizlemetech.forum.model.Curso
import org.springframework.data.jpa.repository.JpaRepository

interface CursoRepository: JpaRepository<Curso, Long> {
}