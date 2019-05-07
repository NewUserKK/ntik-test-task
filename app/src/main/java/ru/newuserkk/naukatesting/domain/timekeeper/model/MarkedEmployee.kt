package ru.newuserkk.naukatesting.domain.timekeeper.model

import ru.newuserkk.naukatesting.domain.employee.model.Employee
import java.io.Serializable
import java.util.*

data class MarkedEmployee(val date: Date, val employee: Employee, val status: AttendanceStatus): Serializable