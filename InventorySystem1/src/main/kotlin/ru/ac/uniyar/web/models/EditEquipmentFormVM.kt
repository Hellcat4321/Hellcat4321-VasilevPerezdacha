package ru.ac.uniyar.web.models

import org.http4k.lens.WebForm
import org.http4k.template.ViewModel
import java.util.UUID


class EditEquipmentFormVM(
    val form: WebForm,
    val id: UUID
) : ViewModel