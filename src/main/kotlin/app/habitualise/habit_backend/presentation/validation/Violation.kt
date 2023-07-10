package app.habitualise.habit_backend.presentation.validation

data class Violation(
    val fieldName: String,
    val message: String
)
