package ru.ac.uniyar.web.filters

import org.http4k.core.Filter
import org.http4k.core.HttpHandler
import org.http4k.core.with
import ru.ac.uniyar.web.models.ErrorVM
import ru.ac.uniyar.web.models.NotFoundVM
import ru.ac.uniyar.web.templates.ContextAwareViewRender

const val UNAUTHORISED_CODE = 401

class ShowErrorMessageFilter(private val htmlView: ContextAwareViewRender) : Filter {
    override fun invoke(next: HttpHandler): HttpHandler = { request ->
        val response = next(request)
        if (response.status.successful || response.header("content-type") != null && response.body.length != 0L) {
            response
        } else if(response.status.code == UNAUTHORISED_CODE) {
            response.with(htmlView(request) of ErrorVM(""))
        } else {
            response.with(htmlView(request) of NotFoundVM(request.uri))
        }
    }
}
