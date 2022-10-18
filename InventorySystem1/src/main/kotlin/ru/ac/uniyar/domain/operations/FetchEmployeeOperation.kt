package ru.ac.uniyar.domain.operations

import ru.ac.uniyar.domain.storage.Employee
import ru.ac.uniyar.domain.storage.EmployeeRepository
import java.util.UUID

fun interface FetchEmployeeOperation {
    fun fetch(id: UUID): Employee?
}

class FetchEmployeeOperationImplementation(
    private val employeeRepository: EmployeeRepository,
) : FetchEmployeeOperation {
    override fun fetch(id: UUID): Employee? = employeeRepository.fetch(id)
}
