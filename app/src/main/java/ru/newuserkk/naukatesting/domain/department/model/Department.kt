package ru.newuserkk.naukatesting.domain.department.model

import java.io.Serializable

data class Department(val name: String): Serializable {
    override fun toString(): String {
        return name
    }
}