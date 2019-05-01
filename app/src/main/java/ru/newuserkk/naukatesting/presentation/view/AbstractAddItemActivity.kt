package ru.newuserkk.naukatesting.presentation.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import ru.newuserkk.naukatesting.presentation.presenter.AbstractAddItemPresenter
import java.io.Serializable

abstract class AbstractAddItemActivity<T: Serializable>: AppCompatActivity() {

    protected abstract val presenter: AbstractAddItemPresenter<T>
    protected abstract val activityResId: Int
    protected abstract val progressBarResId: Int
    protected abstract val contentResId: Int
    protected abstract val submitButtonResId: Int
    protected abstract val errorStringResId: Int

    private var result: T? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activityResId)
        findViewById<View>(submitButtonResId).setOnClickListener {
            presenter.addDepartment()
        }
    }

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
//        findViewById<View>(contentResId).visibility = View.VISIBLE
    }

    fun showSuccessMessage() {
        Toast.makeText(this, "Successfully added department!", Toast.LENGTH_LONG).also {
            val toastLayout = it.view as ViewGroup
            val toastTextView = toastLayout.getChildAt(0) as TextView
            toastTextView.textSize = 12f
        }.show()
    }

    fun showError(e: Throwable) {
        AlertDialog.Builder(this)
            .setMessage(getString(errorStringResId) + e.localizedMessage)
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
                setResult(AbstractListActivity.ITEM_RESULT_OK, returnIntent)
            } else {
                setResult(AbstractListActivity.ITEM_RESULT_NULL, returnIntent)
            }
        }

        super.finish()
    }

    abstract class ItemOptions
}