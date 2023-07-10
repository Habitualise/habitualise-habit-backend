package app.habitualise.habit_backend.presentation.controllers

import an.awesome.pipelinr.Pipeline
import app.habitualise.habit_backend.application.commands.create_habit_command.CreateHabitCommand
import app.habitualise.habit_backend.presentation.requests.CreateHabitRequest
import jakarta.validation.Valid
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("/api/v1/habits")
class HabitController(private val pipeline: Pipeline) {
    private val logger = LoggerFactory.getLogger(HabitController::class.java)

    @PostMapping
    fun createHabit(@Valid @RequestBody createHabitRequest: CreateHabitRequest): ResponseEntity<UUID> {
        val createHabitResult =
            CreateHabitCommand(createHabitRequest.name, createHabitRequest.daysDue, createHabitRequest.owner)
                .execute(pipeline)
        return if (createHabitResult.isSuccess) {
            ResponseEntity(createHabitResult.getOrNull(), HttpStatus.CREATED)
        } else {
            logger.error(
                "An exception occurred when creating a habit",
                createHabitResult.exceptionOrNull()
            ) // Not sure if this is working correctly yet, need to look into it in future ticket
            return ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }
}
