package ru.newuserkk.naukatesting.presentation.presenter.common

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.newuserkk.naukatesting.domain.common.Result
import ru.newuserkk.naukatesting.presentation.view.common.AbstractItemAddActivity
import java.io.Serializable

abstract class AbstractItemAddPresenter<T: Serializable>(protected val view: AbstractItemAddActivity<T>) : CoroutineScope {

    fun addItem() {
        launch {
            view.showProgress()
            val result = withContext(Dispatchers.IO) {
                addItem(createItemFromOptions(view.getItemOptions()))
            }
            view.hideProgress()
            if (result.isSuccessful && result.value != null) {
                view.showSuccessMessage()
                view.setResult(result.value)
                view.finish()

            } else {
                view.showAddingError(result.error!!)
            }
        }
    }

    protected abstract fun createItemFromOptions(options: AbstractItemAddActivity.ItemOptions): T
    protected abstract suspend fun addItem(item: T): Result<T>
}