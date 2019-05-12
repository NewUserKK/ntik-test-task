package ru.newuserkk.timesheet.presentation.view.role

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_role_select.*
import ru.newuserkk.timesheet.R
import ru.newuserkk.timesheet.domain.role.model.Role
import ru.newuserkk.timesheet.presentation.presenter.role.SelectRolePresenter
import ru.newuserkk.timesheet.presentation.view.department.DepartmentListActivity
import ru.newuserkk.timesheet.presentation.view.employee.EmployeeListActivity
import ru.newuserkk.timesheet.presentation.view.timekeeper.TimekeeperCalendarActivity


class SelectRoleActivity : AppCompatActivity() {

    private val presenter: SelectRolePresenter = SelectRolePresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_role_select)
        setupSpinner()
        setupListeners()
    }

    private fun setupSpinner() {
        select_role_list.adapter = ArrayAdapter<RoleWithName>(this,
            R.layout.support_simple_spinner_dropdown_item,
            presenter.getRoleList().map { RoleWithName(it, getLocalizedRoleName(it)) }
        )
    }

    private fun setupListeners() {
        select_role_submit_button.setOnClickListener {
            startSpecializedActivity((select_role_list.selectedItem as RoleWithName).role)
        }
    }

    private fun startSpecializedActivity(role: Role) {
        val intent = when (role) {
            Role.TIMEKEEPER -> Intent(this, TimekeeperCalendarActivity::class.java)
            Role.DEPARTMENT_ADMINISTRATOR -> Intent(this, DepartmentListActivity::class.java)
            Role.EMPLOYEE_ADMINISTRATOR -> Intent(this, EmployeeListActivity::class.java)
        }
        startActivity(intent)
    }

    private fun getLocalizedRoleName(role: Role): String {
        return resources.getString(
            when (role) {
                Role.TIMEKEEPER -> R.string.timekeeper
                Role.DEPARTMENT_ADMINISTRATOR -> R.string.department_administrator
                Role.EMPLOYEE_ADMINISTRATOR -> R.string.employee_administrator
            }
        )
    }
}

private data class RoleWithName(val role: Role, val localizedName: String) {
    override fun toString(): String {
        return localizedName
    }
}