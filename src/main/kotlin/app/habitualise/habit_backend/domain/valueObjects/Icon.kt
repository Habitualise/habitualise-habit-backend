package app.habitualise.habit_backend.domain.valueObjects

import app.habitualise.habit_backend.domain.exceptions.InvalidIconNameException

data class Icon(val name: String, val validate: (String) -> Boolean) {
    init {
        if (!validate(name)) {
            throw InvalidIconNameException("Icon with name: $name does not exist")
        }
    }
}
