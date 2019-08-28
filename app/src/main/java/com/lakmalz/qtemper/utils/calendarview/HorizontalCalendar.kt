package com.lakmalz.qtemper.utils.calendarview

import android.content.Context
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.widget.RelativeLayout
import com.dgreenhalgh.android.simpleitemdecoration.linear.DividerItemDecoration
import com.dgreenhalgh.android.simpleitemdecoration.linear.EndOffsetItemDecoration
import com.dgreenhalgh.android.simpleitemdecoration.linear.StartOffsetItemDecoration
import com.lakmalz.qtemper.R
import com.lakmalz.qtemper.utils.calendarview.CalendarUtils.Companion.getDateRangeList
import com.timehop.stickyheadersrecyclerview.StickyRecyclerHeadersDecoration
import java.util.*
import kotlin.collections.ArrayList


/**
 * Created by Lakmal Weerasekara (Lakmalz) on 7/20/2019.
 */

class HorizontalCalendar : RelativeLayout, CalendarAdapterListeners {

    private var mContext: Context
    private var mListeners: HorizontalCalendarListener
    private lateinit var mCalendarAdapter: CalendarAdapter
    private lateinit var rvCalendar: RecyclerView

    private var mDividerItemDecoration: DividerItemDecoration? = null
    private var mStartOffsetItemDecoration: StartOffsetItemDecoration? = null
    private var mEndOffsetItemDecoration: EndOffsetItemDecoration? = null


    constructor(context: Context ) : super(context) {
        this.mContext = context
        this.mListeners = mContext as HorizontalCalendarListener
        setDecorations()
        initView()
        initAdapter()
        setDefaultDateRange()
    }

    /**
     * set decorations
     */
    private fun setDecorations() {
        mDividerItemDecoration = DividerItemDecoration(ContextCompat.getDrawable(mContext, R.drawable.divider_white))
        mStartOffsetItemDecoration = StartOffsetItemDecoration(resources.getDimensionPixelOffset(R.dimen.start_offset))
        mEndOffsetItemDecoration = EndOffsetItemDecoration(resources.getDimensionPixelOffset(R.dimen.end_offset_16dp))
    }

    private fun initView() {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val v = inflater.inflate(R.layout.view_calendar, this)
        rvCalendar = v.findViewById(R.id.rv_calendar)

    }

    private fun initAdapter() {
        val mLayoutManager = LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false)
        rvCalendar.setLayoutManager(mLayoutManager)

        val list: List<CalendarDate> = ArrayList()

        mCalendarAdapter = CalendarAdapter(mContext, list)

        mCalendarAdapter.setListener(this)

        mDividerItemDecoration?.let { rvCalendar.addItemDecoration(it) }

        rvCalendar.setAdapter(mCalendarAdapter)

        val headersDecor = StickyRecyclerHeadersDecoration(mCalendarAdapter)

        rvCalendar.addItemDecoration(headersDecor)
    }

    /**
     * set dates for each months
     */
    private fun setDefaultDateRange(){
        mCalendarAdapter.addAll(getDateRangeList())
    }

    override fun selectedDate(date: Date?, position: Int) {
        date?.let { mListeners.onClickCalendarItem(it, position) }
    }

}