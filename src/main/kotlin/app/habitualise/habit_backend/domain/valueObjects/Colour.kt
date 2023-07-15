package app.habitualise.habit_backend.domain.valueObjects

import app.habitualise.habit_backend.domain.exceptions.InvalidColourValueException

enum class Colour(val value: String) {
    RED("red"),
    GREEN("green"),
    BLUE("blue"),
    YELLOW("yellow"),
    PURPLE("purple"),
    ORANGE("orange"),
    GREY("grey");

    companion object {
        fun fromValue(value: String): Colour {
            for (colour in values()) {
                if (colour.value == value) {
                    return colour
                }
            }
            throw InvalidColourValueException("Colour with value: $value does not exist")
        }
    }
}
