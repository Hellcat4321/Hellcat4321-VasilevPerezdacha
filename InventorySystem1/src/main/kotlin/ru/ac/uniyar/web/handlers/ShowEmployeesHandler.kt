package ru.ac.uniyar.web.handlers

import org.http4k.core.HttpHandler
import org.http4k.core.Request
import org.http4k.core.Response
import org.http4k.core.Status
import org.http4k.core.with
import ru.ac.uniyar.domain.operations.ListEmployeesOperation
import ru.ac.uniyar.web.models.ShowEmployeesVM
import ru.ac.uniyar.web.templates.ContextAwareViewRender

class ShowEmployeesHandler(
    private val listEmployeesOperation: ListEmployeesOperation,
    private val htmlView: ContextAwareViewRender,
) : HttpHandler {
    override fun invoke(request: Request): Response {
        val employees = listEmployeesOperation.list()
        return Response(Status.OK).with(htmlView(request) of ShowEmployeesVM(employees))
    }
}
