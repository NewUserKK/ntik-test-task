package ru.newuserkk.naukatesting.presentation.view.department

import android.app.AlertDialog
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity;
import ru.newuserkk.naukatesting.R

import kotlinx.android.synthetic.main.activity_department_list.*
import kotlinx.android.synthetic.main.list_department.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.newuserkk.naukatesting.presentation.presenter.department.DepartmentListPresenter
import kotlin.coroutines.CoroutineContext

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

    private fun setupRecyclerView() {
        adapter = DepartmentRecyclerViewAdapter(mutableListOf())
        departmentList.adapter = adapter
        presenter.addDepartments(adapter?.values ?: return)
    }

    private fun startAddDepartmentActivity() {
        val intent = Intent(this, AddDepartmentActivity::class.java)
        startActivity(intent)
    }
}
