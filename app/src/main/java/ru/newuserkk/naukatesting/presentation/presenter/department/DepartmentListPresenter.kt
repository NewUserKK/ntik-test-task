package ru.newuserkk.naukatesting.presentation.presenter.department

import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.newuserkk.naukatesting.domain.common.Result
import ru.newuserkk.naukatesting.domain.department.DepartmentInteractor
import ru.newuserkk.naukatesting.domain.department.DepartmentInteractorImpl
import ru.newuserkk.naukatesting.domain.department.model.Department
import ru.newuserkk.naukatesting.presentation.presenter.AbstractListPresenter
import ru.newuserkk.naukatesting.presentation.view.department.DepartmentListActivity
import kotlin.coroutines.CoroutineContext

class DepartmentListPresenter(view: DepartmentListActivity) : AbstractListPresenter<Department>(view) {

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main

    private val interactor: DepartmentInteractor = DepartmentInteractorImpl()

    override suspend fun getItems(): Result<List<Department>> = interactor.getDepartments()
}