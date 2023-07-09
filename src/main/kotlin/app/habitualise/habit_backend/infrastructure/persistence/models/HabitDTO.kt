package app.habitualise.habit_backend.infrastructure.persistence.models

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field
import java.time.LocalDate
import java.util.UUID

@Document("habits")
data class HabitDTO(
    @Id
    val id: UUID,
    @Field("name")
    val name: String,
    @Field("active")
    val active: Boolean,
    @Field("days_due")
    val daysDue: List<Int>,
    @Field("days_achieved")
    val daysAchieved: List<LocalDate>,
    @Field("owner")
    val owner: String,
    @Field("created_date")
    val creationDate: LocalDate
)
