package ru.taratonov.audit.model

import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.RedisHash
import ru.taratonov.audit.enums.ServiceType
import ru.taratonov.audit.enums.Type
import java.util.UUID

@RedisHash("AuditAction")
data class AuditAction(
    @Id
    val uuid: UUID,
    val type: Type,
    val serviceType: ServiceType,
    val message: String
)
