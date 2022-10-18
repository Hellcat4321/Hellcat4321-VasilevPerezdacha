package ru.ac.uniyar.domain.storage

import java.util.UUID

class EmployeeRepository(employees: List<Employee>) {
    private val employees = employees.associateBy { it.id }.toMutableMap()

    fun list() = employees.values.toList()

    fun fetch(id: UUID) = employees[id]
}
