package app.habitualise.habit_backend.presentation.requests

data class CreateHabitRequest(
    val name: String,
    val daysDue: List<Int>,
    val owner: String
)
