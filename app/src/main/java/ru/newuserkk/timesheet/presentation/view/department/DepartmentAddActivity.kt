package ru.newuserkk.timesheet.presentation.view.department

import kotlinx.android.synthetic.main.fields_department_add.*

import ru.newuserkk.timesheet.R
import ru.newuserkk.timesheet.domain.department.model.Department
import ru.newuserkk.timesheet.presentation.presenter.common.AbstractItemAddPresenter
import ru.newuserkk.timesheet.presentation.presenter.department.DepartmentAddPresenter
import ru.newuserkk.timesheet.presentation.view.common.AbstractItemAddActivity
import ru.newuserkk.timesheet.presentation.view.common.setTextAndPosition


class DepartmentAddActivity : AbstractItemAddActivity<Department>() {

    override val presenter: AbstractItemAddPresenter<Department> = DepartmentAddPresenter(this)
    override val layoutResId = R.layout.activity_department_add
    override val progressBarResId = R.id.department_add_progress_bar
    override val contentResId = R.id.department_add_content
    override val submitButtonResId = R.id.department_add_submit_button

    override fun getItemOptions(): DepartmentOptions {
        return DepartmentOptions(department_add_name_edit_text.text.toString())
    }

    override fun getSuccessMessage(): String = getString(R.string.ok)
    override fun getAddErrorMessage(): String = getString(R.string.department_add_error)

    override fun fillFields(item: Department) {
        department_add_name_edit_text.setTextAndPosition(item.name)
    }

    class DepartmentOptions(val departmentName: String): ItemOptions
}
