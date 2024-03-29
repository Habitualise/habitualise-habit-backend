package app.habitualise.habit_backend.web.validation

import com.fasterxml.jackson.annotation.JsonProperty

data class ValidationError(
    @JsonProperty("field_name")
    val fieldName: String,
    val message: String
)
