package ru.taratonov.audit.service

import ru.taratonov.audit.enums.ServiceType
import ru.taratonov.audit.enums.Type
import ru.taratonov.audit.model.AuditAction
import java.util.UUID

interface AuditService {
    suspend fun save(auditAction: AuditAction)
    suspend fun getByUuid(uuid: UUID): AuditAction
    suspend fun getAllByType(type: Type): List<AuditAction>
    suspend fun getAllByService(serviceType: ServiceType) : List<AuditAction>
    suspend fun getAll(): List<AuditAction>
    suspend fun getAllKeys(): List<String>
}