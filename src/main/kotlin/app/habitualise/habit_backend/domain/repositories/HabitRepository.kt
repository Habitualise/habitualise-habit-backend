package app.habitualise.habit_backend.domain.repositories

import app.habitualise.habit_backend.domain.aggregates.Habit
import java.util.UUID

interface HabitRepository {
    // Currently returns null if Habit with id isn't found. Maybe think about throwing exception/ returning Optional<> instead?
    fun findById(id: UUID): Habit
    fun findByOwner(email: String): List<Habit>
    fun save(habit: Habit)
    fun delete(id: UUID)
}
