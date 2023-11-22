package ru.taratonov.audit.model

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer
import io.swagger.v3.oas.annotations.media.Schema
import org.springframework.http.HttpStatus
import java.time.LocalDateTime

data class ErrorDto(
    @field:Schema(
        description = "message of error",
        name = "message",
        example = "error"
    )
    val msg: String? = null,

    @field:Schema(
        description = "time of error",
        name = "time",
        example = "2023-11-07T12:16:28.801Z"
    )
    @field:JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSSSSS")
    @field:JsonSerialize(using = LocalDateTimeSerializer::class)
    @field:JsonDeserialize(using = LocalDateTimeDeserializer::class)
    val errorTime: LocalDateTime? = null,

    @field:Schema(
        description = "http status of error",
        name = "httpStatus",
        example = "BAD_REQUEST")
    val httpStatus: HttpStatus? = null
)
