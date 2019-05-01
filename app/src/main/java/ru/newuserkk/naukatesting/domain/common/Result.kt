package ru.newuserkk.naukatesting.domain.common

class Result<T>(val value: T?, val error: Throwable?) {
    val isSuccessful: Boolean
        get() = error == null
}