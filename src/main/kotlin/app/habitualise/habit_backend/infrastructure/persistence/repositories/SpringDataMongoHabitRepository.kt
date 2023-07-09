package app.habitualise.habit_backend.infrastructure.persistence.repositories

import app.habitualise.habit_backend.infrastructure.persistence.models.HabitDTO
import org.springframework.data.mongodb.repository.MongoRepository
import java.util.UUID

interface SpringDataMongoHabitRepository : MongoRepository<HabitDTO, UUID> {
    fun findByOwner(email: String): List<HabitDTO>
}
