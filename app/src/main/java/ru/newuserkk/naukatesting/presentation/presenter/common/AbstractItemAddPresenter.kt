package ru.newuserkk.naukatesting.presentation.presenter.common

import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.newuserkk.naukatesting.domain.common.Result
import ru.newuserkk.naukatesting.presentation.view.common.AbstractItemAddActivity
import java.io.Serializable

abstract class AbstractItemAddPresenter<T : Serializable>(protected open val view: AbstractItemAddActivity<T>) :
    CoroutineScope {

    fun addItem(options: AbstractItemAddActivity.ItemOptions, edit: Boolean = false) {
        launch {
            view.showProgress()
            val itemResult = createItemFromOptions(options)
            if (!itemResult.isSuccessful || itemResult.value == null) {
                view.showAddError(itemResult.error!!)
                view.hideProgress()
                return@launch
            }

            val item = itemResult.value

            Log.d(LOG_TAG, "item: $item")

            val result = withContext(Dispatchers.IO) {
                if (edit) {
                    updateItem(getItemId(view.itemToEdit!!), item)
                } else {
                    addItem(item)
                }
            }
            view.hideProgress()

            if (result.isSuccessful && result.value != null) {
                view.showSuccessMessage()
                view.setResult(result.value)
                view.finish()

            } else {
                view.showAddError(result.error!!)
            }
        }
    }

    protected abstract fun createItemFromOptions(options: AbstractItemAddActivity.ItemOptions): Result<T>
    protected abstract fun getItemId(editingItem: T): Long

    protected abstract suspend fun addItem(item: T): Result<T>

    protected abstract suspend fun updateItem(id: Long, item: T): Result<T>

    companion object {
        const val LOG_TAG = "A_ItemAddPresenter"
    }
}