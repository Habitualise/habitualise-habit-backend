package app.habitualise.habit_backend.presentation.requests

import com.fasterxml.jackson.annotation.JsonProperty

data class CreateHabitRequest(
    val name: String,
    @JsonProperty("days_due")
    val daysDue: List<Int>,
    val owner: String
)
