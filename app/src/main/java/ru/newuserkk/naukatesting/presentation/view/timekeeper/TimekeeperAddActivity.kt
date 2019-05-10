package ru.newuserkk.naukatesting.presentation.view.timekeeper

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.fields_timekeeper_add.*
import ru.newuserkk.naukatesting.R
import ru.newuserkk.naukatesting.domain.employee.model.Employee
import ru.newuserkk.naukatesting.domain.timekeeper.model.AttendanceStatus
import ru.newuserkk.naukatesting.domain.timekeeper.model.MarkedEmployee
import ru.newuserkk.naukatesting.presentation.presenter.timekeeper.TimekeeperAddPresenter
import ru.newuserkk.naukatesting.presentation.view.common.AbstractItemAddActivity
import ru.newuserkk.naukatesting.presentation.view.common.AbstractListActivity
import java.util.*

class TimekeeperAddActivity : AbstractItemAddActivity<MarkedEmployee>() {

    override val presenter = TimekeeperAddPresenter(this)
    override val activityResId = R.layout.activity_timekeeper_add
    override val progressBarResId = R.id.timekeeper_add_progress_bar
    override val contentResId = R.id.timekeeper_add_content
    override val submitButtonResId = R.id.timekeeper_add_submit_button

    lateinit var date: Date

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bundle = intent?.extras?.getBundle(AbstractListActivity.ADD_BUNDLE_KEY)
        date = bundle?.getSerializable(TimekeeperCalendarActivity.DATE_KEY) as Date
        presenter.fillEmployeesSpinner()
        presenter.fillStatusSpinner()
    }

    override fun getItemOptions(): ItemOptions =
        MarkedEmployeeOptions(
            date,
            timekeeper_add_employee_spinner.selectedItem as Employee,
            timekeeper_add_status_spinner.selectedItem as AttendanceStatus
        )

    override fun fillFields(item: MarkedEmployee) {
        (timekeeper_add_employee_spinner.adapter as ArrayAdapter<Employee>)
            .getPosition(item.employee)

        (timekeeper_add_status_spinner.adapter as ArrayAdapter<AttendanceStatus>)
            .getPosition(item.status)
    }

    fun setupEmployeesAdapter(values: List<Employee>) {
        timekeeper_add_employee_spinner.adapter = ArrayAdapter<Employee>(
            this,
            R.layout.support_simple_spinner_dropdown_item,
            values
        )
    }

    fun setupStatusAdapter(values: Array<AttendanceStatus>) {
        timekeeper_add_status_spinner.adapter = ArrayAdapter<AttendanceStatus>(
            this,
            R.layout.support_simple_spinner_dropdown_item,
            values
        )
    }

    override fun getSuccessMessage(): String = getString(R.string.ok)
    override fun getAddErrorMessage(): String = getString(R.string.timekeeper_add_status_error)

    class MarkedEmployeeOptions(val date: Date, val employee: Employee, val status: AttendanceStatus) : ItemOptions
}
