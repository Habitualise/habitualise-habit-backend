package app.habitualise.habit_backend.domain.aggregates

import app.habitualise.habit_backend.domain.common.AggregateRoot
import java.time.LocalDate
import java.util.*

class Habit(
    id: UUID,
    name: String,
    daysDue: List<Int>,
    owner: String,
    daysAchieved: List<LocalDate> = mutableListOf(),
    active: Boolean = true,
    creationDate: LocalDate = LocalDate.now()
) :
    AggregateRoot<UUID>(id) {
    var name: String = name
        private set
    var daysDue: List<Int> = daysDue
        private set
    var owner: String = owner
        private set
    val daysAchieved: List<LocalDate> = daysAchieved
        get() = Collections.unmodifiableList(field)
    var active: Boolean = active
        private set
    var creationDate: LocalDate = creationDate
        private set
}
