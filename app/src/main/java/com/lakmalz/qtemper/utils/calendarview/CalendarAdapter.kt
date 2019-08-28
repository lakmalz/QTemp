package com.lakmalz.qtemper.utils.calendarview

import android.content.Context
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import com.lakmalz.qtemper.R
import com.timehop.stickyheadersrecyclerview.StickyRecyclerHeadersAdapter

/**
 * Created by Lakmal Weerasekara (Lakmalz) on 7/20/2019.
 */

class CalendarAdapter(context: Context, dateList: List<CalendarDate>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>(), StickyRecyclerHeadersAdapter<RecyclerView.ViewHolder> {


    lateinit var mListeners: CalendarAdapterListeners
    private var month: TextView? = null
    var isDateSelected: Boolean = true
    val mContext = context
    var mDataSet = dateList

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(mContext).inflate(R.layout.item_view_calendar, parent, false)
        return DateViewHolder(view)
    }

    fun setListener(listeners: CalendarAdapterListeners) {
        this.mListeners = listeners
    }

    override fun getItemCount(): Int {
        return mDataSet.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val v = holder as DateViewHolder
        val selectableItem = mDataSet[position]
        v.dateBackground.setBackgroundColor(ContextCompat.getColor(mContext, R.color.calendar_bg))

        v.txtDay.setTextColor(ContextCompat.getColor(mContext, R.color.calendar_txt))
        v.txtDate.setTextColor(ContextCompat.getColor(mContext, R.color.calendar_txt))
        v.txtMonth.setTextColor(ContextCompat.getColor(mContext, R.color.calendar_txt))

        v.txtDay.text = selectableItem.day
        v.txtDate.text = selectableItem.date
        v.txtMonth.text = selectableItem.monthType.name.replace("-", "")


        if (selectableItem.isSelected) {
            v.dateBackground.setBackgroundColor(ContextCompat.getColor(mContext, R.color.calendar_bg_selected))
            v.txtDay.setTextColor(ContextCompat.getColor(mContext, R.color.app_white))
            v.txtDate.setTextColor(ContextCompat.getColor(mContext, R.color.app_white))
            v.txtMonth.setTextColor(ContextCompat.getColor(mContext, R.color.app_white))
        }
    }

    override fun getHeaderId(position: Int): Long {

        if (mDataSet[position].monthType.name.length <= 1) {
            return mDataSet[position].monthType.name[0].toLong()
        } else if (mDataSet[position].monthType.name.length <= 2) {
            return mDataSet[position].monthType.name[1].toLong()

        } else if (mDataSet[position].monthType.name.length <= 3) {
            return mDataSet[position].monthType.name[2].toLong()

        } else if (mDataSet[position].monthType.name.length <= 4) {
            return mDataSet[position].monthType.name[3].toLong()

        } else if (mDataSet[position].monthType.name.length <= 5) {
            return mDataSet[position].monthType.name[4].toLong()

        } else if (mDataSet[position].monthType.name.length <= 6) {
            return mDataSet[position].monthType.name[5].toLong()

        } else if (mDataSet[position].monthType.name.length <= 7) {
            return mDataSet[position].monthType.name[6].toLong()

        } else if (mDataSet[position].monthType.name.length <= 8) {
            return mDataSet[position].monthType.name[7].toLong()

        } else if (mDataSet[position].monthType.name.length <= 9) {
            return mDataSet[position].monthType.name[8].toLong()

        } else if (mDataSet[position].monthType.name.length <= 10) {
            return mDataSet[position].monthType.name[9].toLong()

        } else if (mDataSet[position].monthType.name.length <= 11) {
            return mDataSet[position].monthType.name[10].toLong()

        } else if (mDataSet[position].monthType.name.length <= 12) {
            return mDataSet[position].monthType.name[11].toLong()
        } else {
            return mDataSet[position].monthType.name[0].toLong()
        }
    }

    override fun onCreateHeaderViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.view_header, parent, false)
        month = v.findViewById<TextView>(R.id.txt_header)
        return object : RecyclerView.ViewHolder(v) {}
    }

    override fun onBindHeaderViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        month?.text = mDataSet.get(position).monthType.name.substring(0, 3)
    }

    fun addAll(dateList: List<CalendarDate>) {
        if (dateList == null) return

        if (dateList.size == 0) return

        mDataSet.toMutableList().clear()

        mDataSet = dateList
        notifyDataSetChanged()

    }

    inner class DateViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        val txtDay: TextView = itemView.findViewById(R.id.txt_day)
        val txtDate: TextView = itemView.findViewById(R.id.txt_date)
        val txtMonth: TextView = itemView.findViewById(R.id.txt_month)
        val dateBackground: RelativeLayout = itemView.findViewById(R.id.date_background)

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            try {

                if (adapterPosition != RecyclerView.NO_POSITION) {
                    isDateSelected = true

                    if (mDataSet[adapterPosition].isSelected) return

                    resetItems()

                    val date = mDataSet[adapterPosition]

                    mDataSet[adapterPosition].isSelected = true

                    mListeners.selectedDate(date.selectedDate, adapterPosition)

                    notifyDataSetChanged()
                }

            } catch (e: Exception) {

            }
        }

        /**
         * All value deselect in calendar
         */
        private fun resetItems() {

            for (i in 0..mDataSet.size) {

                if(i==mDataSet.size) return

                mDataSet[i].isSelected = false
            }
        }

    }

}