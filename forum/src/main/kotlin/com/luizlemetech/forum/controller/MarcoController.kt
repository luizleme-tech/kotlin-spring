package com.luizlemetech.forum.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/marco")
class MarcoController {

    @GetMapping
    fun sayPolo(): String {
        return "Polo"
    }
}