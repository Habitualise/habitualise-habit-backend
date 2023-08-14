package app.habitualise.habit_backend.domain.services

interface IconNameValidator {
    fun isValid(iconName: String): Boolean
}
