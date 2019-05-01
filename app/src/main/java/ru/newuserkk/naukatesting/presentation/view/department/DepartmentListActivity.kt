package ru.newuserkk.naukatesting.presentation.view.department

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity;
import ru.newuserkk.naukatesting.R

import kotlinx.android.synthetic.main.activity_department_list.*
import kotlinx.android.synthetic.main.list_department.*
import ru.newuserkk.naukatesting.domain.department.model.Department
import ru.newuserkk.naukatesting.presentation.presenter.department.DepartmentListPresenter

class DepartmentListActivity : AppCompatActivity() {

    private val presenter = DepartmentListPresenter(this)
    private var adapter: DepartmentRecyclerViewAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_department_list)
        setSupportActionBar(toolbar)

        setupRecyclerView()

        fab.setOnClickListener {
            startAddDepartmentActivity()
        }
    }

    fun showFillError() {
        AlertDialog.Builder(this)
            .setMessage(getString(R.string.department_load_fail))
            .setPositiveButton(getString(R.string.ok)) { _, _ -> finish() }
            .show()
    }

    fun adapterNotifyDataSetChanged() {
        adapter?.notifyDataSetChanged()
    }

    private fun startAddDepartmentActivity() {
        val intent = Intent(this, AddDepartmentActivity::class.java)
        startActivityForResult(intent, REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            val item = data?.extras?.get(DEPARTMENT_RESULT_KEY) as Department
            adapter?.apply {
                values.add(item)
                notifyItemInserted(itemCount)
            }
        }
    }

    private fun setupRecyclerView() {
        adapter = DepartmentRecyclerViewAdapter(mutableListOf())
        departmentList.adapter = adapter
        presenter.fillDepartmentList(adapter?.values ?: return)
    }

    companion object {
        const val REQUEST_CODE = 0
        const val RESULT_OK = 0
        const val RESULT_NULL = 1
        const val DEPARTMENT_RESULT_KEY = "department_result"
    }
}
