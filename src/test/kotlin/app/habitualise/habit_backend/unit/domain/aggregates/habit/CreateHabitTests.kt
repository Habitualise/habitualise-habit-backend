package app.habitualise.habit_backend.unit.domain.aggregates.habit

import app.habitualise.habit_backend.domain.aggregates.Habit
import app.habitualise.habit_backend.domain.exceptions.InvalidDaysDueAssignedException
import org.amshove.kluent.invoking
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldThrow
import org.junit.jupiter.api.Test
import java.util.*

class CreateHabitTests {
    @Test
    fun `Given habit details, when a habit is created, then an the expected habit is returned`() {
        // Arrange
        val id = UUID.randomUUID()
        val name = "Test Habit"
        val daysDue = listOf(1, 2, 3)
        val owner = "Test Owner"

        // Act
        val habit = Habit(id, name, daysDue, owner)

        // Assert
        habit.id.shouldBeEqualTo(id)
        habit.name.shouldBeEqualTo(name)
        habit.daysDue.shouldBeEqualTo(daysDue)
        habit.owner.shouldBeEqualTo(owner)
    }

    @Test
    fun `Given habit details with days due including a value outside 1 to 7, when a habit is created, then throw InvalidDaysDueAssignedException`() {
        // Arrange
        val id = UUID.randomUUID()
        val name = "Test Habit"
        val daysDue = listOf(1, 2, 8)
        val owner = "Test Owner"

        // Act
        val action = { Habit(id, name, daysDue, owner) }

        // Assert
        invoking(action).shouldThrow(InvalidDaysDueAssignedException::class)
    }

    @Test
    fun `Given habit details with days due including a duplicate value, when a habit is created, then throw InvalidDaysDueAssignedException`() {
        // Arrange
        val id = UUID.randomUUID()
        val name = "Test Habit"
        val daysDue = listOf(1, 2, 2)
        val owner = "Test Owner"

        // Act
        val action = { Habit(id, name, daysDue, owner) }

        // Assert
        invoking(action).shouldThrow(InvalidDaysDueAssignedException::class)
    }
}
