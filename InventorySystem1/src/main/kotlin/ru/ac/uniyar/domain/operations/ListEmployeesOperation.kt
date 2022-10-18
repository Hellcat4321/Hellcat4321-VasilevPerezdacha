package ru.ac.uniyar.domain.operations

import ru.ac.uniyar.domain.storage.Employee
import ru.ac.uniyar.domain.storage.EmployeeRepository

fun interface ListEmployeesOperation {
    fun list(): List<Employee>
}

class ListEmployeesOperationImplementation(
    private val employeeRepository: EmployeeRepository,
) : ListEmployeesOperation {
    override fun list() = employeeRepository.list()
}
