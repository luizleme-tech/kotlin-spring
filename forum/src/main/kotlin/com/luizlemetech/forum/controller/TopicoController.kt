package com.luizlemetech.forum.controller

import com.luizlemetech.forum.dto.AtualizacaoTopicoForm
import com.luizlemetech.forum.dto.NovoTopicoForm
import com.luizlemetech.forum.dto.TopicoView
import com.luizlemetech.forum.service.TopicoService
import jakarta.validation.Valid
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.util.UriComponentsBuilder

@RestController
@RequestMapping("/topicos")
class TopicoController(private val service: TopicoService) {

    @GetMapping
    @Cacheable("topicos")
    fun listar(
            @RequestParam(required=false) nomeCurso: String?,
            @PageableDefault(size = 5, sort = ["dataCriacao"], direction = Sort.Direction.DESC) paginacao: Pageable
    ): Page<TopicoView>{
        return service.listar(nomeCurso, paginacao)
    }

    @GetMapping("/{id}")
    fun buscarPorId(@PathVariable id: Long): TopicoView {
        return service.buscarPorId(id)
    }

    @PostMapping
    @CacheEvict(value = ["topicos"], allEntries = true)
    fun cadastrar(@RequestBody @Valid dto: NovoTopicoForm,
                  uriBuilder: UriComponentsBuilder): ResponseEntity<TopicoView> {
        val topicoView = service.cadastrar(dto)
        val uri = uriBuilder.path("/topicos/${topicoView.id}").build().toUri()
        return ResponseEntity.created(uri).body(topicoView)
    }

    @PutMapping
    @CacheEvict(value = ["topicos"], allEntries = true)
    fun atualizar(@RequestBody @Valid from: AtualizacaoTopicoForm): ResponseEntity<TopicoView> {
        val topicoView = service.atualizar(from)
        return ResponseEntity.ok(topicoView)
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @CacheEvict(value = ["topicos"], allEntries = true)
    fun deletar(@PathVariable id: Long) {
        service.deletar(id)
    }
}