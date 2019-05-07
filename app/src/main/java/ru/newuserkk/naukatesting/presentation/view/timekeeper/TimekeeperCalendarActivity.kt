package ru.newuserkk.naukatesting.presentation.view.timekeeper

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.roomorama.caldroid.CaldroidFragment
import com.roomorama.caldroid.CaldroidListener
import ru.newuserkk.naukatesting.R
import java.util.*

class TimekeeperCalendarActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_timekeeper_calendar)
        setupCalendar()
    }

    private fun setupCalendar() {
        val calendar = CaldroidFragment().apply {
            arguments = Bundle().apply {
                putInt(CaldroidFragment.START_DAY_OF_WEEK, CaldroidFragment.MONDAY)
                putBoolean(CaldroidFragment.SIX_WEEKS_IN_CALENDAR, false)
            }
            caldroidListener = object: CaldroidListener() {
                override fun onSelectDate(date: Date, view: View?) {
                    startDetailActivity(date)
                }
            }
        }

        supportFragmentManager.beginTransaction()
            .add(R.id.timekeeper_calendar_container, calendar)
            .commit()
    }

    fun startDetailActivity(date: Date) {
        val intent = Intent(this, TimekeeperListActivity::class.java).apply {
            putExtra(DATE_KEY, date)
        }
        startActivity(intent)
    }

    companion object {
        const val DATE_KEY = "date"
    }
}