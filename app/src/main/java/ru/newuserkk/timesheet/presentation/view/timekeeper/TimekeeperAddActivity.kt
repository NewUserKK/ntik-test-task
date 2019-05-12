package ru.newuserkk.timesheet.presentation.view.timekeeper

import android.os.Bundle
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.fields_timekeeper_add.*
import ru.newuserkk.timesheet.R
import ru.newuserkk.timesheet.domain.employee.model.Employee
import ru.newuserkk.timesheet.domain.timekeeper.model.AttendanceStatus
import ru.newuserkk.timesheet.domain.timekeeper.model.MarkedEmployee
import ru.newuserkk.timesheet.presentation.presenter.timekeeper.TimekeeperAddPresenter
import ru.newuserkk.timesheet.presentation.view.common.AbstractItemAddActivity
import ru.newuserkk.timesheet.presentation.view.common.AbstractListActivity
import java.util.*

class TimekeeperAddActivity : AbstractItemAddActivity<MarkedEmployee>() {

    override val presenter = TimekeeperAddPresenter(this)
    override val layoutResId = R.layout.activity_timekeeper_add
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
            timekeeper_add_employee_spinner.selectedItem as? Employee,
            timekeeper_add_status_spinner.selectedItem as AttendanceStatus
        )

    override fun editFillFields(item: MarkedEmployee) {}

    fun setupEmployeesAdapter(values: List<Employee>, selectionItem: Employee?) {
        val adapter = ArrayAdapter<Employee>(
            this,
            R.layout.support_simple_spinner_dropdown_item,
            values
        )
        timekeeper_add_employee_spinner.adapter = adapter
        timekeeper_add_employee_spinner.setSelection(
            adapter.getPosition(selectionItem)
        )
    }

    fun setupStatusAdapter(values: Array<AttendanceStatus>, selectionItem: AttendanceStatus?) {
        val adapter = ArrayAdapter<AttendanceStatus>(
            this,
            R.layout.support_simple_spinner_dropdown_item,
            values
        )
        timekeeper_add_status_spinner.adapter = adapter
        timekeeper_add_status_spinner.setSelection(
            adapter.getPosition(selectionItem)
        )
    }

    override fun getSuccessMessage(): String = getString(R.string.ok)
    override fun getAddErrorMessage(): String = getString(R.string.timekeeper_add_status_error)

    class MarkedEmployeeOptions(val date: Date, val employee: Employee?, val status: AttendanceStatus) : ItemOptions
}
