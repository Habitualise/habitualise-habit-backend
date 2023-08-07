package app.habitualise.habit_backend.web.requests

import app.habitualise.habit_backend.web.validation.days_of_week_validator.ValidDaysOfWeek
import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size

data class CreateHabitRequest(
    @field:NotEmpty(message = "Habit must have a name.")
    val name: String,

    @field:NotNull(message = "Habit must have a list of days due")
    @field:Size(min = 1, max = 7, message = "Habit must have a valid list of days due between 1 to 7 days")
    @field:ValidDaysOfWeek(message = "Habit must have a valid list of days due which contains no duplicates")
    @JsonProperty("days_due")
    val daysDue: List<Int>,

    @field:NotEmpty(message = "Owner must not be empty")
    @field:Email(message = "Owner must be a valid email address")
    val owner: String
)
