package com.dev175.customcalendar

import android.view.View
import com.dev175.customcalendar.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.DateTimeFormatter

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    private lateinit var selectedDate: LocalDate

    override fun initUi() {
        super.initUi()
        selectedDate = LocalDate.now()
        setCurrentMonth()
    }

    private fun setCurrentMonth() {
        monthYearFromDate(selectedDate)?.let {
            bindings.monthYearTv.text = it

            val days = getDaysInMonth(selectedDate)
            val adapter = CalendarAdapter()
            val calendarItems = days.map { CalendarItem(day = it) }
            adapter.items = calendarItems.toMutableList()
            bindings.calendarRv.adapter = adapter

        }
    }

    private fun getDaysInMonth(date: LocalDate): List<String> {
        val daysInMonthList = mutableListOf<String>()
        val yearMonth = YearMonth.from(date)

        val daysInMonth = yearMonth.lengthOfMonth()
        val firstOfMonth = date.withDayOfMonth(1);
        val dayOfWeek = firstOfMonth.dayOfWeek.value

        for (i in 1..42) {
            if (i <= dayOfWeek || i > daysInMonth + dayOfWeek) {
                daysInMonthList.add("")
            } else {
                val d = i - dayOfWeek
                daysInMonthList.add(d.toString())
            }
        }
        return daysInMonthList
    }

    private fun monthYearFromDate(date: LocalDate): String? {
        val formatter = DateTimeFormatter.ofPattern("MMMM yyyy");
        return date.format(formatter)
    }

    fun prevMonth(view: View) {
        selectedDate = selectedDate.minusMonths(1)
        setCurrentMonth()
    }
    fun nextMonth(view: View) {
        selectedDate = selectedDate.plusMonths(1)
        setCurrentMonth()
    }
}