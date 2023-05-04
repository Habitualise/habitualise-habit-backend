package app.habitualise.habit_backend.domain.aggregates

import app.habitualise.habit_backend.domain.common.AggregateRoot
import java.time.LocalDate
import java.util.*

class Habit(
    override val id: UUID,
    var name: String,
    var active: Boolean = true,
    var daysDue: List<Int>,
    var daysAchieved: List<LocalDate> = LinkedList<LocalDate>(),
    val owner: String,
    val creationDate: LocalDate = LocalDate.now()
) :
    AggregateRoot<UUID>() {


}