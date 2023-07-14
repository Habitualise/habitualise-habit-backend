package app.habitualise.habit_backend.domain.aggregates

import app.habitualise.habit_backend.domain.common.AggregateRoot
import app.habitualise.habit_backend.domain.valueObjects.HabitColour
import java.util.*

class HabitStyle(id: UUID, colour: HabitColour, iconName: String) : AggregateRoot<UUID>(id) {
    var colour: HabitColour = colour
        private set
    var iconName = iconName
        private set
}
