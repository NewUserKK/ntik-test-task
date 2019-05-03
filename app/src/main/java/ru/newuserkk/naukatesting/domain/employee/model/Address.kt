package ru.newuserkk.naukatesting.domain.employee.model

import java.io.Serializable

data class Address(
    val country: String,
    val city: String,
    val street: String,
    val houseNumber: String,
    val flatNumber: String?
): Serializable