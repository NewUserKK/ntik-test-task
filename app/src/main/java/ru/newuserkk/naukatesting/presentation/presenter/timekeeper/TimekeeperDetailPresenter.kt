package ru.newuserkk.naukatesting.presentation.presenter.timekeeper

import ru.newuserkk.naukatesting.domain.model.Address
import ru.newuserkk.naukatesting.domain.model.Department
import ru.newuserkk.naukatesting.domain.model.Employee
import java.sql.Date
import kotlin.random.Random

class TimekeeperDetailPresenter {

    companion object {
        const val LOG_TAG = "TimekeeperDetailPresenter"
    }

    fun getEmployeesData(): List<Employee> {
        return listOf(
            getRandomEmployee(),
            getRandomEmployee(),
            getRandomEmployee(),
            getRandomEmployee(),
            getRandomEmployee(),
            getRandomEmployee(),
            getRandomEmployee(),
            getRandomEmployee(),
            getRandomEmployee(),
            getRandomEmployee(),
            getRandomEmployee(),
            getRandomEmployee()
        )
    }

    private fun getRandomEmployee(): Employee {
        return Employee(
            firstName = getRandomString(15),
            lastName = getRandomString(15),
            middleName = getRandomString(15),
            birthDate = Date.valueOf("2019-04-28"),
            department = Department("Department"),
            position = "Android",
            address = Address("Russia", "SPB", "1", "@", "3")
        )
    }

    fun getRandomString(length: Int) : String {
        val allowedChars = "ABCDEFGHIJKLMNOPQRSTUVWXTZabcdefghiklmnopqrstuvwxyz"
        return (1..length)
            .map { allowedChars.random() }
            .joinToString("")
    }
}