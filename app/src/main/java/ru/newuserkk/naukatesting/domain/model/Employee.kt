package ru.newuserkk.naukatesting.domain.model

import java.util.*

data class Employee(
    val firstName: String,
    val lastName: String,
    val middleName: String?,
    val birthDate: Date,
    val department: Department,
    val position: String,
    val address: Address
)

fun Employee.getFullName(): String {
    return "$lastName $firstName" + if (middleName != null) " $middleName" else ""
}