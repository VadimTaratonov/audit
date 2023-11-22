package ru.taratonov.audit.exception

class NotFoundException(private val s: String) :RuntimeException(s) {
    override val message: String
        get() = s
}