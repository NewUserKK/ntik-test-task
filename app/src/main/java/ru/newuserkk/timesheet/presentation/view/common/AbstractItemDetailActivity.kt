package ru.newuserkk.timesheet.presentation.view.common

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import java.io.Serializable

abstract class AbstractItemDetailActivity<T: Serializable>: AppCompatActivity() {

    abstract val layoutResId: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutResId)

        val item = intent?.extras?.getSerializable(AbstractListActivity.TAG_ITEM_KEY) as T
        showInfo(item)
    }

    abstract fun showInfo(item: T)

    companion object {
        const val LOG_TAG = "AbstractItemDetailActivity"
    }

}