package ru.ac.uniyar.domain.operations

import ru.ac.uniyar.domain.storage.Equipment
import ru.ac.uniyar.domain.storage.EquipmentRepository

fun interface ListEquipmentOperation {
    fun list(): List<Equipment>
}

class ListEquipmentOperationImplementation(
    private val equipmentRepository: EquipmentRepository,
) : ListEquipmentOperation {
    override fun list() = equipmentRepository.list()
}
