package ru.taratonov.audit.api

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import ru.taratonov.audit.exception.NotFoundException
import ru.taratonov.audit.logger
import ru.taratonov.audit.model.ErrorDto
import java.time.LocalDateTime

@RestControllerAdvice
class ControllerAdvice {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = [NotFoundException::class])
    fun catchException(ex: NotFoundException): ErrorDto {
        logger.info("catch NotFoundException")
        return ErrorDto(
            ex.message,
            LocalDateTime.now(),
            HttpStatus.NOT_FOUND
        )
    }
}