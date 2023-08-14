package app.habitualise.habit_backend.unit.application.commands.create_habit

import app.habitualise.habit_backend.application.commands.create_habit_command.CreateHabitCommand
import app.habitualise.habit_backend.application.commands.create_habit_command.CreateHabitCommandHandler
import app.habitualise.habit_backend.domain.aggregates.Habit
import app.habitualise.habit_backend.domain.repositories.HabitRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldNotBeNull
import org.junit.jupiter.api.Test

class CreateHabitHandlerTests {
    @Test
    fun `Given a create habit command, when handle is invoked, return a successful result with the habit id`() {
        // Arrange
        val habitRepositoryMock = mockk<HabitRepository>(relaxed = true)
        val command = CreateHabitCommand(
            "Test Habit",
            listOf(1, 2, 3),
            "Test Owner",
            "Test Icon",
            "red"
        )

        var capturedHabit: Habit? = null
        every { habitRepositoryMock.save(any()) } answers { capturedHabit = firstArg() }

        val handler = CreateHabitCommandHandler(habitRepositoryMock)

        // Act
        val result = handler.handle(command)

        // Assert
        result.shouldNotBeNull()
        capturedHabit.shouldNotBeNull()
        capturedHabit?.id.shouldBeEqualTo(result.getOrNull())

        verify { habitRepositoryMock.save(any()) }
    }

    @Test
    fun `Given a create habit command, when handle is invoked and an exception is thrown, return an error result`() {
        // Arrange
        val habitRepositoryMock = mockk<HabitRepository>(relaxed = true)
        val command = CreateHabitCommand(
            "Test Habit",
            listOf(1, 2, 3),
            "Test Owner",
            "Test Icon",
            "red"
        )

        val exceptionToThrow = Exception()
        every { habitRepositoryMock.save(any()) } throws exceptionToThrow

        val handler = CreateHabitCommandHandler(habitRepositoryMock)

        // Act
        val result = handler.handle(command)

        // Assert
        result.shouldNotBeNull()
        result.exceptionOrNull().shouldBeEqualTo(exceptionToThrow)
    }
}
