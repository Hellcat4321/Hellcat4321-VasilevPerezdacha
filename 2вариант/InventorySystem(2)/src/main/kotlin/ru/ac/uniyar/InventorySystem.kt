package ru.ac.uniyar

import org.http4k.core.ContentType
import org.http4k.core.HttpHandler
import org.http4k.core.RequestContexts
import org.http4k.core.then
import org.http4k.filter.ServerFilters
import org.http4k.lens.RequestContextKey
import org.http4k.lens.RequestContextLens
import org.http4k.routing.ResourceLoader
import org.http4k.routing.routes
import org.http4k.routing.static
import org.http4k.server.Undertow
import org.http4k.server.asServer
import ru.ac.uniyar.domain.operations.OperationStorage
import ru.ac.uniyar.domain.storage.Employee
import ru.ac.uniyar.domain.storage.RolePermissions
import ru.ac.uniyar.domain.storage.Settings
import ru.ac.uniyar.domain.storage.initializeRepositoryStorage
import ru.ac.uniyar.web.filters.JwtTools
import ru.ac.uniyar.web.filters.ShowErrorMessageFilter
import ru.ac.uniyar.web.filters.authenticationFilter
import ru.ac.uniyar.web.filters.authorizationFilter
import ru.ac.uniyar.web.formRouter
import ru.ac.uniyar.web.handlers.HandlerStorage
import ru.ac.uniyar.web.templates.ContextAwarePebbleTemplates
import ru.ac.uniyar.web.templates.ContextAwareViewRender

fun main() {
    val settings = Settings()
    val storage = initializeRepositoryStorage()
    val operationStorage = OperationStorage(storage, settings)

    val renderer = ContextAwarePebbleTemplates().hotReload("src/main/resources")
    val htmlView = ContextAwareViewRender(renderer, ContentType.TEXT_HTML)

    val contexts = RequestContexts()
    val currentEmployeeLens: RequestContextLens<Employee?> = RequestContextKey.optional(contexts, "employee")
    val permissionsLens: RequestContextLens<RolePermissions?> =
        RequestContextKey.required(contexts, "permissions")
    htmlView.associateContextLens("currentEmployee", currentEmployeeLens)
    htmlView.associateContextLens("rolePermissions", permissionsLens)

    val jwtTools = JwtTools(settings.getSalt(), issuer = "ru.ac.uniyar.InventorySystem")

    val handlerStorage = HandlerStorage(
        operationStorage,
        permissionsLens,
        htmlView,
        jwtTools
    )

    val dynamicRouter = formRouter(handlerStorage)
    val staticFilesHandler = static(ResourceLoader.Classpath("/ru/ac/uniyar/public"))
    val routes = routes(
        dynamicRouter,
        staticFilesHandler,
    )

    val authorizedApp = authenticationFilter(
        currentEmployeeLens,
        operationStorage.fetchEmployeeViaUserId,
        jwtTools
    ).then(
        authorizationFilter(
            currentEmployeeLens,
            permissionsLens,
            operationStorage.fetchPermissionsViaIdQuery
        ).then(routes)
    )

    val app = routes(authorizedApp, staticFilesHandler)
    val app2: HttpHandler = ServerFilters.InitialiseRequestContext(contexts)
        .then(ShowErrorMessageFilter(htmlView))
        .then(app)

    val server = app2.asServer(Undertow(9001)).start()
    println("Server started on http://localhost:" + server.port())
}