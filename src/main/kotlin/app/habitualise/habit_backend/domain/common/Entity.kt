package app.habitualise.habit_backend.domain.common

abstract class Entity<TId>(val id: TId) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Entity<*>) return false

        return id == other.id
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }
}
