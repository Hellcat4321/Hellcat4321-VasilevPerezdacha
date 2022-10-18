package ru.ac.uniyar.web.filters

import org.http4k.core.Filter
import org.http4k.core.HttpHandler
import org.http4k.core.Request
import org.http4k.core.with
import org.http4k.lens.RequestContextLens
import ru.ac.uniyar.domain.queries.FetchPermissionsViaIdQuery
import ru.ac.uniyar.domain.storage.Employee
import ru.ac.uniyar.domain.storage.RolePermissions

fun authorizationFilter(
    currentEmployeeLens: RequestContextLens<Employee?>,
    rolePermissionLens: RequestContextLens<RolePermissions?>,
    fetchPermissionsViaIdQuery: FetchPermissionsViaIdQuery,
): Filter = Filter { next: HttpHandler ->
    { request: Request ->
        val rolePermissions = currentEmployeeLens(request)?.let {
            fetchPermissionsViaIdQuery(it.roleId)
        } ?: RolePermissions.GUEST_ROLE
        val authorizedRequest = request.with(rolePermissionLens of rolePermissions)
        next(authorizedRequest)
    }
}
