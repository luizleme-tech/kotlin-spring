package com.luizlemetech.forum.mapper

import com.luizlemetech.forum.dto.TopicoView
import com.luizlemetech.forum.model.Topico
import com.luizlemetech.forum.utils.toView
import org.springframework.stereotype.Component

@Component
class TopicoViewMapper: Mapper<Topico, TopicoView> {
    override fun map(t: Topico): TopicoView {
        return t.toView()
    }
}