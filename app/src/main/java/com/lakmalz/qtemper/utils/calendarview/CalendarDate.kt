package com.lakmalz.qtemper.utils.calendarview

import java.util.*


/**
 * Created by Lakmal Weerasekara (Lakmalz) on 7/20/2019.
 */

class CalendarDate(
    var date: String,
    var day: String,
    var monthType: MonthType, isSelectedDefaultDate: Boolean, selectedDate: Date
) : SelectableItem(isSelectedDefaultDate, selectedDate)