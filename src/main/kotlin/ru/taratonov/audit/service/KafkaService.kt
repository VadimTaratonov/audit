package ru.taratonov.audit.service

interface KafkaService {

    fun listenTopic(message: String)
}