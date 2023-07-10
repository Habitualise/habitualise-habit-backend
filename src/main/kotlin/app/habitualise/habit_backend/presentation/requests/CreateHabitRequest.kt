package app.habitualise.habit_backend.presentation.requests

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.Size

data class CreateHabitRequest(
    @field:NotEmpty(message = "Habit must have a name.")
    val name: String,
    @field:Size(min = 1, max = 7, message = "Habit must have a valid list of days due between 1 to 7 days")
    @JsonProperty("days_due")
    val daysDue: List<Int>,
    @field:Email(message = "Owner must be a valid email address")
    val owner: String
)
