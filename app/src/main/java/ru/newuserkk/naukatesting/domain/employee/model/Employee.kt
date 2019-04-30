package ru.newuserkk.naukatesting.domain.employee.model

import ru.newuserkk.naukatesting.domain.department.model.Department
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