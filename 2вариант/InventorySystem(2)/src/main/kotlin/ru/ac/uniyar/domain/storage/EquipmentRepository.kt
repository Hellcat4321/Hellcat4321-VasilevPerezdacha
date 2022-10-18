package ru.ac.uniyar.domain.storage

import java.util.UUID

class EquipmentRepository(equipmentList: List<Equipment>) {
    private val equipment = equipmentList.associateBy { it.id }.toMutableMap()

    fun list(): List<Equipment> = equipment.values.toList()

    fun fetch(id: UUID) = equipment[id]
}
