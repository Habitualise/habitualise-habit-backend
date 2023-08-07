package app.habitualise.habit_backend.web.controllers

import an.awesome.pipelinr.Pipeline
import app.habitualise.habit_backend.application.commands.create_habit_command.CreateHabitCommand
import app.habitualise.habit_backend.domain.exceptions.DomainException
import app.habitualise.habit_backend.web.requests.CreateHabitRequest
import jakarta.validation.Valid
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/habits")
class HabitController(private val pipeline: Pipeline) {
    private val logger = LoggerFactory.getLogger(HabitController::class.java)

    @PostMapping
    fun createHabit(@Valid @RequestBody createHabitRequest: CreateHabitRequest): ResponseEntity<String> {
        val createHabitResult =
            CreateHabitCommand(
                createHabitRequest.name,
                createHabitRequest.daysDue,
                createHabitRequest.owner,
                createHabitRequest.iconName,
                createHabitRequest.colour
            )
                .execute(pipeline)
        if (createHabitResult.isSuccess) {
            return ResponseEntity(createHabitResult.getOrNull().toString(), HttpStatus.CREATED)
        }
        return if (createHabitResult.exceptionOrNull() is DomainException) {
            val exceptionMessage = (createHabitResult.exceptionOrNull() as DomainException).message
            ResponseEntity(exceptionMessage, HttpStatus.BAD_REQUEST)
        } else {
            logger.error(
                "An exception occurred when creating a habit",
                createHabitResult.exceptionOrNull()
            ) // Not sure if this is working correctly yet, need to look into it in future ticket
            ResponseEntity("An error occurred when creating a habit", HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }
}
