package app.habitualise.habit_backend.unit.web.validators

import app.habitualise.habit_backend.web.validation.colour_name_validator.ColourNameConstraintValidator
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class ColourNameConstraintValidatorTests {
    @Test
    fun `Given a valid colour name, when isValid is invoked, then return true`() {
        // Arrange
        val validator = ColourNameConstraintValidator()
        val colourName = "red"

        // Act
        val result = validator.isValid(colourName, null)

        // Assert
        result.shouldBeEqualTo(true)
    }

    @Test
    fun `Given an invalid colour name, when isValid is invoked, then return false`() {
        // Arrange
        val validator = ColourNameConstraintValidator()
        val colourName = "invalid"

        // Act
        val result = validator.isValid(colourName, null)

        // Assert
        result.shouldBeEqualTo(false)
    }
}