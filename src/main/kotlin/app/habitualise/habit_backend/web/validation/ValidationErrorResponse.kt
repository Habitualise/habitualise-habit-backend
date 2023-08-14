package app.habitualise.habit_backend.web.validation

import com.fasterxml.jackson.annotation.JsonProperty

data class ValidationErrorResponse(
    @JsonProperty("validation_errors")
    val validationErrors: MutableList<ValidationError> = mutableListOf()
)
