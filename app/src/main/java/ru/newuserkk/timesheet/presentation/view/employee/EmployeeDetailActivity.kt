package ru.newuserkk.timesheet.presentation.view.employee

import kotlinx.android.synthetic.main.fields_employee_detail.*
import ru.newuserkk.timesheet.R
import ru.newuserkk.timesheet.domain.employee.model.Employee
import ru.newuserkk.timesheet.presentation.view.common.AbstractItemDetailActivity
import java.text.SimpleDateFormat
import java.util.*

class EmployeeDetailActivity : AbstractItemDetailActivity<Employee>() {

    override val layoutResId = R.layout.activity_employee_detail

    override fun showInfo(item: Employee) {
        item.let {
            employee_detail_first_name_value.text = it.firstName
            employee_detail_last_name_value.text = it.lastName
            employee_detail_middle_name_value.text = it.middleName
            employee_detail_birth_date_value.text = SimpleDateFormat("dd.MM.yyyy", Locale.US).format(it.birthDate)
            employee_detail_position_value.text = it.position
            it.address.let { address ->
                employee_detail_country_value.text = address.country
                employee_detail_city_value.text = address.city
                employee_detail_street_value.text = address.street
                employee_detail_house_number_value.text = address.house
                employee_detail_flat_value.text = address.flat
            }
            employee_detail_phone_value.text = it.phone
        }
    }
}
