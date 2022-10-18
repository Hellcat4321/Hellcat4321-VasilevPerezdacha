package ru.ac.uniyar.domain.operations

import ru.ac.uniyar.domain.storage.Equipment
import ru.ac.uniyar.domain.storage.EquipmentRepository
import java.util.UUID

fun interface FetchEquipmentOperation {
    fun fetch(id: UUID): Equipment?
}

class FetchEquipmentOperationImplementation(
    private val equipmentRepository: EquipmentRepository,
) : FetchEquipmentOperation {
    override fun fetch(id: UUID): Equipment? {
        return equipmentRepository.fetch(id)
    }
}
