package app.habitualise.habit_backend

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class HabitBackendApplication

fun main(args: Array<String>) {
    runApplication<HabitBackendApplication>(*args)
}