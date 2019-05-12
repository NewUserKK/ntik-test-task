package ru.newuserkk.timesheet.presentation.view.employee

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.DatePicker
import kotlinx.android.synthetic.main.fields_employee_add.*
import ru.newuserkk.timesheet.R
import ru.newuserkk.timesheet.domain.department.model.Department
import ru.newuserkk.timesheet.domain.employee.model.Employee
import ru.newuserkk.timesheet.presentation.presenter.employee.EmployeeAddPresenter
import ru.newuserkk.timesheet.presentation.view.common.AbstractItemAddActivity
import ru.newuserkk.timesheet.presentation.view.common.setTextAndPosition
import java.text.SimpleDateFormat
import java.util.*

class EmployeeAddActivity : AbstractItemAddActivity<Employee>() {

    override val presenter = EmployeeAddPresenter(this)
    override val layoutResId: Int
        get() = R.layout.activity_employee_add
    override val progressBarResId: Int
        get() = R.id.employee_add_progress_bar
    override val contentResId: Int
        get() = R.id.employee_add_content
    override val submitButtonResId: Int
        get() = R.id.employee_add_submit_button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        employee_add_birth_date_picker.setOnClickListener {
            val datePickerListener = DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                employee_add_birth_date_value.text = "$dayOfMonth.$month.$year"
            }
            var day = 1
            var month = 1
            var year = 2019
            itemToEdit?.let { item ->
                val calendar = Calendar.getInstance()
                calendar.time = item.birthDate
                day = calendar.get(Calendar.DAY_OF_WEEK)
                month = calendar.get(Calendar.MONTH)
                year = calendar.get(Calendar.YEAR)
            }
            DatePickerDialog(
                this,
                android.R.style.Theme_Material_Light_Dialog_Alert,
                datePickerListener,
                year, month, day
            ).show()
        }

        presenter.fillDepartmentsSpinner()
    }

    fun setupDepartmentsAdapter(values: List<Department>, selectionItem: Department?) {
        val adapter = ArrayAdapter<Department>(
            this,
            R.layout.support_simple_spinner_dropdown_item,
            values
        )
        employee_add_department_spinner.adapter = adapter
        employee_add_department_spinner.setSelection(
            adapter.getPosition(selectionItem)
        )
    }

    override fun getItemOptions(): ItemOptions = EmployeeOptions(
        firstName = employee_add_first_name_edit_text.text.toString(),
        lastName = employee_add_last_name_edit_text.text.toString(),
        middleName = employee_add_middle_name_edit_text.text.toString(),
        birthDate = employee_add_birth_date_value.text.toString(),
        department = employee_add_department_spinner.selectedItem as? Department,
        position = employee_add_position_edit_text.text.toString(),
        country = employee_add_country_edit_text.text.toString(),
        city = employee_add_city_edit_text.text.toString(),
        street = employee_add_street_edit_text.text.toString(),
        house = employee_add_house_number_edit_text.text.toString(),
        flat = employee_add_flat_edit_text.text.toString(),
        phone = employee_add_phone_edit_text.text.toString()
    )

    override fun editFillFields(item: Employee) {
        item.let {
            employee_add_first_name_edit_text.setTextAndPosition(it.firstName)
            employee_add_last_name_edit_text.setTextAndPosition(it.lastName)
            employee_add_middle_name_edit_text.setTextAndPosition(it.middleName)
            employee_add_birth_date_value.text = SimpleDateFormat("dd.MM.yyyy", Locale.US).format(it.birthDate)
            employee_add_position_edit_text.setTextAndPosition(it.position)
            it.address.let { address ->
                employee_add_country_edit_text.setTextAndPosition(address.country)
                employee_add_city_edit_text.setTextAndPosition(address.city)
                employee_add_street_edit_text.setTextAndPosition(address.street)
                employee_add_house_number_edit_text.setTextAndPosition(address.house)
                employee_add_flat_edit_text.setTextAndPosition(address.flat)
            }
            employee_add_phone_edit_text.setTextAndPosition(it.phone)
        }
    }

    override fun getSuccessMessage(): String = getString(R.string.ok)
    override fun getAddErrorMessage(): String = getString(R.string.add_employee_error)
    class EmployeeOptions(
        val firstName: String,
        val lastName: String,
        val middleName: String,
        val birthDate: String,
        val department: Department?,
        val position: String,
        val country: String,
        val city: String,
        val street: String,
        val house: String,
        val flat: String,
        val phone: String
    ) : ItemOptions

}
