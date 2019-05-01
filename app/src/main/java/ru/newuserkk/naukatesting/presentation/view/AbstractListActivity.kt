package ru.newuserkk.naukatesting.presentation.view

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import ru.newuserkk.naukatesting.R
import ru.newuserkk.naukatesting.presentation.presenter.AbstractListPresenter
import java.io.Serializable

abstract class AbstractListActivity<T: Serializable>: AppCompatActivity() {

    protected abstract val presenter: AbstractListPresenter<T>
    protected abstract val contentViewResId: Int
    protected abstract val toolbarResId: Int
    protected abstract val addButtonResId: Int
    protected abstract val listResId: Int
    protected abstract val addActivityTypeToken: Class<AbstractAddItemActivity<T>>

    protected abstract var adapter: AbstractItemRecyclerViewAdapter<T>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(contentViewResId)
        setSupportActionBar(findViewById(toolbarResId))

        setupRecyclerView()

        findViewById<Button>(addButtonResId).setOnClickListener {
            startAddItemActivity()
        }
    }

    fun adapterNotifyDataSetChanged() {
        adapter.notifyDataSetChanged()
    }

    fun showListFillError() {
        AlertDialog.Builder(this)
            .setMessage(getString(R.string.department_load_fail))
            .setPositiveButton(getString(R.string.ok)) { _, _ -> finish() }
            .show()
    }

    private fun startAddItemActivity() {
        val intent = Intent(this, addActivityTypeToken)
        startActivityForResult(intent, AbstractListActivity.ITEM_REQUEST_CODE)
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
    }
}