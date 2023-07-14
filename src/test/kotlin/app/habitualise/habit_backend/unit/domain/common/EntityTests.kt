package app.habitualise.habit_backend.unit.domain.common

import app.habitualise.habit_backend.domain.common.Entity
import org.amshove.kluent.*
import org.junit.jupiter.api.Test
import java.util.*

class EntityTests {
    @Test
    fun `Given two entities, when the ids are the same, then they are equal`() {
        // Arrange
        val id = UUID.randomUUID()
        val entity1 = TestEntity(id)
        val entity2 = TestEntity(id)

        // Act
        val result = entity1 == entity2

        // Assert
        result.shouldBeTrue()
    }

    @Test
    fun `Given two entities, when the ids are different, then they are not equal`() {
        // Arrange
        val entity1 = TestEntity(UUID.randomUUID())
        val entity2 = TestEntity(UUID.randomUUID())

        // Act
        val result = entity1 == entity2

        // Assert
        result.shouldBeFalse()
    }
}

class TestEntity<UUID>(id: UUID) : Entity<UUID>(id) {
}