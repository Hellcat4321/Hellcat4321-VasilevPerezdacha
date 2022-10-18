package ru.ac.uniyar

import org.http4k.core.ContentType
import org.http4k.core.HttpHandler
import org.http4k.core.then
import org.http4k.routing.ResourceLoader
import org.http4k.routing.routes
import org.http4k.routing.static
import org.http4k.server.Undertow
import org.http4k.server.asServer
import ru.ac.uniyar.domain.operations.OperationStorage
import ru.ac.uniyar.domain.storage.initializeRepositoryStorage
import ru.ac.uniyar.web.filters.ShowErrorMessageFilter
import ru.ac.uniyar.web.formRouter
import ru.ac.uniyar.web.handlers.HandlerStorage
import ru.ac.uniyar.web.templates.ContextAwarePebbleTemplates
import ru.ac.uniyar.web.templates.ContextAwareViewRender

fun main() {
    val storage = initializeRepositoryStorage()
    val operationStorage = OperationStorage(storage)

    val renderer = ContextAwarePebbleTemplates().HotReload("src/main/resources")
    val htmlView = ContextAwareViewRender.withContentType(renderer, ContentType.TEXT_HTML)

    val handlerStorage = HandlerStorage(operationStorage, htmlView)

    val dynamicRouter = formRouter(handlerStorage)
    val staticFilesHandler = static(ResourceLoader.Classpath("/ru/ac/uniyar/public"))
    val routes = routes(
        dynamicRouter,
        staticFilesHandler
    )
    val app: HttpHandler = ShowErrorMessageFilter(htmlView)
        .then(routes)

    val server = app.asServer(Undertow(9000)).start()
    println("Server started on http://localhost:" + server.port())
}
