package ru.ac.uniyar.domain.storage

import java.time.LocalDate
import java.util.UUID

class NameShouldBeFullException : RuntimeException("Пустое поле имени")

class ProductIdShouldBeFullException : RuntimeException("Пустое поле номера")

class DescriptionShouldBeFullException : RuntimeException("Пустое поле описания")

class SubmissionDateShouldBeFullException : RuntimeException("Пустое поле даты")

class AddQuery(
    private val equipmentRepository: EquipmentRepository) {
    operator fun invoke(name: String?, productId: String?, description: String?, submissionDate: LocalDate?): UUID {
        var exceptionToThrow: Exception? = null
        if (name == null || name == ""){
            exceptionToThrow = NameShouldBeFullException()
        }
        if (productId == null || productId == ""){
            exceptionToThrow = ProductIdShouldBeFullException()
        }
        if (description == null || description == ""){
            exceptionToThrow = DescriptionShouldBeFullException()
        }
        if (submissionDate == null ){
            exceptionToThrow = SubmissionDateShouldBeFullException()
        }
        if (exceptionToThrow != null) {
            throw exceptionToThrow
        }
        return equipmentRepository.add(
            Equipment(EMPTY_UUID, name!!, productId!!, description!!, submissionDate!!)
        )
    }
}

class FetchEquipmentQuery(private val equipmentRepository: EquipmentRepository){
    operator fun invoke(id: UUID): Equipment? = equipmentRepository.fetch(id)
}

class EditEquipmentQuery(
    private val equipmentRepository: EquipmentRepository
){
    operator fun invoke(
        id: UUID,
        name: String?,
        productId: String?,
        description: String?,
        submissionDate: LocalDate?,

        ){
        var exceptionToThrow: Exception? = null
        if (name == null || name == ""){
            exceptionToThrow = NameShouldBeFullException()
        }
        if (productId == null || productId == ""){
            exceptionToThrow = ProductIdShouldBeFullException()
        }
        if (description == null || description == ""){
            exceptionToThrow = DescriptionShouldBeFullException()
        }
        if (submissionDate == null || submissionDate.toString() == ""){
            exceptionToThrow = SubmissionDateShouldBeFullException()
        }
        if (exceptionToThrow != null) {
            throw exceptionToThrow
        }
        equipmentRepository.edit(
            id, name!!, productId!!, description!!, submissionDate!!
        )
    }
}

