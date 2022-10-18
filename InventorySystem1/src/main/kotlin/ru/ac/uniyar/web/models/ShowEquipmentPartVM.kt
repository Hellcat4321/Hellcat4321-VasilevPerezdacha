package ru.ac.uniyar.web.models

import org.http4k.template.ViewModel
import ru.ac.uniyar.domain.storage.Equipment

data class ShowEquipmentPartVM(
    val equipment: Equipment,
) : ViewModel
