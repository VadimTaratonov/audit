package ru.taratonov.audit.model

import java.time.LocalDateTime

data class ErrorDto(
    val message: String? = null,
    val time: LocalDateTime? = null
)
