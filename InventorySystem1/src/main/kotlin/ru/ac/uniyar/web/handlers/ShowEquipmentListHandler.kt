package ru.ac.uniyar.web.handlers

import org.http4k.core.HttpHandler
import org.http4k.core.Request
import org.http4k.core.Response
import org.http4k.core.Status
import org.http4k.core.with
import ru.ac.uniyar.domain.operations.ListEquipmentOperation
import ru.ac.uniyar.web.models.ShowEquipmentVM
import ru.ac.uniyar.web.templates.ContextAwareViewRender

class ShowEquipmentListHandler(
    private val listEquipmentOperation: ListEquipmentOperation,
    private val htmlView: ContextAwareViewRender,
) : HttpHandler {
    override fun invoke(request: Request): Response {
        return Response(Status.OK).with(
            htmlView(request) of ShowEquipmentVM(listEquipmentOperation.list())
        )
    }
}
