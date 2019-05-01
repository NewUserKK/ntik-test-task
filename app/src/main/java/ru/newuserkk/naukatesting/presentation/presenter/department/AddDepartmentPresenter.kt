package ru.newuserkk.naukatesting.presentation.presenter.department

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.newuserkk.naukatesting.data.repository.department.DepartmentRepositoryTest
import ru.newuserkk.naukatesting.domain.department.DepartmentRepository
import ru.newuserkk.naukatesting.domain.department.model.Department
import ru.newuserkk.naukatesting.presentation.view.department.AddDepartmentActivity
import kotlin.coroutines.CoroutineContext

class AddDepartmentPresenter(private val view: AddDepartmentActivity) : CoroutineScope {
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main

    private val departmentRepository: DepartmentRepository = DepartmentRepositoryTest()

    fun addDepartment() {
        launch {
            view.showProgress()
            withContext(Dispatchers.IO) {
                departmentRepository.addDepartment(Department(view.getDepartmentName()))
            }
            view.hideProgress()
            view.showSuccessMessage()
            view.goBack()
        }
    }

}