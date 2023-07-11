package app.habitualise.habit_backend.domain.aggregates

import app.habitualise.habit_backend.domain.common.AggregateRoot
import app.habitualise.habit_backend.domain.valueObjects.HabitColour
import java.util.*

class HabitStyle(override val id: UUID, colour: HabitColour, iconName: String) : AggregateRoot<UUID>() {
    var colour: HabitColour = colour
        private set
    var iconName = iconName
        private set
}
