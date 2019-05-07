package ru.newuserkk.naukatesting.presentation.view.department

import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.content_department_add.*

import ru.newuserkk.naukatesting.R
import ru.newuserkk.naukatesting.domain.department.model.Department
import ru.newuserkk.naukatesting.presentation.presenter.common.AbstractItemAddPresenter
import ru.newuserkk.naukatesting.presentation.presenter.department.DepartmentAddPresenter
import ru.newuserkk.naukatesting.presentation.view.common.AbstractItemAddActivity


class DepartmentAddActivity : AbstractItemAddActivity<Department>() {

    override val presenter: AbstractItemAddPresenter<Department>
        get() = DepartmentAddPresenter(this)
    override val activityResId: Int
        get() = R.layout.activity_department_add
    override val progressBarResId: Int
        get() = R.id.department_add_progress_bar
    override val contentResId: Int
        get() = R.id.department_add_content
    override val submitButtonResId: Int
        get() = R.id.department_add_submit_button

    override fun getItemOptions(): DepartmentOptions {
        return DepartmentOptions(department_add_name_edit_text.text.toString())
    }

    override fun getSuccessMessage(): String = getString(R.string.departmen_add_success)
    override fun getAddErrorMessage(): String = getString(R.string.department_add_error)


    class DepartmentOptions(val departmentName: String): ItemOptions
}