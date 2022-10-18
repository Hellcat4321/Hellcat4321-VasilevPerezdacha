package ru.ac.uniyar.web.handlers

import org.http4k.core.HttpHandler
import org.http4k.core.Request
import org.http4k.core.Response
import org.http4k.core.Status
import org.http4k.core.with
import ru.ac.uniyar.domain.storage.FetchEquipmentQuery
import ru.ac.uniyar.web.models.EditEquipmentFormVM
import org.http4k.lens.LensFailure
import org.http4k.lens.Path
import org.http4k.lens.WebForm
import org.http4k.lens.uuid
import ru.ac.uniyar.web.templates.ContextAwareViewRender
import java.util.UUID

class EditEquipmentFormHandler(
    private val fetchEquipmentQuery: FetchEquipmentQuery,
    private val htmlView: ContextAwareViewRender,
) : HttpHandler {
    companion object {
        private val idLens = Path.uuid().of("id")
    }

    override fun invoke(p1: Request): Response {
        var toReturn: Response? = null
        val index = try {
            idLens(p1)
        } catch (error: LensFailure) {
            toReturn = Response(Status.BAD_REQUEST).header("Error", error.toString())
        }
        return if (toReturn != null) {
            toReturn
        } else {
            val equipment = fetchEquipmentQuery(index as UUID) ?: return Response(Status.BAD_REQUEST)
            val webForm = WebForm(
                mapOf(
                    "name" to listOf(equipment.name),
                    "productId" to listOf(equipment.productId),
                    "description" to listOf(equipment.description),
                    "submissionDate" to listOf(equipment.submissionDate.toString()),
                )
            )
            Response(Status.OK).with(htmlView(p1) of EditEquipmentFormVM(webForm, equipment.id))
        }
    }
}