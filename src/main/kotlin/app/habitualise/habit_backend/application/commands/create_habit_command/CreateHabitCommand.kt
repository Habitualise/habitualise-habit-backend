package app.habitualise.habit_backend.application.commands.create_habit_command

import app.habitualise.habit_backend.application.commands.GenericCommand
import java.util.*

data class CreateHabitCommand(
    val name: String,
    val daysDue: List<Int>,
    val owner: String,
    val iconName: String,
    val colour: String
) : GenericCommand<UUID>
