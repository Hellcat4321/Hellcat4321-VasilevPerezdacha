package ru.ac.uniyar.web.models

import org.http4k.template.ViewModel
import ru.ac.uniyar.domain.storage.Employee

data class ShowEmployeeVM(
    val employee: Employee,
) : ViewModel
