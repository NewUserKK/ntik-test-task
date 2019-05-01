package ru.newuserkk.naukatesting.domain.employee.model

data class Address(
    val country: String,
    val city: String,
    val street: String,
    val houseNumber: String,
    val flatNumber: String?
)