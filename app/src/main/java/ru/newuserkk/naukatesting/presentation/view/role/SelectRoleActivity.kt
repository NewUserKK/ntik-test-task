package ru.newuserkk.naukatesting.presentation.view.role

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_select_role.*
import ru.newuserkk.naukatesting.R
import ru.newuserkk.naukatesting.presentation.presenter.role.SelectRolePresenter
import ru.newuserkk.naukatesting.domain.model.Role
import ru.newuserkk.naukatesting.presentation.view.timekeeper.TimekeeperActivity



class SelectRoleActivity : AppCompatActivity() {

    private val presenter: SelectRolePresenter = SelectRolePresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_role)
        setupSpinner()
        setupListeners()

    }

    private fun setupSpinner() {
        selectRoleList.adapter = ArrayAdapter<RoleWithName>(this,
            R.layout.support_simple_spinner_dropdown_item,
            presenter.getRoleList().map { RoleWithName(it, getLocalizedRoleName(it)) }
        )
    }

    private fun setupListeners() {
        selectRoleButtonOk.setOnClickListener {
            startSpecializedActivity((selectRoleList.selectedItem as RoleWithName).role)
        }
    }

    private fun startSpecializedActivity(role: Role) {
        val intent = when (role) {
            Role.TIMEKEEPER -> Intent(this, TimekeeperActivity::class.java)
            else -> TODO()
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