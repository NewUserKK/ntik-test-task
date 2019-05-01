package ru.newuserkk.naukatesting.presentation.presenter

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import ru.newuserkk.naukatesting.domain.common.Result
import ru.newuserkk.naukatesting.presentation.view.AbstractAddItemActivity
import java.io.Serializable

abstract class AbstractAddItemPresenter<T: Serializable>(private val view: AbstractAddItemActivity<T>) : CoroutineScope {

    fun addDepartment() {
        launch {
            view.showProgress()
            val result = addItem(createItemFromOptions(view.getItemOptions()))
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

    abstract fun createItemFromOptions(options: AbstractAddItemActivity.ItemOptions): T
    abstract suspend fun addItem(item: T): Result<T>
}