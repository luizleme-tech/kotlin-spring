package com.luizlemetech.forum.dto

import java.time.LocalDateTime

data class ErrorView(
    val timestampo: LocalDateTime = LocalDateTime.now(),
    val status: Int,
    val error: String,
    val message: String?,
    val path: String
)
