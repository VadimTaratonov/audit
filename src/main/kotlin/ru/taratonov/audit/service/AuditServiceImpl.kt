package ru.taratonov.audit.service

import org.springframework.stereotype.Service
import ru.taratonov.audit.enums.ServiceType
import ru.taratonov.audit.enums.Type
import ru.taratonov.audit.exception.NotFoundException
import ru.taratonov.audit.logger
import ru.taratonov.audit.model.AuditAction
import ru.taratonov.audit.repository.AuditRepository
import java.util.*

@Service
class AuditServiceImpl(
    private val auditRepository: AuditRepository
) : AuditService {
    override fun save(auditAction: AuditAction) {
        logger.debug("save new audit action with uuid {}", auditAction.uuid)
        auditRepository.save(auditAction)
    }

    override fun getByUuid(uuid: UUID): AuditAction {
        logger.debug("get audit action with uuid {}", uuid)
        return auditRepository.findById(uuid)
            .orElseThrow {
                logger.error("audit action with uuid {} not found!", uuid)
                NotFoundException("audit action with uuid $uuid not found!")
            }
    }

    override fun getAllByType(type: Type): List<AuditAction> {
        logger.debug("get audit actions with type {}", type)
        return getAll()
            .filter { it.type == type }
            .ifEmpty {
                logger.error("audit actions with type {} not found!", type)
                throw NotFoundException("audit actions with type $type not found!")
            }

    }

    override fun getAllByService(serviceType: ServiceType): List<AuditAction> {
        logger.debug("get audit actions with serviceType {}", serviceType)
        return getAll()
            .filter { it.serviceType == serviceType }
            .ifEmpty {
                logger.error("audit actions with serviceType {} not found!", serviceType)
                throw NotFoundException("audit actions with serviceType $serviceType not found!")
            }
    }

    override fun getAll(): List<AuditAction> {
        logger.debug("get all audit actions")
        return auditRepository.findAll().toList()
            .ifEmpty {
                logger.error("audit actions not found!")
                throw NotFoundException("audit actions not found!")
            }
    }
}