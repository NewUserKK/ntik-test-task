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
    protected abstract val activityLayoutResId: Int
    protected abstract val toolbarResId: Int
    protected abstract val addButtonResId: Int
    protected abstract val listResId: Int
    protected abstract val itemAddActivityTypeToken: Class<out AbstractItemAddActivity<T>>
//    protected abstract val itemEditActivityTypeToken: Class<out AbstractItemEditActivity<T>>

    protected abstract val adapter: AbstractItemRecyclerViewAdapter<T>

    private var editItemPosition = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activityLayoutResId)
        presenter = initPresenter()

        setSupportActionBar(findViewById(toolbarResId))
        setupRecyclerView()

        findViewById<View>(addButtonResId).setOnClickListener {
            startAddItemActivity(getAddActivityBundle())
        }

        adapter.onItemClickListener = {
            editItemPosition = adapter.values.indexOf(it.tag)
            startEditItemActivity(it.tag as T)
        }
    }

    abstract fun initPresenter(): AbstractListPresenter<T>

    open fun getAddActivityBundle(): Bundle? = null

    private fun setupRecyclerView() {
        findViewById<RecyclerView>(listResId).adapter = adapter
        presenter.fillList(adapter.values)
    }

    private fun startAddItemActivity(bundle: Bundle? = null) {
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
        if (requestCode == ITEM_REQUEST_CODE) {
            when (resultCode) {
                ITEM_RESULT_ADD -> {
                    val item = data?.extras?.get(ITEM_RESULT_KEY) as T
                    adapter.apply {
                        values.add(item)
                        notifyItemInserted(itemCount)
                    }
                }

                ITEM_RESULT_EDIT -> {
                    val item = data?.extras?.get(ITEM_RESULT_KEY) as T
                    adapter.values[editItemPosition] = item
                    adapter.notifyItemChanged(editItemPosition)
                }
            }

        }
    }

    fun startEditItemActivity(item: T) {
        val intent = Intent(this, itemAddActivityTypeToken).apply {
            putExtra(EDIT_ITEM_KEY, item)
        }
        startActivityForResult(intent, ITEM_REQUEST_CODE)
    }

    fun showListFillError() {
        AlertDialog.Builder(this)
            .setMessage(getFillErrorMessage())
            .setPositiveButton(getString(R.string.ok)) { _, _ -> finish() }
            .show()
    }
    abstract fun getFillErrorMessage(): String

    fun adapterNotifyDataSetChanged() {
        adapter.notifyDataSetChanged()
    }

    companion object {
        const val ITEM_REQUEST_CODE = 0
        const val ITEM_RESULT_ADD = 0
        const val ITEM_RESULT_EDIT = 1
        const val ITEM_RESULT_NULL = 2
        const val ITEM_RESULT_KEY = "item_result"
        const val ADD_BUNDLE_KEY = "add_bundle"
        const val EDIT_ITEM_KEY = "item_to_edit"
    }
}