package ru.ac.uniyar.web

import org.http4k.core.Method
import org.http4k.core.Response
import org.http4k.core.Status
import org.http4k.routing.bind
import org.http4k.routing.routes
import ru.ac.uniyar.web.handlers.HandlerStorage

fun formRouter(
    handlerStorage: HandlerStorage,
) = routes(
    "/" bind Method.GET to handlerStorage.showStartPageHandler,
    "/employees" bind Method.GET to handlerStorage.showEmployeesHandler,
    "/employees/{id}" bind Method.GET to handlerStorage.showEmployeeHandler,
    "/equipment" bind Method.GET to handlerStorage.showEquipmentListHandler,
    "/equipment/{id}" bind Method.GET to handlerStorage.showEquipmentHandler,
    "/equipments/new" bind Method.GET to handlerStorage.showNewEquipmentFormHandler,
    "/equipments/new" bind Method.POST to handlerStorage.addNewEquipmentHandler,
    "/equipment/{id}/edit" bind Method.GET to handlerStorage.editEquipmentFormHandler,
    "/equipment/{id}/edit" bind Method.POST to handlerStorage.submitChangesInEquipmentHandler,
    "/ping" bind Method.GET to { Response(Status.OK) },
)
