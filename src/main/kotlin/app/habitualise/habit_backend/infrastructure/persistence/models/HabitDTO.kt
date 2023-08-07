package app.habitualise.habit_backend.infrastructure.persistence.models

import app.habitualise.habit_backend.domain.valueObjects.Style
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field
import java.time.LocalDate
import java.util.UUID

@Document(collection = "habits")
data class HabitDTO(
    @Id
    val id: UUID,
    @Field("name")
    val name: String,
    @Field("days_due")
    val daysDue: List<Int>,
    @Field("owner")
    val owner: String,
    @Field("style")
    val style: Style,
    @Field("days_achieved")
    val daysAchieved: List<LocalDate>,
    @Field("active")
    val active: Boolean,
    @Field("creation_date")
    val creationDate: LocalDate
)
