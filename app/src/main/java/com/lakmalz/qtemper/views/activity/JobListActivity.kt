package com.lakmalz.qtemper.views.activity

import android.os.Bundle
import android.support.v4.content.ContextCompat.getDrawable
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.dgreenhalgh.android.simpleitemdecoration.linear.DividerItemDecoration
import com.dgreenhalgh.android.simpleitemdecoration.linear.EndOffsetItemDecoration
import com.dgreenhalgh.android.simpleitemdecoration.linear.StartOffsetItemDecoration
import com.lakmalz.qtemper.App
import com.lakmalz.qtemper.R
import com.lakmalz.qtemper.adapters.JobsAdapter
import com.lakmalz.qtemper.data.api.response.JobResponse
import com.lakmalz.qtemper.models.Job
import com.lakmalz.qtemper.utils.Constant
import com.lakmalz.qtemper.utils.calendarview.HorizontalCalendar
import com.lakmalz.qtemper.utils.calendarview.HorizontalCalendarListener
import com.lakmalz.qtemper.views.base.BaseActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class JobListActivity : BaseActivity(), HorizontalCalendarListener {

    private lateinit var jobsAdapter: JobsAdapter
    private var jobViewModel = App.injectJobViewModel()
    private val dateFormator  = SimpleDateFormat(Constant.YYYYMMDD)

    private var mDividerItemDecoration: RecyclerView.ItemDecoration? = null
    private var mStartOffsetItemDecoration: RecyclerView.ItemDecoration? = null
    private var mEndOffsetItemDecoration: RecyclerView.ItemDecoration? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var currentDate = dateFormator.format(Calendar.getInstance().time)
        setCalendarView()
        setJobAdapter()
        getJobList(currentDate)
    }

    /**
     *  set horizontal calendar view
     */
    private fun setCalendarView() {
        val horizontalCalendar = HorizontalCalendar(this)
        vw_calendar_container.addView(horizontalCalendar)
    }

    /**
     * Calendar date select event
     */
    override fun onClickCalendarItem(date: Date, position: Int) {
        getJobList(dateFormator.format(date))
    }

    /**
     * get job list from API
     */
    private fun getJobList(selectedDate: String) {
        vw_empty_container.visibility = View.GONE
        showProgress()
        subscribe(jobViewModel.getJobList(selectedDate)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    setData(it?.jobList)
                    dismissProgress()
                }, {
                    showError(it)
                    dismissProgress()
                })
        )
    }

    /**
     * data binding
     */
    private fun setData(jobList: JobResponse?) {

        rv_job_list.visibility = View.VISIBLE
        if (jobList?.data?.jobList?.isEmpty()!!) {
            vw_empty_container.visibility = View.VISIBLE
            rv_job_list.visibility = View.GONE
        }

        jobList?.data?.jobList?.let { jobsAdapter.addAll(it) }
    }

    /**
     * show error
     */
    private fun showError(it: Throwable) {

    }

    /**
     * set adapter's decorators
     */
    private fun setDecorations() {
        mDividerItemDecoration = DividerItemDecoration(
            getDrawable(
                this,
                R.drawable.divider_white_16dp
            )
        )
        mStartOffsetItemDecoration = StartOffsetItemDecoration(resources.getDimensionPixelOffset(R.dimen.start_offset))
        mEndOffsetItemDecoration = EndOffsetItemDecoration(resources.getDimensionPixelOffset(R.dimen.end_offset_16dp))
    }

    /**
     * set Job adapter
     */
    private fun setJobAdapter() {
        setDecorations()

        val jobList: MutableList<Job> = ArrayList()

        jobsAdapter = JobsAdapter(this, jobList)
        mDividerItemDecoration?.let { rv_job_list.addItemDecoration(it) }
        mStartOffsetItemDecoration?.let { rv_job_list.addItemDecoration(it) }
        mEndOffsetItemDecoration?.let { rv_job_list.addItemDecoration(it) }
        rv_job_list.adapter = jobsAdapter
        rv_job_list.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rv_job_list.setHasFixedSize(true)
    }
}
