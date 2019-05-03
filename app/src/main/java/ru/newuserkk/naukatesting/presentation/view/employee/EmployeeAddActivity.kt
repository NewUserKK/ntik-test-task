package ru.newuserkk.naukatesting.presentation.view.employee

import android.os.Bundle
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.fields_employee_add.*
import ru.newuserkk.naukatesting.R
import ru.newuserkk.naukatesting.domain.department.model.Department
import ru.newuserkk.naukatesting.domain.employee.model.Employee
import ru.newuserkk.naukatesting.presentation.presenter.employee.EmployeeAddPresenter
import ru.newuserkk.naukatesting.presentation.view.common.AbstractItemAddActivity

class EmployeeAddActivity : AbstractItemAddActivity<Employee>() {

    override val presenter = EmployeeAddPresenter(this)
    override val activityResId: Int
        get() = R.layout.activity_employee_add
    override val progressBarResId: Int
        get() = R.id.employee_add_progress_bar
    override val contentResId: Int
        get() = R.id.employee_add_content
    override val submitButtonResId: Int
        get() = R.id.employee_add_submit_button
    override val errorStringResId: Int
        get() = R.string.error_add_employee

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.retrieveDepartments()
    }

    fun setupAdapter(values: List<Department>) {
        employee_add_department_spinner.adapter = ArrayAdapter<Department>(this,
            R.layout.support_simple_spinner_dropdown_item,
            values
        )
    }

    override fun getItemOptions(): ItemOptions = EmployeeOptions(
        firstName = employee_add_first_name_edit_text.text.toString(),
        lastName = employee_add_last_name_edit_text.text.toString(),
        middleName = employee_add_middle_name_edit_text.text.toString(),
        birthDate = employee_add_birth_date_edit_text.text.toString(),
        department = employee_add_department_spinner.selectedItem as Department,
        position = employee_add_position_edit_text.text.toString(),
        country = employee_add_country_edit_text.text.toString(),
        city = employee_add_city_edit_text.text.toString(),
        street = employee_add_street_edit_text.text.toString(),
        house = employee_add_house_number_edit_text.text.toString(),
        flat = employee_add_flat_edit_text.text.toString(),
        phone = employee_add_phone_edit_text.text.toString()
    )

    override fun showSuccessMessage() {
        //TODO: string resource
        Toast.makeText(this, "Successfully added employee!", Toast.LENGTH_LONG).also {
            val toastLayout = it.view as ViewGroup
            val toastTextView = toastLayout.getChildAt(0) as TextView
            toastTextView.textSize = 12f
        }.show()
    }

    class EmployeeOptions(
        val firstName: String,
        val lastName: String,
        val middleName: String,
        val birthDate: String,
        val department: Department,
        val position: String,
        val country: String,
        val city: String,
        val street: String,
        val house: String,
        val flat: String,
        val phone: String
    ): ItemOptions()

}
