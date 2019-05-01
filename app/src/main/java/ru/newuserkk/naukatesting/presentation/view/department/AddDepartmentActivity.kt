package ru.newuserkk.naukatesting.presentation.view.department

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_add_department.*
import kotlinx.android.synthetic.main.content_add_department.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

import ru.newuserkk.naukatesting.R
import ru.newuserkk.naukatesting.presentation.presenter.department.AddDepartmentPresenter
import kotlin.coroutines.CoroutineContext


class AddDepartmentActivity : AppCompatActivity() {

    private val presenter = AddDepartmentPresenter(this)

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

    fun showProgress() {
        departmentAddProgressBar.visibility = View.VISIBLE
        departmentAddContent.visibility = View.GONE
    }

    fun hideProgress() {
        departmentAddProgressBar.visibility = View.GONE
//        departmentAddContent.visibility = View.VISIBLE
    }

    fun showSuccessMessage() {
        Toast.makeText(this, "Successfully added department!", Toast.LENGTH_LONG).also {
            val toastLayout = it.view as ViewGroup
            val toastTextView = toastLayout.getChildAt(0) as TextView
            toastTextView.textSize = 12f
        }.show()
    }

    fun goBack() {
        Log.d(LOG_TAG, "Going back")
        finish()
    }

    companion object {
        const val LOG_TAG = "AddDepartmentActivity"
    }
}
