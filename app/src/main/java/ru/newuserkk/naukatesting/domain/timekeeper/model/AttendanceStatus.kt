package ru.newuserkk.naukatesting.domain.timekeeper.model

enum class AttendanceStatus(val id: Int, val value: String) {
    PRESENCE_FULL_DAY(0, "Я"),
    PRESENCE_AT_DAY_OFF(1, "Рв"),
    PRESENCE_BUSINESS_TRIP(2, "К"),
    PRESENCE_CLEAN_DAY(3, "Хд"),

    ABSENCE_UNKNOWN(4, "Н"),
    ABSENCE_DAY_OFF(5, "В"),
    ABSENCE_TEMPORARY_DISABILITY(6, "Б"),
    ABSENCE_PAID_VACATION(7, "ОТ"),
    ABSENCE_NOT_PAID_VACATION(8, "До"),
    ABSENCE_EDUCATIONAL_VACATION(9, "У"),
    ABSENCE_BABY_VACATION(10, "Ож");

    override fun toString(): String {
        return value
    }
}