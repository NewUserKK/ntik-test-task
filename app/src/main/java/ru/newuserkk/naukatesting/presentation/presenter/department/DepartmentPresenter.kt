package ru.newuserkk.naukatesting.presentation.presenter.department

import ru.newuserkk.naukatesting.data.repository.department.DepartmentRepositoryImpl
import ru.newuserkk.naukatesting.data.repository.department.DepartmentRepositoryTest
import ru.newuserkk.naukatesting.domain.department.DepartmentRepository
import ru.newuserkk.naukatesting.domain.department.model.Department

class DepartmentPresenter {

    private val repository: DepartmentRepository = DepartmentRepositoryTest()

    suspend fun getDepartments(): List<Department> = repository.getDepartments()
}