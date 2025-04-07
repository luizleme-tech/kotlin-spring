package com.luizlemetech.forum.utils

import com.luizlemetech.forum.dto.TopicoView
import com.luizlemetech.forum.model.Topico

fun Topico.toView(): TopicoView {
    return TopicoView(
        this.id!!,
        this.titulo,
        this.mensagem,
        this.status,
        this.dataCriacao,
    )
}