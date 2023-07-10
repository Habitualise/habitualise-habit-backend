package app.habitualise.habit_backend.application.configuration

import an.awesome.pipelinr.*
import org.springframework.beans.factory.ObjectProvider
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class PipelinrConfiguration {
    @Bean
    fun pipeline(
        commandHandlers: ObjectProvider<Command.Handler<*, *>?>,
        notificationHandlers: ObjectProvider<Notification.Handler<*>?>,
        middlewares: ObjectProvider<Command.Middleware>
    ): Pipeline {
        return Pipelinr()
            .with(CommandHandlers { commandHandlers.stream() })
            .with(NotificationHandlers { notificationHandlers.stream() }) // Note there are no Notifications/ Handlers yet
            .with(Command.Middlewares { middlewares.orderedStream() }) // Note there are no Middlewares yet
    }
}
