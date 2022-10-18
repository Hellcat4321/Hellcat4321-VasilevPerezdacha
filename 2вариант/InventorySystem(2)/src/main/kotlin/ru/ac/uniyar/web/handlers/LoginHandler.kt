package ru.ac.uniyar.web.handlers


import org.http4k.core.Body
import org.http4k.core.HttpHandler
import org.http4k.core.Request
import org.http4k.core.Response
import org.http4k.core.Status
import org.http4k.core.cookie.Cookie
import org.http4k.core.cookie.SameSite
import org.http4k.core.cookie.cookie
import org.http4k.core.cookie.invalidateCookie
import org.http4k.core.with
import org.http4k.lens.FormField
import org.http4k.lens.Invalid
import org.http4k.lens.Validator
import org.http4k.lens.nonEmptyString
import org.http4k.lens.webForm
import ru.ac.uniyar.domain.queries.AuthenticateUserViaLoginQuery
import ru.ac.uniyar.domain.queries.AuthenticationException
import ru.ac.uniyar.web.filters.JwtTools
import ru.ac.uniyar.web.models.LoginFormVM
import ru.ac.uniyar.web.templates.ContextAwareViewRender

class ShowLoginFormHandler(
    private val htmlView: ContextAwareViewRender,
): HttpHandler {
    override fun invoke(request: Request): Response {
        return Response(Status.OK).with(htmlView(request) of LoginFormVM())
    }
}

class AuthenticateUser(
    private val authenticateUserViaLoginQuery: AuthenticateUserViaLoginQuery,
    private val htmlView: ContextAwareViewRender,
    private val jwtTools: JwtTools,
): HttpHandler {

    companion object {
        private val loginFormLens = FormField.nonEmptyString().required("login")
        private val passwordFormLens = FormField.nonEmptyString().required("password")
        private val formLens = Body.webForm(Validator.Feedback, loginFormLens, passwordFormLens).toLens()
    }

    override fun invoke(request: Request): Response {
        var webForm = formLens(request)
        if (webForm.errors.isNotEmpty()){
            return Response(Status.OK).with(htmlView(request) of LoginFormVM(webForm))
        }
        val userId = try {
            authenticateUserViaLoginQuery(loginFormLens(webForm), passwordFormLens(webForm))
        }catch (_: AuthenticationException){
            val newErrors = webForm.errors + Invalid(
                passwordFormLens.meta.copy(description = "wrong login or password"))
            webForm = webForm.copy(errors = newErrors)
            return Response(Status.OK).with(htmlView(request) of LoginFormVM(webForm))
        }
        val token = jwtTools.create(userId) ?: return Response(Status.INTERNAL_SERVER_ERROR)
        val authCookie = Cookie("token", token, httpOnly = true, sameSite = SameSite.Strict)
        return Response(Status.FOUND)
            .header("Location","/")
            .cookie(authCookie)
    }
}

class LogOutUser: HttpHandler {
    override fun invoke(request: Request): Response {
        return Response(Status.FOUND)
            .header("Location", "/")
            .invalidateCookie("token")
    }
}
