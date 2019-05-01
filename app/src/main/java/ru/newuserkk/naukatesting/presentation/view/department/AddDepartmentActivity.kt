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
import ru.newuserkk.naukatesting.presentation.presenter.department.AddDepartmentPresenter
import kotlin.coroutines.CoroutineContext


class AddDepartmentActivity : AppCompatActivity() {

    private val presenter = AddDepartmentPresenter(this)
    private var result: Department? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_department)
        departmentAddSubmitButton.setOnClickListener {
            presenter.addDepartment()
        }
    }

    fun getDepartmentName(): String {
        return departmentAddNameField.text.toString()
    }

    fun setResult(result: Department) {
        this.result = result
    }

    fun showProgress() {
        departmentAddProgressBar.visibility = View.VISIBLE
        departmentAddContent.visibility = View.GONE
    }

    fun hideProgress() {
        departmentAddProgressBar.visibility = View.GONE
        departmentAddContent.visibility = View.VISIBLE
    }

    fun showSuccessMessage() {
        Toast.makeText(this, "Successfully added department!", Toast.LENGTH_LONG).also {
            val toastLayout = it.view as ViewGroup
            val toastTextView = toastLayout.getChildAt(0) as TextView
            toastTextView.textSize = 12f
        }.show()
    }

    fun showError(e: Throwable) {
        AlertDialog.Builder(this)
            .setMessage(getString(R.string.add_department_error) + e.localizedMessage)
            .show()
    }

    override fun finish() {
        result.let { result ->
            val returnIntent = Intent().apply {
                putExtra(
                    DepartmentListActivity.DEPARTMENT_RESULT_KEY,
                    result)
            }
            if (result != null) {
                setResult(DepartmentListActivity.RESULT_OK, returnIntent)
            } else {
                setResult(DepartmentListActivity.RESULT_NULL, returnIntent)
            }
        }

        super.finish()
    }

    companion object {
        const val LOG_TAG = "AddDepartmentActivity"
    }
}
