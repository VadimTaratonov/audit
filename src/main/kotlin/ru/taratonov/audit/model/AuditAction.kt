package ru.taratonov.audit.model

import io.swagger.v3.oas.annotations.media.Schema
import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.RedisHash
import ru.taratonov.audit.enums.ServiceType
import ru.taratonov.audit.enums.Type
import java.util.UUID

@RedisHash("AuditAction")
data class AuditAction(
    @Id
    @field:Schema(
        description = "uuid of audit action",
        name = "uuid",
        example = "f7d02206-6b87-4dc0-82d6-aab815c9101e"
    )
    val uuid: UUID,

    @field:Schema(
        description = "type of audit action",
        name = "type",
        example = "START"
    )
    val type: Type,

    @field:Schema(
        description = "service type of audit action",
        name = "serviceType",
        example = "DEAL"
    )
    val serviceType: ServiceType,

    @field:Schema(
        description = "message of audit action",
        name = "message",
        example = "Method sendDocuments with parameters 3"
    )
    val message: String
)
