package ru.newuserkk.naukatesting.presentation.presenter.department

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.newuserkk.naukatesting.domain.department.DepartmentInteractor
import ru.newuserkk.naukatesting.domain.department.DepartmentInteractorImpl
import ru.newuserkk.naukatesting.domain.department.model.Department
import ru.newuserkk.naukatesting.presentation.view.department.AddDepartmentActivity
import kotlin.coroutines.CoroutineContext

class AddDepartmentPresenter(private val view: AddDepartmentActivity) : CoroutineScope {
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main

    private val departmentInteractor: DepartmentInteractor = DepartmentInteractorImpl()

    fun addDepartment() {
        launch {
            view.showProgress()
            val result = departmentInteractor.addDepartment(
                Department(view.getDepartmentName())
            )
            view.hideProgress()

            if (result.isSuccessful && result.value != null) {
                view.showSuccessMessage()
                view.setResult(result.value)
                view.finish()

            } else {
                view.showError(result.error!!)
            }
        }
    }
}