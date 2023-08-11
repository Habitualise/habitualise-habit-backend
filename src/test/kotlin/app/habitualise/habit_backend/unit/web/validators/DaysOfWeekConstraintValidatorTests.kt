package app.habitualise.habit_backend.unit.web.validators

import app.habitualise.habit_backend.web.validation.days_of_week_validator.DaysOfWeekConstraintValidator
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class DaysOfWeekConstraintValidatorTests {
    @Test
    fun `Given a valid days of week, when isValid is invoked, then return true`() {
        // Arrange
        val validator = DaysOfWeekConstraintValidator()
        val daysOfWeek = listOf(1, 2, 3)

        // Act
        val result = validator.isValid(daysOfWeek, null)

        // Assert
        result.shouldBeEqualTo(true)
    }

    @Test
    fun `Given an invalid days of week, when isValid is invoked, then return false`() {
        // Arrange
        val validator = DaysOfWeekConstraintValidator()
        val daysOfWeek = listOf(1, 2, 8)

        // Act
        val result = validator.isValid(daysOfWeek, null)

        // Assert
        result.shouldBeEqualTo(false)
    }
}
