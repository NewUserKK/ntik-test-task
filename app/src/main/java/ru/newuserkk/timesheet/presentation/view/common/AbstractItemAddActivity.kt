package ru.newuserkk.timesheet.presentation.view.common

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import ru.newuserkk.timesheet.R
import ru.newuserkk.timesheet.presentation.presenter.common.AbstractItemAddPresenter
import java.io.Serializable

abstract class AbstractItemAddActivity<T : Serializable> : AppCompatActivity() {

    companion object {
        const val LOG_TAG = "AbstractItemAddActivity"
    }

    protected abstract val presenter: AbstractItemAddPresenter<T>
    protected abstract val layoutResId: Int
    protected abstract val progressBarResId: Int
    protected abstract val contentResId: Int
    protected abstract val submitButtonResId: Int

    var itemToEdit: T? = null
        private set

    val isEditing: Boolean
        get() = itemToEdit != null

    private var result: T? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutResId)

        itemToEdit = intent?.extras?.getSerializable(AbstractListActivity.TAG_ITEM_KEY) as? T
        if (itemToEdit != null) {
            title = getString(R.string.title_activity_add_item)
            fillFields(itemToEdit!!)
            findViewById<View>(submitButtonResId).setOnClickListener {
                Log.d(LOG_TAG, "item to edit: $itemToEdit")
                presenter.addItem(getItemOptions(), edit = true)
            }

        } else {
            findViewById<View>(submitButtonResId).setOnClickListener {
                presenter.addItem(getItemOptions())
            }
        }

    }

    abstract fun fillFields(item: T)

    abstract fun getItemOptions(): ItemOptions

    fun setResult(result: T) {
        this.result = result
    }

    fun showProgress() {
        findViewById<View>(progressBarResId).visibility = View.VISIBLE
        findViewById<View>(contentResId).visibility = View.GONE
    }

    fun hideProgress() {
        findViewById<View>(progressBarResId).visibility = View.GONE
        findViewById<View>(contentResId).visibility = View.VISIBLE
    }

    fun showSuccessMessage() {
        Toast.makeText(this, getSuccessMessage(), Toast.LENGTH_SHORT)
            .small()
            .show()
    }

    abstract fun getSuccessMessage(): String

    fun showAddError(e: Throwable) {
        showError(getAddErrorMessage(), e)
    }

    abstract fun getAddErrorMessage(): String

    fun showError(message: String, e: Throwable) {
        Log.e(LOG_TAG, e.message)
        AlertDialog.Builder(this)
            .setMessage(message + "\nDetails: ${e.message}")
            .setPositiveButton(getString(R.string.ok)) { _, _ -> }
            .show()
    }

    override fun finish() {
        result.let { result ->
            val returnIntent = Intent().apply {
                putExtra(
                    AbstractListActivity.ITEM_RESULT_KEY,
                    result
                )
            }
            if (result != null) {
                val code = if (isEditing) {
                    AbstractListActivity.ITEM_RESULT_EDIT
                } else {
                    AbstractListActivity.ITEM_RESULT_ADD
                }

                setResult(code, returnIntent)
            } else {
                setResult(AbstractListActivity.ITEM_RESULT_NULL, returnIntent)
            }
        }

        super.finish()
    }

    interface ItemOptions
}