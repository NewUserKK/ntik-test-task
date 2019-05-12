package ru.newuserkk.timesheet.presentation.presenter.role

import ru.newuserkk.timesheet.domain.role.model.Role
import ru.newuserkk.timesheet.presentation.view.role.SelectRoleActivity

class SelectRolePresenter(val view: SelectRoleActivity) {
    fun getRoleList(): List<Role> {
        return Role.values().toList()
    }
}