package com.luizlemetech.forum.controller

import com.luizlemetech.forum.dto.AtualizacaoTopicoForm
import com.luizlemetech.forum.dto.NovoTopicoForm
import com.luizlemetech.forum.dto.TopicoView
import com.luizlemetech.forum.service.TopicoService
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/topicos")
class TopicoController(private val service: TopicoService) {

    @GetMapping
    fun listar(): List<TopicoView>{
        return service.listar()
    }

    @GetMapping("/{id}")
    fun buscarPorId(@PathVariable id: Long): TopicoView {
        return service.buscarPorId(id)
    }

    @PostMapping
    fun cadastrar(@RequestBody @Valid dto: NovoTopicoForm) {
        service.cadastrar(dto)
    }

    @PutMapping
    fun atualizar(@RequestBody @Valid from: AtualizacaoTopicoForm) {
        service.atualizar(from)
    }
}