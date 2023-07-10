package app.habitualise.habit_backend.presentation.validation

import com.fasterxml.jackson.annotation.JsonProperty

data class ValidationErrors(
    @JsonProperty("field_name")
    val fieldName: String,
    val message: String
)
