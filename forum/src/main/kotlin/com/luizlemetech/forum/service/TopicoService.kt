package com.luizlemetech.forum.service

import com.luizlemetech.forum.dto.AtualizacaoTopicoForm
import com.luizlemetech.forum.dto.NovoTopicoForm
import com.luizlemetech.forum.dto.TopicoView
import com.luizlemetech.forum.exception.NotFoundException
import com.luizlemetech.forum.mapper.TopicoFormMapper
import com.luizlemetech.forum.mapper.TopicoViewMapper
import com.luizlemetech.forum.repository.TopicoRepository
import jakarta.transaction.Transactional
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class TopicoService(
    private val repository: TopicoRepository,
    private val topicoViewMapper: TopicoViewMapper,
    private val topicoFormMapper: TopicoFormMapper) {

    private val notFoundMessage: String = "Topico nao encontrado"

    fun listar(
            nomeCurso: String?,
            paginacao: Pageable
        ): Page<TopicoView> {

        val topicos = if (nomeCurso == null) {
            repository.findAll(paginacao)
        } else {
            repository.findByCursoNome(nomeCurso, paginacao)
        }
        return topicos.map {t -> topicoViewMapper.map(t) }
    }

    fun buscarPorId(id: Long): TopicoView {
        val topico =  repository.findById(id).orElseThrow { NotFoundException(notFoundMessage) }
        return topicoViewMapper.map(topico)
    }

    @Transactional
    fun cadastrar(form: NovoTopicoForm): TopicoView {
        val topico = topicoFormMapper.map(form)
        repository.save(topico)
        return topicoViewMapper.map(topico)
    }

    @Transactional
    fun atualizar(form: AtualizacaoTopicoForm): TopicoView {
        val topico = repository.findById(form.id)
            .orElseThrow { NotFoundException(notFoundMessage) }
        topico.titulo = form.titulo
        topico.mensagem = form.mensagem
        return topicoViewMapper.map(topico)
    }

    @Transactional
    fun deletar(id: Long) {
        repository.deleteById(id)
    }
}