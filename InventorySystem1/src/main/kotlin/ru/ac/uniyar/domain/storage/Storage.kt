package ru.ac.uniyar.domain.storage

import java.time.LocalDate
import java.util.UUID

data class Storage(
    val employeeRepository: EmployeeRepository,
    val equipmentRepository: EquipmentRepository,
)

fun initializeRepositoryStorage(): Storage {
    val petr = Employee(
        id = UUID.fromString("8333f4b1-322e-4baa-be42-05079d9fbb95"),
        name = "Пётр Васильевич Григорьев",
        login = "petr",
        phone = "+790000000001",
    )
    val ivan = Employee(
        id = UUID.fromString("c9096a41-b3c5-485a-8756-fd5056a8a944"),
        name = "Иван Сергеевич Ушаков",
        login = "ivan",
        phone = "+790000000002",
    )
    val irina = Employee(
        id = UUID.fromString("8d93f93f-d491-45ed-ac94-66ad04a01e00"),
        name = "Ирина Николаевна Кук",
        login = "irina",
        phone = "+790000000003",
    )
    val employeeRepository = EmployeeRepository(listOf(
        petr,
        ivan,
        irina,
    ))

    val printer = Equipment(
        id = UUID.fromString("8ab75a8b-c39c-4607-b253-5615e6e5e4db"),
        name = "Принтер HP LazerJet 1000",
        productId = "578-575-755-555",
        description = "Старый проверенный принтер",
        submissionDate = LocalDate.of(2010, 10, 15),
    )
    val phone = Equipment(
        id = UUID.fromString("2b985f47-dfcc-45a1-b41c-f4f01f8b2b6f"),
        name = "Samsung Galaxy S4",
        productId = "8845-3155-6655",
        description = "Мощный флагман",
        submissionDate = LocalDate.of(2015, 7, 6),
    )
    val equipmentRepository = EquipmentRepository(listOf(
        printer,
        phone,
    ))
    return Storage(
        employeeRepository = employeeRepository,
        equipmentRepository = equipmentRepository,
    )
}
