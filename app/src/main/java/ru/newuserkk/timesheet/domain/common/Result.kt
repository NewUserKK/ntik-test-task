package ru.newuserkk.timesheet.domain.common

class Result<T>(val value: T?, val error: Throwable? = null) {
    val isSuccessful: Boolean
        get() = error == null
}