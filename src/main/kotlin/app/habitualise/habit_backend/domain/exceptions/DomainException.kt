package app.habitualise.habit_backend.domain.exceptions

abstract class DomainException(
    message: String,
    cause: Throwable? = null
) : RuntimeException(message, cause)
