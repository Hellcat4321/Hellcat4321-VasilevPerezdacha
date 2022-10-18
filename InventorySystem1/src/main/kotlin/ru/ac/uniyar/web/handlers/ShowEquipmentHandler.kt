package ru.ac.uniyar.web.handlers

import org.http4k.core.HttpHandler
import org.http4k.core.Request
import org.http4k.core.Response
import org.http4k.core.Status
import org.http4k.core.with
import org.http4k.routing.path
import ru.ac.uniyar.domain.operations.FetchEquipmentOperation
import ru.ac.uniyar.web.models.ShowEquipmentPartVM
import ru.ac.uniyar.web.templates.ContextAwareViewRender
import java.util.UUID

class ShowEquipmentHandler(
    private val fetchEquipmentOperation: FetchEquipmentOperation,
    private val htmlView: ContextAwareViewRender,
) : HttpHandler {
    override fun invoke(request: Request): Response {
        val equipment = request.path("id")?.let {
            UUID.fromString(it)
        }?.let {
            fetchEquipmentOperation.fetch(it)
        } ?: return Response(Status.BAD_REQUEST)

        return Response(Status.OK).with(
            htmlView(request) of ShowEquipmentPartVM(equipment)
        )
    }
}
