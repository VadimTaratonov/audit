package ru.taratonov.audit.service

import com.fasterxml.jackson.databind.ObjectMapper
import io.lettuce.core.ExperimentalLettuceCoroutinesApi
import io.lettuce.core.api.coroutines.RedisCoroutinesCommands
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList
import org.springframework.stereotype.Service
import ru.taratonov.audit.enums.ServiceType
import ru.taratonov.audit.enums.Type
import ru.taratonov.audit.exception.NotFoundException
import ru.taratonov.audit.logger
import ru.taratonov.audit.model.AuditAction
import java.util.*

@Service
class AuditServiceImpl @OptIn(ExperimentalLettuceCoroutinesApi::class) constructor(
    private val redis: RedisCoroutinesCommands<String, String>,
    private val mapper: ObjectMapper
) : AuditService {
    @OptIn(ExperimentalLettuceCoroutinesApi::class)
    override suspend fun save(auditAction: AuditAction) {
        logger.info("save new audit action from {}", auditAction.serviceType)
        val uuid = UUID.randomUUID().toString()
        val auditActionJson = mapper.writeValueAsString(auditAction)
        redis.set(uuid, auditActionJson)
    }

    @OptIn(ExperimentalLettuceCoroutinesApi::class)
    override suspend fun getByUuid(uuid: UUID): AuditAction {
        logger.info("get audit action with uuid {}", uuid)
        val auditActionJson = redis.get(uuid.toString())
        if (auditActionJson != null) {
            return mapper.readValue(auditActionJson, AuditAction::class.java)
        } else {
            logger.error("audit action with uuid {} not found!", uuid)
            throw NotFoundException("audit action with uuid $uuid not found!")
        }
    }

    override suspend fun getAllByType(type: Type): List<AuditAction> {
        logger.info("get audit actions with type {}", type)
        return getAll()
            .filter { it.type == type }
            .ifEmpty {
                logger.error("audit actions with type {} not found!", type)
                throw NotFoundException("audit actions with type $type not found!")
            }
    }

    override suspend fun getAllByService(serviceType: ServiceType): List<AuditAction> {
        logger.info("get audit actions with serviceType {}", serviceType)
        return getAll()
            .filter { it.serviceType == serviceType }
            .ifEmpty {
                logger.error("audit actions with serviceType {} not found!", serviceType)
                throw NotFoundException("audit actions with serviceType $serviceType not found!")
            }
    }

    @OptIn(ExperimentalLettuceCoroutinesApi::class)
    override suspend fun getAll(): List<AuditAction> {
        logger.info("get all audit actions")
        return redis.keys("*")
            .map { redis.get(it) }
            .filter { it != null }
            .map { mapper.readValue(it, AuditAction::class.java) }
            .toList()
            .ifEmpty {
                logger.error("audit actions not found!")
                throw NotFoundException("audit actions not found!")
            }
    }

    @OptIn(ExperimentalLettuceCoroutinesApi::class)
    override suspend fun getAllKeys(): List<String> {
        logger.info("get all audit actions")
        return redis.keys("*")
            .toList()
            .ifEmpty {
                logger.error("audit actions keys not found!")
                throw NotFoundException("audit actions keys not found!")
            }
    }
}