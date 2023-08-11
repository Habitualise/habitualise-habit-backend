package app.habitualise.habit_backend.infrastructure.services.read_projection_engine

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field
import java.util.*

@Document(collection = "habit_read_projections")
data class HabitReadProjection(
    @Id
    val id: UUID,
    @Field("name")
    val name: String,
    @Field("icon_name")
    val iconName: String,
    @Field("colour")
    val colour: String,
    @Field("is_completed_today")
    val isCompletedToday: Boolean,
    @Field("completion_percentage")
    val completionPercentage: Int,
    @Field("active")
    val active: Boolean,
    @Field("days_due")
    val daysDue: List<Int>,
    @Field("completion_history")
    val completionHistory: List<Boolean>
)
