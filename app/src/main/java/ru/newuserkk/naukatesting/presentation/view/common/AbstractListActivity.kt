package ru.newuserkk.naukatesting.presentation.view.common

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import ru.newuserkk.naukatesting.R
import ru.newuserkk.naukatesting.presentation.presenter.common.AbstractListPresenter
import java.io.Serializable

abstract class AbstractListActivity<T: Serializable>: AppCompatActivity() {

    protected lateinit var presenter: AbstractListPresenter<T>
    protected abstract val activityResId: Int
    protected abstract val toolbarResId: Int
    protected abstract val addButtonResId: Int
    protected abstract val listResId: Int
    protected abstract val itemAddActivityTypeToken: Class<out AbstractItemAddActivity<T>>

    protected abstract val adapter: AbstractItemRecyclerViewAdapter<T>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activityResId)
        presenter = initPresenter()

        setSupportActionBar(findViewById(toolbarResId))
        setupRecyclerView()

        findViewById<View>(addButtonResId).setOnClickListener {
            startAddItemActivity(getAddActivityBundle())
        }
    }

    abstract fun initPresenter(): AbstractListPresenter<T>

    open fun getAddActivityBundle(): Bundle? {
        return null
    }

    fun adapterNotifyDataSetChanged() {
        adapter.notifyDataSetChanged()
    }

    fun showListFillError() {
        AlertDialog.Builder(this)
            .setMessage(getFillErrorMessage())
            .setPositiveButton(getString(R.string.ok)) { _, _ -> finish() }
            .show()
    }
    abstract fun getFillErrorMessage(): String

    private fun startAddItemActivity(bundle: Bundle?) {
        val intent = Intent(this, itemAddActivityTypeToken).apply {
            putExtra(ADD_BUNDLE_KEY, bundle)
        }
        startActivityForResult(
            intent,
            ITEM_REQUEST_CODE
        )
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == ITEM_REQUEST_CODE && resultCode == ITEM_RESULT_OK) {
            val item = data?.extras?.get(ITEM_RESULT_KEY) as T
            adapter.apply {
                values.add(item)
                notifyItemInserted(itemCount)
            }
        }
    }

    private fun setupRecyclerView() {
        findViewById<RecyclerView>(listResId).adapter = adapter
        presenter.fillList(adapter.values)
    }

    companion object {
        const val ITEM_REQUEST_CODE = 0
        const val ITEM_RESULT_OK = 0
        const val ITEM_RESULT_NULL = 1
        const val ITEM_RESULT_KEY = "item_result"
        const val ADD_BUNDLE_KEY = "add_bundle"
    }
}