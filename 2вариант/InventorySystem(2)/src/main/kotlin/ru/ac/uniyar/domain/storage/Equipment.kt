package ru.ac.uniyar.domain.storage

import java.time.LocalDate
import java.util.UUID

data class Equipment(
    val id: UUID,
    val name: String,
    val productId: String,
    val description: String,
    val submissionDate: LocalDate,
)
