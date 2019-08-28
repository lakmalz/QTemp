package com.lakmalz.qtemper.utils.calendarview

import java.util.*

interface HorizontalCalendarListener {
    fun onClickCalendarItem(date: Date, position: Int)
}