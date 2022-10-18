package ru.ac.uniyar.domain.storage

import java.time.LocalDate
import java.util.UUID

data class Equipment(
    val id: UUID,
    var name: String,
    var productId: String,
    var description: String,
    var submissionDate: LocalDate,
){
    fun setUuid(uuid: UUID): Equipment {
        return this.copy(id = uuid)
    }
}
