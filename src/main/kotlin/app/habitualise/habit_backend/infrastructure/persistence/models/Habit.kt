package app.habitualise.habit_backend.infrastructure.persistence.models

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field
import java.time.LocalDate

@Document("habits")
data class Habit(
    @Id
    val id: ObjectId = ObjectId(),
    val name: String,
    val active: Boolean = true,
    @Field("days_due")
    val daysDue: List<Int>,
    @Field("days_achieved")
    val daysAchieved: List<LocalDate> = emptyList(),
    @Field("created_date")
    val createdDate: LocalDate = LocalDate.now()
)
