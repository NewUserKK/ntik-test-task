package ru.newuserkk.naukatesting.presentation.presenter.role

import ru.newuserkk.naukatesting.domain.role.model.Role
import ru.newuserkk.naukatesting.presentation.view.role.SelectRoleActivity

class SelectRolePresenter(val view: SelectRoleActivity) {
    fun getRoleList(): List<Role> {
        return Role.values().toList()
    }
}