package ru.ac.uniyar.domain.queries

import ru.ac.uniyar.domain.storage.Settings
import ru.ac.uniyar.domain.storage.Storage


class AuthenticateUserViaLoginQuery(
    storage: Storage,
    private val settings: Settings
) {
    private val usersRepository = storage.employeeRepository
    operator fun invoke(login: String, password: String): String {
        val user = usersRepository.list().find { it.login == login } ?: throw AuthenticationException()
        val hashedPassword = hashPassword(password, settings.getSalt())
        if (hashedPassword != user.password)
            throw AuthenticationException()
        return user.id.toString()
    }
}

class AuthenticationException: RuntimeException("Логин или пароль неверны")