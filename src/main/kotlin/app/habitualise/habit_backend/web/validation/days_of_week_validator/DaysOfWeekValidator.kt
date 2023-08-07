package app.habitualise.habit_backend.web.validation.days_of_week_validator

import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext

class DaysOfWeekValidator : ConstraintValidator<ValidDaysOfWeek, Collection<Int>> {
    override fun isValid(value: Collection<Int>?, context: ConstraintValidatorContext?): Boolean {
        if (value == null) return false
        return value.all { it in 1..7 }
    }
}
