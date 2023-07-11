package app.habitualise.habit_backend.domain.common

import java.util.*

abstract class AggregateRoot<ID> : Entity<ID>() {
    private val _domainEvents = LinkedList<DomainEvent>()
    val domainEvents: List<DomainEvent>
        get() = _domainEvents

    protected fun addDomainEvent(event: DomainEvent) {
        _domainEvents += event
    }

    fun clearDomainEvents() {
        _domainEvents.clear()
    }
}
