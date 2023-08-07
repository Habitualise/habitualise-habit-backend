package app.habitualise.habit_backend.infrastructure.services.read_projection_engine

import app.habitualise.habit_backend.infrastructure.persistence.models.HabitDTO
import com.mongodb.client.model.changestream.ChangeStreamDocument
import org.bson.Document
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.aggregation.Aggregation
import org.springframework.data.mongodb.core.messaging.ChangeStreamRequest
import org.springframework.data.mongodb.core.messaging.DefaultMessageListenerContainer
import org.springframework.data.mongodb.core.messaging.MessageListener
import org.springframework.data.mongodb.core.query.Criteria.where
import org.springframework.stereotype.Service
import java.time.LocalDate
import kotlin.math.round

@Service
class ReadProjectionEngine(private val template: MongoTemplate) {

    private lateinit var container: DefaultMessageListenerContainer

    fun start() {
        container = DefaultMessageListenerContainer(template)
        container.start()

        val listener = createListener()

        val request = ChangeStreamRequest.builder()
            .collection("habits")
            .filter(
                Aggregation.newAggregation(
                    Aggregation.match(
                        where("operationType").`in`("insert", "update", "replace"),
                    )
                ),
            )
            .publishTo(listener)
            .build()

        container.register(request, HabitDTO::class.java)
    }

    fun stop() {
        container.stop()
    }

    private fun createListener(): MessageListener<ChangeStreamDocument<Document>, HabitDTO> {
        return MessageListener { message ->
            val habitReadProjection = message.body?.let { body ->
                HabitReadProjection(
                    id = body.id,
                    name = body.name,
                    iconName = body.style.icon,
                    colour = body.style.colour.value,
                    isCompletedToday = body.daysAchieved.lastOrNull() == LocalDate.now(),
                    completionPercentage = calculateCompletionPercentage(
                        body.creationDate,
                        body.daysDue,
                        body.daysAchieved
                    ),
                    active = body.active,
                    daysDue = body.daysDue,
                    completionHistory = calculateLast14AttemptsHistory(
                        body.creationDate,
                        body.daysDue,
                        body.daysAchieved
                    )
                )
            }
            if (habitReadProjection != null) {
                try {
                    template.save(habitReadProjection)
                } catch (e: Exception) {
                    println(e)
                }
            }
        }
    }

    private fun calculateCompletionPercentage(
        creationDate: LocalDate,
        daysDue: List<Int>,
        daysAchieved: List<LocalDate>
    ): Int {
        val daysOfWeekDue = daysDue.toSet()
        val daysAttempted = LocalDate.now().getDaysOfWeekBetweenFromHistoricalDate(creationDate, daysOfWeekDue).size
        return if (daysAttempted == 0) 0 else round(daysAchieved.size.toDouble() / daysAttempted).toInt()
    }

    private fun calculateLast14AttemptsHistory(
        creationDate: LocalDate,
        daysDue: List<Int>,
        daysAchieved: List<LocalDate>
    ): List<Boolean> {
        val daysOfWeekDue = daysDue.toSet()
        return LocalDate.now().getDaysOfWeekBetweenFromHistoricalDate(creationDate, daysOfWeekDue, 14)
            .map { it in daysAchieved }
    }

    /**
     * This function returns a list of dates which are of the days of the week given between the given date and target date which is **before** the given date.
     *
     * @param targetDate The end point of the date range, should be earlier than the invoking date (this)
     * @param daysOfWeek A set of days of the week (1-7) of which the returned dates should be
     * @param amount The amount of dates to return. If null, all dates between the start and end date will be returned
     */
    private fun LocalDate.getDaysOfWeekBetweenFromHistoricalDate(
        targetDate: LocalDate,
        daysOfWeek: Set<Int>,
        amount: Int? = null
    ): List<LocalDate> {
        val daysOfWeekBetweenDates = mutableListOf<LocalDate>()
        var currentDate = this
        val maxDaysReturned = amount ?: (targetDate.until(this).days + 1)
        while (currentDate.isAfter(targetDate) && daysOfWeekBetweenDates.size < maxDaysReturned) {
            if (daysOfWeek.contains(currentDate.dayOfWeek.value)) {
                daysOfWeekBetweenDates.add(currentDate)
            }
            currentDate = currentDate.minusDays(1)
        }
        return daysOfWeekBetweenDates.reversed()
    }
}
