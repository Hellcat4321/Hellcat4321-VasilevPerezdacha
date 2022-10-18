package ru.ac.uniyar.domain.storage

import java.util.UUID

data class RolePermissions(
    val id: UUID,
    val name: String,
    val watchEquipment: Boolean,
    val watchEmployee: Boolean,
) {
    companion object{
        val GUEST_ROLE = RolePermissions(
            id = UUID.fromString("f27c7df1-df1f-4878-8f1d-0670f8e9ddaa"),
            name = "Гость",
            watchEmployee = false,
            watchEquipment = false
        )
    }
}
