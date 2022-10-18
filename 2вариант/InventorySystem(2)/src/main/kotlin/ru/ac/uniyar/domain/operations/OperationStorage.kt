package ru.ac.uniyar.domain.operations

import ru.ac.uniyar.domain.queries.AuthenticateUserViaLoginQuery
import ru.ac.uniyar.domain.queries.FetchPermissionsViaIdQuery
import ru.ac.uniyar.domain.storage.Settings
import ru.ac.uniyar.domain.storage.Storage


class OperationStorage(
    storage: Storage,
    settings: Settings

) {

    val fetchEmployeeOperation: FetchEmployeeOperation = FetchEmployeeOperationImplementation(
        storage.employeeRepository,
    )

    val fetchEquipmentOperation: FetchEquipmentOperation = FetchEquipmentOperationImplementation(
        storage.equipmentRepository,
    )

    val listEmployeesOperation: ListEmployeesOperation = ListEmployeesOperationImplementation(
        storage.employeeRepository,
    )

    val listEquipmentOperation: ListEquipmentOperation = ListEquipmentOperationImplementation(
        storage.equipmentRepository,
    )

    val authenticateUserViaLoginQuery = AuthenticateUserViaLoginQuery(
        storage,
        settings
    )

    val fetchPermissionsViaIdQuery = FetchPermissionsViaIdQuery(storage.rolePermissionsRepository)
    val fetchEmployeeViaUserId = FetchEmployeeViaUserId(storage.employeeRepository)
}
