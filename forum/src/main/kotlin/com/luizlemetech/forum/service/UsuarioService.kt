package com.luizlemetech.forum.service

import com.luizlemetech.forum.model.Usuario
import com.luizlemetech.forum.repository.UsuarioRepository
import org.springframework.stereotype.Service

@Service
class UsuarioService(private val repository: UsuarioRepository) {


    fun buscarPorId(id: Long): Usuario {
        return repository.getOne(id)
    }
}
