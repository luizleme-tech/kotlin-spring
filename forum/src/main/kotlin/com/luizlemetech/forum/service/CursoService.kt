package com.luizlemetech.forum.service

import com.luizlemetech.forum.model.Curso
import com.luizlemetech.forum.repository.CursoRepository
import org.springframework.stereotype.Service

@Service
class CursoService(private val repository: CursoRepository) {

     fun buscarPorId(id: Long): Curso {
         return repository.getOne(id)
     }
}
