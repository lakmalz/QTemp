package com.lakmalz.qtemper.utils.calendarview

import java.util.*

interface CalendarAdapterListeners {
    fun selectedDate(date: Date?, position: Int)
}