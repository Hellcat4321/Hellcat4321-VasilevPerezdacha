package ru.ac.uniyar.domain.storage

import java.time.LocalDate
import java.util.UUID

data class Storage(
    val employeeRepository: EmployeeRepository,
    val equipmentRepository: EquipmentRepository,
    val rolePermissionsRepository : RolePermissionsRepository
)

fun initializeRepositoryStorage(): Storage {
    val petr = Employee(
        id = UUID.fromString("8333f4b1-322e-4baa-be42-05079d9fbb95"),
        roleId = UUID.fromString("d5203d63-c940-4eac-9513-e39bf8a46926"),
        name = "Пётр Васильевич Григорьев",
        login = "petr",
        phone = "+790000000001",
        password = "8c263898939e7afadea7d5656955972c0116a7a2082a7c502b1aaa065aed9fe5c2a187bb580adca0a63233dfe4b0ba103c0730d4afe2d57c62f742d0ef5b917d"
    )
    val ivan = Employee(
        id = UUID.fromString("c9096a41-b3c5-485a-8756-fd5056a8a944"),
        roleId = UUID.fromString("0e5531bc-54b7-4c63-ae36-a0036f76cb08"),
        name = "Иван Сергеевич Ушаков",
        login = "ivan",
        phone = "+790000000002",
        password = "8c263898939e7afadea7d5656955972c0116a7a2082a7c502b1aaa065aed9fe5c2a187bb580adca0a63233dfe4b0ba103c0730d4afe2d57c62f742d0ef5b917d"
    )
    val irina = Employee(
        id = UUID.fromString("8d93f93f-d491-45ed-ac94-66ad04a01e00"),
        roleId = UUID.fromString("0e5531bc-54b7-4c63-ae36-a0036f76cb08"),
        name = "Ирина Николаевна Кук",
        login = "irina",
        phone = "+790000000003",
        password = "8c263898939e7afadea7d5656955972c0116a7a2082a7c502b1aaa065aed9fe5c2a187bb580adca0a63233dfe4b0ba103c0730d4afe2d57c62f742d0ef5b917d"
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

    val guestRole = RolePermissions(
        id = UUID.fromString("f27c7df1-df1f-4878-8f1d-0670f8e9ddaa"),
        name = "Гость",
        watchEmployee = false,
        watchEquipment = false
    )
    val polzovRole = RolePermissions(
        id = UUID.fromString("0e5531bc-54b7-4c63-ae36-a0036f76cb08"),
        name = "Авторизованный пользователь",
        watchEquipment = true,
        watchEmployee = false
    )
    val adminRole = RolePermissions(
        id = UUID.fromString("d5203d63-c940-4eac-9513-e39bf8a46926"),
        name = "Администратор",
        watchEquipment = true,
        watchEmployee = true
    )

    val rolePermissionsRepository = RolePermissionsRepository(
        listOf(
            guestRole,
            polzovRole,
            adminRole
        )
    )
    return Storage(
        employeeRepository = employeeRepository,
        equipmentRepository = equipmentRepository,
        rolePermissionsRepository = rolePermissionsRepository
    )
}

