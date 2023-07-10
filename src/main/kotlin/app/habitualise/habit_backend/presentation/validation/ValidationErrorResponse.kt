package app.habitualise.habit_backend.presentation.validation

data class ValidationErrorResponse(
    val violations: MutableList<Violation> = mutableListOf()
)
