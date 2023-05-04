package app.habitualise.habit_backend.domain.repositories

import app.habitualise.habit_backend.domain.aggregates.Habit
import java.util.UUID

interface HabitRepository {
    fun findById(id: UUID): Habit
    fun findByEmail(email: String): List<Habit>
    fun save(habit: Habit)
    fun delete(id: UUID)
}