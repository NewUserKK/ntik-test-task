package ru.newuserkk.naukatesting.presentation.presenter

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import ru.newuserkk.naukatesting.domain.common.Result
import ru.newuserkk.naukatesting.presentation.view.AbstractListActivity

abstract class AbstractListPresenter<T>(private val view: AbstractListActivity<T>) : CoroutineScope {

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

    abstract fun getItems(): Result<List<T>>
}