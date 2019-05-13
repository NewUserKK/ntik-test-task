package ru.newuserkk.timesheet.presentation.view.common

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import ru.newuserkk.timesheet.R
import ru.newuserkk.timesheet.presentation.presenter.common.AbstractListPresenter
import java.io.Serializable

abstract class AbstractListActivity<T : Serializable> : AppCompatActivity() {

    protected lateinit var presenter: AbstractListPresenter<T>
    protected abstract val layoutResId: Int
    protected abstract val toolbarResId: Int
    protected abstract val addButtonResId: Int
    protected abstract val listResId: Int
    protected abstract val itemDetailActivityTypeToken: Class<out AbstractItemDetailActivity<T>>
    protected abstract val itemAddActivityTypeToken: Class<out AbstractItemAddActivity<T>>

    protected abstract val adapter: AbstractItemRecyclerViewAdapter<T>

    private var editItemPosition = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutResId)
        presenter = initPresenter()

        setSupportActionBar(findViewById(toolbarResId))
        setupRecyclerView()

        findViewById<View>(addButtonResId).setOnClickListener {
            startAddItemActivity(getAddItemActivityBundle())
        }

        adapter.apply {
            onItemClickListener = {
                startShowItemActivity(it.tag as T)
            }

            onEditClickListener = {
                editItemPosition = adapter.values.indexOf(it.tag)
                startEditItemActivity(it.tag as T, getAddItemActivityBundle())
            }

            onRemoveClickListener = {
                presenter.removeItem(it.tag as T)
            }
        }
    }

    abstract fun initPresenter(): AbstractListPresenter<T>

    open fun getAddItemActivityBundle(): Bundle? = null

    private fun setupRecyclerView() {
        val list = findViewById<RecyclerView>(listResId)
        list.adapter = adapter
        list.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        presenter.fillList(adapter.values)
    }

    private fun startShowItemActivity(item: T) {
        val intent = Intent(this, itemDetailActivityTypeToken).apply {
            putExtra(TAG_ITEM_KEY, item)
        }
        startActivity(intent)
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

    private fun startEditItemActivity(item: T, bundle: Bundle? = null) {
        val intent = Intent(this, itemAddActivityTypeToken).apply {
            putExtra(ADD_BUNDLE_KEY, bundle)
            putExtra(TAG_ITEM_KEY, item)
        }
        startActivityForResult(intent, ITEM_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == ITEM_REQUEST_CODE) {
            when (resultCode) {
                ITEM_RESULT_ADD -> {
                    val item = data?.extras?.get(ITEM_RESULT_KEY) as? T ?: return
                    adapter.apply {
                        values.add(item)
                        notifyItemInserted(itemCount)
                    }
                }

                ITEM_RESULT_EDIT -> {
                    val item = data?.extras?.get(ITEM_RESULT_KEY) as? T ?: return
                    adapter.values[editItemPosition] = item
                    adapter.notifyItemChanged(editItemPosition)
                }
            }

        }
    }

    fun removeItemFromAdapter(item: T) {
        adapter.values.remove(item)
        adapter.notifyDataSetChanged()
    }

    fun showListFillError() {
        AlertDialog.Builder(this)
            .setMessage(getFillErrorMessage())
            .setPositiveButton(getString(R.string.ok)) { _, _ -> finish() }
            .show()
    }

    abstract fun getFillErrorMessage(): String

    fun showRemoveSuccessMessage() {
        Toast.makeText(this, getString(R.string.list_remove_success), Toast.LENGTH_SHORT)
            .small()
            .show()
    }

    fun showRemoveError() {
        AlertDialog.Builder(this)
            .setMessage(getRemoveErrorMessage())
            .setPositiveButton(getString(R.string.ok)) { _, _ -> }
            .show()
    }

    abstract fun getRemoveErrorMessage(): String

    fun notifyDataSetChanged() {
        adapter.notifyDataSetChanged()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.cancelJobs()
    }

    companion object {
        const val ITEM_REQUEST_CODE = 0
        const val ITEM_RESULT_ADD = 0
        const val ITEM_RESULT_EDIT = 1
        const val ITEM_RESULT_NULL = 2
        const val ITEM_RESULT_KEY = "item_result"
        const val ADD_BUNDLE_KEY = "add_bundle"
        const val TAG_ITEM_KEY = "tag_item"
    }
}