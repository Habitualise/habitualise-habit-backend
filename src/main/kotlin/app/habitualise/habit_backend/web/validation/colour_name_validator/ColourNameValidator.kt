package app.habitualise.habit_backend.web.validation.colour_name_validator

import app.habitualise.habit_backend.domain.valueObjects.Colour
import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext

class ColourNameValidator : ConstraintValidator<ValidColourName, String?> {
    override fun isValid(colourName: String?, context: ConstraintValidatorContext?): Boolean {
        if (colourName == null) return false
        return Colour.tryFromName(colourName)
    }
}
