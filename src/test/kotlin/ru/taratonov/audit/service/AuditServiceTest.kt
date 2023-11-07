package ru.taratonov.audit.service

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.junit.jupiter.MockitoExtension
import ru.taratonov.audit.enums.ServiceType
import ru.taratonov.audit.enums.Type
import ru.taratonov.audit.exception.NotFoundException
import ru.taratonov.audit.model.AuditAction
import ru.taratonov.audit.repository.AuditRepository
import java.util.*

@ExtendWith(value = [MockitoExtension::class])
class AuditServiceTest {

    private val auditAction = AuditAction(
        uuid = UUID.fromString("f7d02206-6b87-4dc0-82d6-aab815c9101e"),
        type = Type.START,
        serviceType = ServiceType.DEAL,
        message = "message"
    )

    private val auditRepository: AuditRepository = mock()
    private val auditService = AuditServiceImpl(auditRepository)

    @Test
    fun getByUuid() {
        Mockito.`when`(auditRepository.findById(auditAction.uuid)).thenReturn(Optional.of(auditAction))

        val result = auditService.getByUuid(auditAction.uuid)

        assertEquals(auditAction, result)
    }

    @Test
    fun getByUuidThrowException() {
        Mockito.`when`(auditRepository.findById(auditAction.uuid)).thenReturn(Optional.empty())

        assertThrows(NotFoundException::class.java) {
            auditService.getByUuid(auditAction.uuid)
        }
    }

    @Test
    fun getAllByType() {
        val actual = mutableListOf(auditAction)
        Mockito.`when`(auditRepository.findAll()).thenReturn(actual)

        val result = auditService.getAllByType(auditAction.type)

        assertEquals(actual.size, result.size)
    }

    @Test
    fun getAllByTypeThrowException() {
        val actual = mutableListOf(auditAction)
        Mockito.`when`(auditRepository.findAll()).thenReturn(actual)

        assertThrows(NotFoundException::class.java) {
            auditService.getAllByType(Type.FAILURE)
        }
    }

    @Test
    fun getAllByService() {
        val actual = mutableListOf(auditAction)
        Mockito.`when`(auditRepository.findAll()).thenReturn(actual)

        val result = auditService.getAllByService(auditAction.serviceType)

        assertEquals(actual.size, result.size)
    }

    @Test
    fun getAllByServiceThrowException() {
        val actual = mutableListOf(auditAction)
        Mockito.`when`(auditRepository.findAll()).thenReturn(actual)

        assertThrows(NotFoundException::class.java) {
            auditService.getAllByService(ServiceType.DOSSIER)
        }
    }

    @Test
    fun getAll() {
        val actual = mutableListOf(auditAction)
        Mockito.`when`(auditRepository.findAll()).thenReturn(actual)

        val result = auditService.getAll()

        assertEquals(actual.size, result.size)
    }

    @Test
    fun getAllThrowException() {
        Mockito.`when`(auditRepository.findAll()).thenReturn(mutableListOf())

        assertThrows(NotFoundException::class.java) {
            auditService.getAll()
        }
    }
}