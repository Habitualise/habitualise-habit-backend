package app.habitualise.habit_backend.infrastructure.persistence.repositories

import app.habitualise.habit_backend.domain.aggregates.Habit
import app.habitualise.habit_backend.domain.exceptions.HabitNotFoundException
import app.habitualise.habit_backend.domain.repositories.HabitRepository
import app.habitualise.habit_backend.infrastructure.persistence.models.HabitDTO
import org.springframework.stereotype.Repository
import java.util.*

@Repository
class MongoHabitRepository(private val mongoRepo: SpringDataMongoHabitRepository) : HabitRepository {

    override fun findById(id: UUID): Habit {
        val habitDTO = mongoRepo.findById(id)
            .orElseThrow { HabitNotFoundException("Habit with ID: $id not found") }
        return toDomainEntity(habitDTO)
    }

    override fun findByOwner(email: String): List<Habit> {
        val habitDTOs = mongoRepo.findByOwner(email)
        return habitDTOs.map(this::toDomainEntity)
    }

    override fun save(habit: Habit) {
        val habitDTO = toDTO(habit)
        mongoRepo.save(habitDTO)
    }

    override fun delete(id: UUID) {
        val habitDTO = mongoRepo.findById(id)
            .orElseThrow { HabitNotFoundException("Habit with ID: $id not found") }
        mongoRepo.delete(habitDTO)
    }

    private fun toDTO(habit: Habit): HabitDTO {
        return HabitDTO(
            habit.id,
            habit.name,
            habit.daysDue,
            habit.owner,
            habit.style,
            habit.daysAchieved,
            habit.active,
            habit.creationDate
        )
    }

    private fun toDomainEntity(habitDTO: HabitDTO): Habit {
        return Habit(
            habitDTO.id,
            habitDTO.name,
            habitDTO.daysDue,
            habitDTO.owner,
            habitDTO.style,
            habitDTO.daysAchieved,
            habitDTO.active,
            habitDTO.creationDate
        )
    }
}
