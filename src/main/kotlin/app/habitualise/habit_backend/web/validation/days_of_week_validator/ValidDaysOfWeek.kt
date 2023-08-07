package app.habitualise.habit_backend.web.validation.days_of_week_validator

import jakarta.validation.Constraint
import jakarta.validation.Payload
import kotlin.reflect.KClass

@Target(AnnotationTarget.FIELD, AnnotationTarget.VALUE_PARAMETER)
@Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = [DaysOfWeekValidator::class])
annotation class ValidDaysOfWeek(
    val message: String = "{fieldName} contains invalid values",
    val groups: Array<KClass<out Any>> = [],
    val payload: Array<KClass<out Payload>> = []
)
