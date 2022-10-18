package ru.ac.uniyar.domain.storage

import java.util.UUID

class RolePermissionsRepository(
    rolePermissionsRepository: List<RolePermissions> = emptyList()
)
{
    private val repository = rolePermissionsRepository.associateBy { it.id }.toMutableMap()

    fun fetch(id: UUID): RolePermissions? = repository[id]

    fun add(rolePermissions: RolePermissions) {
        if (repository.containsKey(rolePermissions.id)) return

        repository[rolePermissions.id] = rolePermissions
    }

    fun list() = repository.values.toList()
}