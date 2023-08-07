package app.habitualise.habit_backend.domain.aggregates

import app.habitualise.habit_backend.domain.common.AggregateRoot
import app.habitualise.habit_backend.domain.exceptions.InvalidDaysDueAssignedException
import app.habitualise.habit_backend.domain.valueObjects.Style
import java.time.LocalDate
import java.util.*

class Habit(
    id: UUID,
    name: String,
    daysDue: List<Int>,
    owner: String,
    style: Style,
    daysAchieved: List<LocalDate>,
    active: Boolean,
    creationDate: LocalDate
) :
    AggregateRoot<UUID>(id) {
    init {
        if (daysDue.any { it < 1 || it > 7 }) {
            throw InvalidDaysDueAssignedException("Days due must be between 1 and 7")
        }

        if (daysDue.size != daysDue.distinct().size) {
            throw InvalidDaysDueAssignedException("Days due cannot contain any duplicate days")
        }
    }

    constructor(id: UUID, name: String, daysDue: List<Int>, owner: String, style: Style) : this(
        id,
        name,
        daysDue,
        owner,
        style,
        mutableListOf(),
        true,
        LocalDate.now()
    )

    var name: String = name
        private set
    var daysDue: List<Int> = daysDue
        private set
    var owner: String = owner
        private set
    var style: Style = style
        private set
    val daysAchieved: List<LocalDate> = daysAchieved
        get() = Collections.unmodifiableList(field)
    var active: Boolean = active
        private set
    var creationDate: LocalDate = creationDate
        private set
}
