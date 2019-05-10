package ru.newuserkk.naukatesting.domain.department.model

import java.io.Serializable

data class Department(
    val name: String,
    var id: Int = -1

) : Serializable {

    override fun toString(): String {
        return name
    }

}