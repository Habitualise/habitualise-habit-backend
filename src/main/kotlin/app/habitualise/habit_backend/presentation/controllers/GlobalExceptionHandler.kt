package app.habitualise.habit_backend.presentation.controllers

import app.habitualise.habit_backend.presentation.validation.ValidationErrorResponse
import app.habitualise.habit_backend.presentation.validation.ValidationError
import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.PropertyNamingStrategy
import org.springframework.http.HttpStatus
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice


@RestControllerAdvice
class GlobalExceptionHandler {

    private val namingStrategy: PropertyNamingStrategy = PropertyNamingStrategies.SNAKE_CASE

    @ExceptionHandler(MethodArgumentNotValidException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    fun handleValidationException(ex: MethodArgumentNotValidException): ValidationErrorResponse {
        val response = ValidationErrorResponse()
        for (fieldError in ex.bindingResult.fieldErrors) {
            val fieldName = toSnakeCase(fieldError.field)
            val errorMessage = fieldError.defaultMessage
            response.validationErrors.add(ValidationError(fieldName, errorMessage ?: "Invalid value"))
        }
        return response
    }

    private fun toSnakeCase(string: String): String {
        return namingStrategy.nameForField(null, null, string)
    }
}
