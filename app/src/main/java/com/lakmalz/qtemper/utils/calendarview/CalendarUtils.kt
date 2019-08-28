package com.lakmalz.qtemper.utils.calendarview

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class CalendarUtils {
    companion object {
        /**
         * get month as string "JUN"
         *
         * @param month (05)
         * @return
         */
        fun getMonth(month: String): MonthType {
            var monthType: MonthType? = null
            when (month) {
                "01" -> monthType = MonthType(0, "JAN")
                "02" -> monthType = MonthType(1, "FEB")
                "03" -> monthType = MonthType(2, "MAR")
                "04" -> monthType = MonthType(3, "APR ")
                "05" -> monthType = MonthType(4, "MAY")
                "06" -> monthType = MonthType(5, "JUN-")
                "07" -> monthType = MonthType(6, "JUL")
                "08" -> monthType = MonthType(7, "AUG")
                "09" -> monthType = MonthType(8, "SEP")
                "10" -> monthType = MonthType(9, "OCT")
                "11" -> monthType = MonthType(10, "NOV")
                "12" -> monthType = MonthType(11, "DEC")
            }

            return monthType!!
        }


        /**
         * get date list between two days (date range date list)
         *
         * @param dateString1
         * @param dateString2
         * @return
         */
        fun getDates(dateString1: String, dateString2: String): List<Date> {
            val dates = java.util.ArrayList<Date>()
            val df1 = SimpleDateFormat("yyyy-MM-dd")

            var date1: Date? = null
            var date2: Date? = null

            try {
                date1 = df1.parse(dateString1)
                date2 = df1.parse(dateString2)
            } catch (e: ParseException) {
                e.printStackTrace()
            }

            val cal1 = Calendar.getInstance()
            cal1.time = date1!!


            val cal2 = Calendar.getInstance()
            cal2.time = date2!!

            while (!cal1.after(cal2)) {
                dates.add(cal1.time)
                cal1.add(Calendar.DATE, 1)
            }
            return dates
        }

        /**
         * get month dates of range
         */
        fun getDateRangeList(): List<CalendarDate> {
            val fNormalDate = SimpleDateFormat("yyyy-MM-dd")
            val cal = Calendar.getInstance()

            val currentDay = Integer.parseInt(SimpleDateFormat("d").format(cal.time))
            val currentMonth = Integer.parseInt(SimpleDateFormat("MM").format(cal.time))
            val currentYear = Integer.parseInt(SimpleDateFormat("y").format(cal.time))

            val rMonth = currentMonth + 4 - 12

            var endMonth = currentMonth + 4
            val endDate = currentDay
            var endYear = currentYear
            if (rMonth > 0) {
                endMonth = rMonth
                endYear = currentYear + 1
            }

            val selectableItems = java.util.ArrayList<CalendarDate>()
            val dates = getDates(fNormalDate.format(cal.time), "$endYear-$endMonth-$endDate")
            var isSelected = false

            for (date in dates) {
                val mm = SimpleDateFormat("MM").format(date.time)
                val dd = SimpleDateFormat("dd").format(date.time)
                val eee = SimpleDateFormat("EEE").format(date.time)
                if (!isSelected) {
                    selectableItems.add(CalendarDate(dd, eee, getMonth(mm), true, date))
                    isSelected = true
                } else {
                    selectableItems.add(CalendarDate(dd, eee, getMonth(mm), false, date))
                }
            }

            return selectableItems
        }
    }
}