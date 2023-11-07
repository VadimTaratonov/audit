package ru.taratonov.audit.repository

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import ru.taratonov.audit.model.AuditAction
import java.util.*

@Repository
interface AuditRepository : CrudRepository<AuditAction, UUID> {
}