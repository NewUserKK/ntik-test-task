package ru.newuserkk.naukatesting.presentation.presenter

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import ru.newuserkk.naukatesting.domain.common.Result
import ru.newuserkk.naukatesting.presentation.view.AbstractListActivity
import java.io.Serializable

abstract class AbstractListPresenter<T: Serializable>(private val view: AbstractListActivity<T>) : CoroutineScope {

    fun fillList(values: MutableList<T>) {
        launch {
            val result = getItems()
            if (result.isSuccessful && result.value != null) {
                values.clear()
                values.addAll(result.value)
                view.adapterNotifyDataSetChanged()

            } else {
                view.showListFillError()
            }
        }
    }

    abstract suspend fun getItems(): Result<List<T>>
}