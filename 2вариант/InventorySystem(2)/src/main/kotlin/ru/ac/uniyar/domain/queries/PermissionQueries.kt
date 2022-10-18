package ru.ac.uniyar.domain.queries

import ru.ac.uniyar.domain.storage.RolePermissions
import ru.ac.uniyar.domain.storage.RolePermissionsRepository
import java.util.UUID

class FetchPermissionsViaIdQuery(
    private val permissionsRepository: RolePermissionsRepository
) {
    operator fun invoke(roleId: UUID) = permissionsRepository.fetch(roleId)
}

class ListOfPermissionsQuery(
    private val rolePermissionsRepository: RolePermissionsRepository
) {
    operator fun invoke(): List<RolePermissions> {
        return rolePermissionsRepository.list()
    }
}
