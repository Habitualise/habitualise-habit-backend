package app.habitualise.habit_backend.application.commands.create_habit_command

import an.awesome.pipelinr.Command
import app.habitualise.habit_backend.domain.aggregates.Habit
import app.habitualise.habit_backend.domain.repositories.HabitRepository
import org.springframework.stereotype.Component
import java.util.*

@Component
class CreateHabitCommandHandler(private val habitRepository: HabitRepository) :
    Command.Handler<CreateHabitCommand, Result<UUID>> {
    override fun handle(command: CreateHabitCommand): Result<UUID> {
        val habit = Habit(UUID.randomUUID(), command.name, command.daysDue, command.owner)
        try {
            habitRepository.save(habit)
        } catch (e: Exception) {
            return Result.failure(e)
        }
        return Result.success(habit.id)
    }
}
