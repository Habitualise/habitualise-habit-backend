package app.habitualise.habit_backend.infrastructure.services.read_projection_engine

import jakarta.annotation.PostConstruct
import jakarta.annotation.PreDestroy
import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.core.messaging.DefaultMessageListenerContainer

@Configuration
class ReadProjectionEngineConfig(private val readProjectionEngine: ReadProjectionEngine) {

    @PostConstruct
    fun init() {
        readProjectionEngine.start()
    }

    @PreDestroy
    fun cleanup() {
        readProjectionEngine.stop()
    }
}
