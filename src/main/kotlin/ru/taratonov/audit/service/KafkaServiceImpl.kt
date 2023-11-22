package ru.taratonov.audit.service

import com.fasterxml.jackson.databind.ObjectMapper
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service
import ru.taratonov.audit.logger
import ru.taratonov.audit.model.AuditAction


@Service
class KafkaServiceImpl(
    private val auditService: AuditService,
    private val mapper: ObjectMapper
) : KafkaService {

    @KafkaListener(topics = ["\${audit.topic.name}"], groupId = "\${spring.kafka.consumer.group-id}")
    override fun listenTopic(message: String) {
        GlobalScope.launch {
            val auditAction = mapper.readValue(message, AuditAction::class.java)
            logger.debug("get auditAction from kafka")
            auditService.save(auditAction)
        }
    }
}