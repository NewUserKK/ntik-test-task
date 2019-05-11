package ru.newuserkk.naukatesting.presentation.presenter.common

import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.newuserkk.naukatesting.domain.common.Result
import ru.newuserkk.naukatesting.presentation.view.common.AbstractListActivity
import java.io.Serializable

abstract class AbstractListPresenter<T : Serializable>(protected val view: AbstractListActivity<T>) : CoroutineScope {

    fun fillList(values: MutableList<T>) {
        launch {
            val result = withContext(Dispatchers.IO) {
                getItems()
            }

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
            val result = withContext(Dispatchers.IO) {
                removeItemImpl(item)
            }

            if (result.isSuccessful) {
                view.removeItemFromAdapter(item)
            } else {
                view.showRemoveError()
            }
        }
    }

    abstract suspend fun removeItemImpl(item: T): Result<T>

    abstract suspend fun getItems(): Result<List<T>>

    companion object {
        const val LOG_TAG = "AbstractListPresenter"
    }
}