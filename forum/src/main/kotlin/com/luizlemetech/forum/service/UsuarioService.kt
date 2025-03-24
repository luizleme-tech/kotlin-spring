package com.luizlemetech.forum.service

import com.luizlemetech.forum.model.Usuario
import org.springframework.stereotype.Service

@Service
class UsuarioService(var usuarios: List<Usuario>) {
    init{
        val usuario = Usuario(
            id =1,
            nome = "Ana Costa",
            email = "ana@email.com",
        )
        usuarios = listOf(usuario)
    }

    fun buscarPorId(id: Long): Usuario {
        return usuarios.stream().filter { c -> c.id == id }.findFirst().get()
    }
}
