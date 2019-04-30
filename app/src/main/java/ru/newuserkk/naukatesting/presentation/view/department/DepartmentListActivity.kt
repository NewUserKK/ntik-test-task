package ru.newuserkk.naukatesting.presentation.view.department

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView
import ru.newuserkk.naukatesting.R

import kotlinx.android.synthetic.main.activity_department_list.*
import kotlinx.android.synthetic.main.list_department.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.newuserkk.naukatesting.presentation.presenter.department.DepartmentPresenter
import kotlin.coroutines.CoroutineContext

class DepartmentListActivity : AppCompatActivity(), CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO

    private val presenter = DepartmentPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_department_list)
        setSupportActionBar(toolbar)

        setupRecyclerView()

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }

    private fun setupRecyclerView() {
        departmentList.adapter = DepartmentRecyclerViewAdapter(mutableListOf())
        launch {
            (departmentList.adapter as DepartmentRecyclerViewAdapter).values.addAll(presenter.getDepartments())
        }
    }
}
