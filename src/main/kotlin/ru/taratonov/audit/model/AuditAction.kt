package ru.taratonov.audit.model

import io.swagger.v3.oas.annotations.media.Schema
import ru.taratonov.audit.enums.ServiceType
import ru.taratonov.audit.enums.Type

data class AuditAction(

    @field:Schema(
        description = "type of audit action",
        name = "type",
        example = "START"
    )
    val type: Type? = null,

    @field:Schema(
        description = "service type of audit action",
        name = "serviceType",
        example = "DEAL"
    )
    val serviceType: ServiceType? = null,

    @field:Schema(
        description = "message of audit action",
        name = "message",
        example = "Method sendDocuments with parameters 3"
    )
    val message: String? = null
)
