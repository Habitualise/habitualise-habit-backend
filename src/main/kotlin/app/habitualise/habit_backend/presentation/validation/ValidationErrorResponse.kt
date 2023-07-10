package app.habitualise.habit_backend.presentation.validation

import com.fasterxml.jackson.annotation.JsonProperty

data class ValidationErrorResponse(
    @JsonProperty("validation_errors")
    val validationErrors: MutableList<ValidationErrors> = mutableListOf()
)
