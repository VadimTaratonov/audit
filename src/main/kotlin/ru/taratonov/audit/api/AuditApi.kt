package ru.taratonov.audit.api

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import ru.taratonov.audit.enums.ServiceType
import ru.taratonov.audit.enums.Type
import ru.taratonov.audit.model.AuditAction
import ru.taratonov.audit.model.ErrorDto
import java.util.*

@RequestMapping("/audit")
@Tag(name = "Audit Controller", description = "Audit API")
interface AuditApi {

    @PostMapping
    @Operation(summary = "Save new action", description = "Allows to save new action to redis db")
    @ApiResponse(
        responseCode = "200",
        description = "Action saved!",
        content = [Content(mediaType = "application/json", schema = Schema(implementation = AuditAction::class))]
    )
    fun save(
        @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Entity to saving",
            content = [Content(schema = Schema(implementation = AuditAction::class))]
        )
        @RequestBody
        @Valid auditAction: AuditAction
    ): ResponseEntity<Void>

    @GetMapping("/uuid/{uuid}")
    @Operation(summary = "Get action", description = "Allows to get action from redis db")
    @ApiResponse(
        responseCode = "200",
        description = "Action received!",
        content = [Content(mediaType = "application/json", schema = Schema(implementation = AuditAction::class))]
    )
    @ApiResponse(
        responseCode = "404",
        description = "Action not found!",
        content = [Content(mediaType = "application/json", schema = Schema(implementation = ErrorDto::class))]
    )
    fun getByUuid(@PathVariable("uuid") uuid: UUID): AuditAction

    @GetMapping("/type/{type}")
    @Operation(summary = "Get actions by type", description = "Allows to get actions by type from redis db")
    @ApiResponse(
        responseCode = "200",
        description = "Actions received!",
        content = [Content(mediaType = "application/json", schema = Schema(implementation = AuditAction::class))]
    )
    @ApiResponse(
        responseCode = "404",
        description = "Actions not found!",
        content = [Content(mediaType = "application/json", schema = Schema(implementation = ErrorDto::class))]
    )
    fun getAllByType(@PathVariable("type") type: Type): List<AuditAction>

    @GetMapping("/service/{serviceType}")
    @Operation(
        summary = "Get actions by service type",
        description = "Allows to get actions by service type from redis db"
    )
    @ApiResponse(
        responseCode = "200",
        description = "Actions received!",
        content = [Content(mediaType = "application/json", schema = Schema(implementation = AuditAction::class))]
    )
    @ApiResponse(
        responseCode = "404",
        description = "Actions not found!",
        content = [Content(mediaType = "application/json", schema = Schema(implementation = ErrorDto::class))]
    )
    fun getAllByService(@PathVariable("serviceType") serviceType: ServiceType): List<AuditAction>

    @GetMapping("/all")
    @Operation(summary = "Get actions", description = "Allows to get all actions from redis db")
    @ApiResponse(
        responseCode = "200",
        description = "Actions received!",
        content = [Content(mediaType = "application/json", schema = Schema(implementation = AuditAction::class))]
    )
    @ApiResponse(
        responseCode = "404",
        description = "Actions not found!",
        content = [Content(mediaType = "application/json", schema = Schema(implementation = ErrorDto::class))]
    )
    fun getAll(): List<AuditAction>
}