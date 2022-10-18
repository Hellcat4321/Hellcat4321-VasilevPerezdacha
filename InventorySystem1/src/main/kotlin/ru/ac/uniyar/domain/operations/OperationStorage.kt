package ru.ac.uniyar.domain.operations

import ru.ac.uniyar.domain.storage.AddQuery
import ru.ac.uniyar.domain.storage.EditEquipmentQuery
import ru.ac.uniyar.domain.storage.FetchEquipmentQuery
import ru.ac.uniyar.domain.storage.Storage

class OperationStorage(
    storage: Storage,
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

    val addEquipmentQuery = AddQuery(
        storage.equipmentRepository
    )

    val fetchEquipmentQuery = FetchEquipmentQuery(
        storage.equipmentRepository
    )

    val editEquipmentQuery = EditEquipmentQuery(
        storage.equipmentRepository
    )
}
