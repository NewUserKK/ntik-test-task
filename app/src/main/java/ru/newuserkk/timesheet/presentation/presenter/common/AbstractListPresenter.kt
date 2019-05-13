package ru.newuserkk.timesheet.presentation.presenter.common

import kotlinx.coroutines.*
import ru.newuserkk.timesheet.domain.common.Result
import ru.newuserkk.timesheet.presentation.view.common.AbstractListActivity
import java.io.Serializable


abstract class AbstractListPresenter<T : Serializable>(
    protected val view: AbstractListActivity<T>
) : CoroutineScope {

    private val job = Job()
    override val coroutineContext = Dispatchers.Main + job

    fun fillList(values: MutableList<T>) {
        launch {
            view.showProgress()
            val result = withContext(Dispatchers.IO + job) {
                getItems()
            }
            view.hideProgress()

            if (result.isSuccessful && result.value != null) {
                values.clear()
                values.addAll(result.value)
                view.notifyDataSetChanged()

            } else {
                view.showListFillError()
            }
        }
    }

    fun removeItem(item: T) {
        launch {
            val result = withContext(Dispatchers.IO + job) {
                removeItemImpl(item)
            }

            if (result.isSuccessful) {
                view.removeItemFromAdapter(item)
                view.showRemoveSuccessMessage()
            } else {
                view.showRemoveError()
            }
        }
    }

    abstract suspend fun removeItemImpl(item: T): Result<T>

    abstract suspend fun getItems(): Result<List<T>>

    fun cancelJobs() {
        job.cancel()
    }

    companion object {
        const val LOG_TAG = "AbstractListPresenter"
    }
}