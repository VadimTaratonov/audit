package ru.taratonov.audit.api

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController
import ru.taratonov.audit.enums.ServiceType
import ru.taratonov.audit.enums.Type
import ru.taratonov.audit.model.AuditAction
import ru.taratonov.audit.service.AuditService
import java.util.*

@RestController
class AuditController(
    private val auditService: AuditService
) : AuditApi {
    override suspend fun save(auditAction: AuditAction): ResponseEntity<Void> {
        auditService.save(auditAction)
        return ResponseEntity(HttpStatus.OK)
    }

    override suspend fun getByUuid(uuid: UUID): AuditAction {
        return auditService.getByUuid(uuid)
    }

    override suspend fun getAllByType(type: Type): List<AuditAction> {
        return auditService.getAllByType(type)
    }

    override suspend fun getAllByService(serviceType: ServiceType): List<AuditAction> {
        return auditService.getAllByService(serviceType)
    }

    override suspend fun getAll(): List<AuditAction> {
        return auditService.getAll()
    }

    override suspend fun getAllKeys(): List<String> {
        return auditService.getAllKeys()
    }
}