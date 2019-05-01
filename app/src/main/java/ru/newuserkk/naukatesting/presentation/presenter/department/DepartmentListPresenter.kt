package ru.newuserkk.naukatesting.presentation.presenter.department

import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.newuserkk.naukatesting.domain.department.DepartmentInteractor
import ru.newuserkk.naukatesting.domain.department.DepartmentInteractorImpl
import ru.newuserkk.naukatesting.domain.department.model.Department
import ru.newuserkk.naukatesting.presentation.view.department.DepartmentListActivity
import kotlin.coroutines.CoroutineContext

class DepartmentListPresenter(private val view: DepartmentListActivity) : CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main

    private val interactor: DepartmentInteractor = DepartmentInteractorImpl()

    fun addDepartments(values: MutableList<Department>) {
        launch {
            val result = interactor.getDepartments()
            if (result.isSuccessful && result.value != null) {
                values.clear()
                values.addAll(result.value)
            } else {
                Log.e(LOG_TAG, result.error?.message ?: "null")
                view.showFillError()
            }
        }
    }

    companion object {
        const val LOG_TAG = "DepartmentListPresenter"
    }
}