package com.luizlemetech.forum.mapper

import com.luizlemetech.forum.dto.NovoTopicoForm
import com.luizlemetech.forum.model.Topico
import com.luizlemetech.forum.service.CursoService
import com.luizlemetech.forum.service.UsuarioService
import org.springframework.stereotype.Component

@Component
class TopicoFormMapper(
    private val cursoService: CursoService,
    private val usuarioService: UsuarioService
): Mapper<NovoTopicoForm, Topico> {
    override fun map(t: NovoTopicoForm): Topico {
        return Topico(
            titulo = t.titulo,
            mensagem = t.mensagem,
            curso = cursoService.buscarPorId(t.idCurso),
            autor = usuarioService.buscarPorId(t.idAutor)
        )
    }
}