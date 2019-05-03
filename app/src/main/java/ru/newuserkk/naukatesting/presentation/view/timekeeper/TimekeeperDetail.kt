package ru.newuserkk.naukatesting.presentation.view.timekeeper

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity;
import ru.newuserkk.naukatesting.R

import kotlinx.android.synthetic.main.activity_timekeeper_detail.*
import kotlinx.android.synthetic.main.content_timekeeper_detail.*
import kotlinx.android.synthetic.main.list_employee.*
import ru.newuserkk.naukatesting.presentation.presenter.timekeeper.TimekeeperDetailPresenter
import ru.newuserkk.naukatesting.presentation.view.employee.EmployeeRecyclerViewAdapter
import java.text.SimpleDateFormat
import java.util.*

class TimekeeperDetail : AppCompatActivity() {

    private val presenter = TimekeeperDetailPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_timekeeper_detail)
        setupActionBar()

        val date = intent?.extras?.getSerializable("date") as Date
        // using deprecated method since resources.configuration.locales is available only from APIv24
        timekeeper_detail_date.text = SimpleDateFormat("dd MMMM y", resources.configuration.locale)
            .format(date)

        setupRecyclerView()

        timekeeper_detail_add_button.setOnClickListener { view ->
            startAddEmployeeActivity()
        }

    }

    fun startAddEmployeeActivity() {

    }

    private fun setupActionBar() {
        setSupportActionBar(timekeeper_detail_toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
    }

    private fun setupRecyclerView() {
        employee_list.adapter = EmployeeRecyclerViewAdapter(presenter.getEmployeesData().toMutableList())
    }

}
