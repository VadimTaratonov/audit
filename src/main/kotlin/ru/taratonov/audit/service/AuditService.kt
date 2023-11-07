package ru.taratonov.audit.service

import ru.taratonov.audit.enums.ServiceType
import ru.taratonov.audit.enums.Type
import ru.taratonov.audit.model.AuditAction
import java.util.UUID

interface AuditService {
    fun save(auditAction: AuditAction)
    fun getByUuid(uuid: UUID): AuditAction
    fun getAllByType(type: Type): List<AuditAction>
    fun getAllByService(serviceType: ServiceType) : List<AuditAction>
    fun getAll(): List<AuditAction>
}