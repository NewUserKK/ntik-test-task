package ru.newuserkk.naukatesting.presentation.view.department

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_add_department.*
import kotlinx.android.synthetic.main.content_add_department.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

import ru.newuserkk.naukatesting.R
import ru.newuserkk.naukatesting.domain.department.model.Department
import ru.newuserkk.naukatesting.presentation.presenter.AbstractAddItemPresenter
import ru.newuserkk.naukatesting.presentation.presenter.department.AddDepartmentPresenter
import ru.newuserkk.naukatesting.presentation.presenter.department.DepartmentListPresenter
import ru.newuserkk.naukatesting.presentation.view.AbstractAddItemActivity
import kotlin.coroutines.CoroutineContext


class AddDepartmentActivity : AbstractAddItemActivity<Department>() {

    override val presenter: AbstractAddItemPresenter<Department>
        get() = AddDepartmentPresenter(this)
    override val activityResId: Int
        get() = R.layout.activity_add_department
    override val progressBarResId: Int
        get() = R.id.departmentAddProgressBar
    override val contentResId: Int
        get() = R.id.departmentAddContent
    override val submitButtonResId: Int
        get() = R.id.departmentAddSubmitButton
    override val errorStringResId: Int
        get() = R.string.add_department_error

    override fun getItemOptions(): DepartmentOptions {
        return DepartmentOptions(departmentAddNameField.text.toString())
    }

    class DepartmentOptions(val departmentName: String): ItemOptions()
}
