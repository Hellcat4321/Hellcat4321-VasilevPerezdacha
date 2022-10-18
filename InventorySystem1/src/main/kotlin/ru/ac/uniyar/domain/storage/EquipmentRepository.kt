package ru.ac.uniyar.domain.storage

import java.time.LocalDate
import java.util.UUID

class EquipmentRepository(equipmentList: List<Equipment>) {
    private val equipment = equipmentList.associateBy { it.id }.toMutableMap()

    fun list(): List<Equipment> = equipment.values.toList()

    fun fetch(id: UUID) = equipment[id]

    fun add(equipments: Equipment): UUID {
        var newId = equipments.id
        while (equipment.containsKey(newId) || newId == EMPTY_UUID) {
            newId = UUID.randomUUID()
        }
        equipment[newId] = equipments.setUuid(newId)
        return newId
    }

    fun edit(
        id: UUID,
        name: String,
        productId: String,
        description: String,
        submissionDate: LocalDate

    ){
        equipment[id]!!.name = name
        equipment[id]!!.productId = productId
        equipment[id]!!.description = description
        equipment[id]!!.submissionDate = submissionDate
    }
}
