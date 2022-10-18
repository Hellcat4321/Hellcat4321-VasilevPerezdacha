package ru.ac.uniyar.web.handlers

import org.http4k.core.HttpHandler
import org.http4k.core.Request
import org.http4k.core.Response
import org.http4k.core.Status
import org.http4k.core.with
import ru.ac.uniyar.web.models.StartPageVM
import ru.ac.uniyar.web.templates.ContextAwareViewRender

class ShowStartPageHandler(
    private val htmlView: ContextAwareViewRender,
) : HttpHandler {
    override fun invoke(p1: Request): Response =
        Response(Status.OK).with(htmlView(p1) of StartPageVM())
}
