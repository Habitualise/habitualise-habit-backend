package app.habitualise.habit_backend.unit.web.validators

import app.habitualise.habit_backend.infrastructure.validators.MaterialCommunityIconNameValidator
import app.habitualise.habit_backend.web.validation.icon_name_validator.IconNameConstraintValidator
import io.mockk.mockk
import io.mockk.every
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class IconNameConstraintValidatorTest {

    private val iconNameValidator = mockk<MaterialCommunityIconNameValidator>()

    private val iconNameConstraintValidator = IconNameConstraintValidator(iconNameValidator)

    @Test
    fun `Given a valid icon name, when isValid is invoked, then return true`() {
        // Arrange
        every { iconNameValidator.isValid(any()) } returns true
        val iconName = "valid-icon"

        // Act
        val result = iconNameConstraintValidator.isValid(iconName, null)

        // Assert
        result.shouldBeEqualTo(true)
    }

    @Test
    fun `Given a invalid icon name, when isValid is invoked, then return false`() {
        // Arrange
        every { iconNameValidator.isValid(any()) } returns false
        val iconName = "invalid-icon"

        // Act
        val result = iconNameConstraintValidator.isValid(iconName, null)

        // Assert
        result.shouldBeEqualTo(false)
    }
}