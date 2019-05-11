package ru.newuserkk.naukatesting.presentation.view.timekeeper

import android.os.Bundle
import kotlinx.android.synthetic.main.activity_department_list.*
import kotlinx.android.synthetic.main.content_timekeeper_list.*
import kotlinx.android.synthetic.main.list_timekeeper.*
import ru.newuserkk.naukatesting.R
import ru.newuserkk.naukatesting.domain.timekeeper.model.MarkedEmployee
import ru.newuserkk.naukatesting.presentation.presenter.timekeeper.TimekeeperListPresenter
import ru.newuserkk.naukatesting.presentation.view.common.AbstractItemRecyclerViewAdapter
import ru.newuserkk.naukatesting.presentation.view.common.AbstractListActivity
import ru.newuserkk.naukatesting.presentation.view.timekeeper.TimekeeperCalendarActivity.Companion.DATE_KEY
import java.text.SimpleDateFormat
import java.util.*

class TimekeeperListActivity : AbstractListActivity<MarkedEmployee>() {

    override val activityLayoutResId = R.layout.activity_timekeeper_list
    override val toolbarResId = R.id.timekeeper_list_toolbar
    override val addButtonResId = R.id.timekeeper_list_add_button
    override val listResId = R.id.timekeeper_employee_list
    override val itemAddActivityTypeToken = TimekeeperAddActivity::class.java
    override val adapter: AbstractItemRecyclerViewAdapter<MarkedEmployee> =
        MarkedEmployeeRecyclerViewAdapter().apply {
            onRemoveClickListener = {
                presenter.removeItem(it.tag as MarkedEmployee)
            }
        }

    lateinit var date: Date

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        timekeeper_list_date.text = SimpleDateFormat("dd MMMM y", resources.configuration.locale)
            .format(date)
    }

    override fun initPresenter(): TimekeeperListPresenter {
        date = intent?.extras?.getSerializable(DATE_KEY) as Date
        return TimekeeperListPresenter(this, date)
    }

    override fun getAddActivityBundle(): Bundle? {
        return Bundle().apply { putSerializable(TimekeeperCalendarActivity.DATE_KEY, date) }
    }

    override fun getFillErrorMessage(): String = getString(R.string.timekeeper_employee_load_fail)

    override fun getRemoveErrorMessage(): String = getString(R.string.remove_item_fail)
}
