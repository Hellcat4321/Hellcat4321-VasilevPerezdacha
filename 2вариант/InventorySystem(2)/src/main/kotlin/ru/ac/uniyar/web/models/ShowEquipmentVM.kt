package ru.ac.uniyar.web.models

import org.http4k.template.ViewModel
import ru.ac.uniyar.domain.storage.Equipment

data class ShowEquipmentVM(
    val equipment: List<Equipment>,
) : ViewModel
