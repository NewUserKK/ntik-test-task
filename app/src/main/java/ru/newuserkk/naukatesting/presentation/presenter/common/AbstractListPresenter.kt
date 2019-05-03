package ru.newuserkk.naukatesting.presentation.presenter.common

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
                view.adapterNotifyDataSetChanged()

            } else {
                view.showListFillError(getFillListErrorMessage(result))
            }
        }
    }


    abstract suspend fun getItems(): Result<List<T>>
    abstract fun getFillListErrorMessage(result: Result<List<T>>): String
}