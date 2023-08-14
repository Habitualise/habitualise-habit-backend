package app.habitualise.habit_backend.web.validation.icon_name_validator

import app.habitualise.habit_backend.infrastructure.validators.MaterialCommunityIconNameValidator
import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext
import org.springframework.beans.factory.annotation.Autowired

class IconNameConstraintValidator : ConstraintValidator<ValidIconName, String?> {
    @Autowired
    private lateinit var iconNameValidator: MaterialCommunityIconNameValidator
    override fun isValid(iconName: String?, context: ConstraintValidatorContext?): Boolean {
        if (iconName == null) return false
        return iconNameValidator.isValid(iconName)
    }
}
