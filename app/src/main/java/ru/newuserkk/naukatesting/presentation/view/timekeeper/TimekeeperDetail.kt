package ru.newuserkk.naukatesting.presentation.view.timekeeper

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity;
import ru.newuserkk.naukatesting.R

import kotlinx.android.synthetic.main.activity_timekeeper_detail.*
import kotlinx.android.synthetic.main.content_timekeeper_detail.*
import kotlinx.android.synthetic.main.list_timekeeper_detail.*
import ru.newuserkk.naukatesting.presentation.presenter.timekeeper.TimekeeperDetailPresenter
import java.text.DateFormat
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
        timekeeperDetailDate.text = SimpleDateFormat("dd MMMM y", resources.configuration.locale)
            .format(date)

        setupRecyclerView()

        fab.setOnClickListener { view ->
            startAddEmployeeActivity()
        }

    }

    fun startAddEmployeeActivity() {

    }

    private fun setupActionBar() {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
    }

    private fun setupRecyclerView() {
        timekeeperDetailEmployeeList.adapter = EmployeeRecyclerViewAdapter(presenter.getEmployeesData())
    }

}
