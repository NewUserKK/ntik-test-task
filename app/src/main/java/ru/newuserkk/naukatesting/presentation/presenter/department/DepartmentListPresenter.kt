package ru.newuserkk.naukatesting.presentation.presenter.department

import kotlinx.coroutines.Dispatchers
import ru.newuserkk.naukatesting.R
import ru.newuserkk.naukatesting.data.repository.department.DepartmentRoomRepository
import ru.newuserkk.naukatesting.domain.common.Result
import ru.newuserkk.naukatesting.domain.department.DepartmentInteractor
import ru.newuserkk.naukatesting.domain.department.DepartmentInteractorImpl
import ru.newuserkk.naukatesting.domain.department.DepartmentRepository
import ru.newuserkk.naukatesting.domain.department.model.Department
import ru.newuserkk.naukatesting.presentation.presenter.common.AbstractListPresenter
import ru.newuserkk.naukatesting.presentation.view.department.DepartmentListActivity
import kotlin.coroutines.CoroutineContext

class DepartmentListPresenter(view: DepartmentListActivity) : AbstractListPresenter<Department>(view) {

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main

    private val interactor: DepartmentInteractor = DepartmentInteractorImpl()

    override suspend fun getItems(): Result<List<Department>> {
        return interactor.getDepartments()
    }

    override suspend fun removeItemImpl(item: Department): Result<Department> {
        return interactor.removeDepartment(item)
    }
}