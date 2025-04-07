package com.luizlemetech.forum.service

import com.luizlemetech.forum.model.Curso
import org.springframework.stereotype.Service

@Service
class CursoService(var cursos: List<Curso>) {
    init{
        val curso = Curso(
            id =1,
            nome = "Kotlin",
            categoria = "Programacao",
        )
        cursos = listOf(curso)
    }

    fun buscarPorId(id: Long): Curso {
        return cursos.stream().filter { c -> c.id == id }.findFirst().get()
    }
}
