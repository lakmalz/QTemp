package com.lakmalz.qtemper.adapters

import android.app.Activity
import android.content.Context
import android.graphics.Typeface
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.text.style.RelativeSizeSpan
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import com.lakmalz.qtemper.R
import com.lakmalz.qtemper.models.Job
import com.lakmalz.qtemper.utils.Utils
import com.squareup.picasso.Picasso
import kotlin.math.roundToInt


class JobsAdapter(context: Context, dataList: List<Job>) : RecyclerView.Adapter<JobsAdapter.JobHolder>() {

    private val homeBannerRatio = 2.0f
    private var deviceWidth: Int = 0
    private val displayMetrics = DisplayMetrics()
    private val mContext = context
    private var mDataSet = dataList

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): JobHolder {
        val view = LayoutInflater.from(mContext).inflate(R.layout.item_view_job_list, parent, false)
        (mContext as Activity).windowManager.defaultDisplay.getMetrics(displayMetrics)
        deviceWidth = displayMetrics.widthPixels
        return JobHolder(view)
    }

    override fun getItemCount(): Int {
        return mDataSet.size
    }

    fun addAll(dateList: List<Job>) {
        if (dateList == null) return

        if (dateList.isEmpty()) return

        mDataSet.toMutableList().clear()

        mDataSet = dateList
        notifyDataSetChanged()

    }

    override fun onBindViewHolder(holder: JobHolder, position: Int) {
        try {
            val job = mDataSet[position]

            val imageUrl = job.client?.photos?.get(0)?.formats?.get(0)?.cdnUrl
            val title = "€${job.shifts?.get(0)?.earningsPerHour}/u ${job.title.toString().trim()}  ${job.distance} km"
            val distanceLength = "${job.distance} km".length
            val distanceStart = title.length - distanceLength

            Picasso.with(mContext).load(imageUrl)
                .placeholder(R.drawable.place_holder)
                .error(R.drawable.place_holder)
                .into(holder.imgJobLocation)

            holder.tvTitle.text = spannableString(title, distanceStart, title.length)

            val width = getImageWidth()
            holder.imgJobLocation.getLayoutParams().width = width
            holder.imgJobLocation.getLayoutParams().height = getImageHeight(width)

        } catch (e: Exception) {

        }
    }

    inner class JobHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imgJobLocation: ImageView = itemView.findViewById(com.lakmalz.qtemper.R.id.img_vw_job_location)
        var tvTitle: TextView = itemView.findViewById(com.lakmalz.qtemper.R.id.tv_title)

        /**
         * These values I did not set from API models because I do not have an idea to set properties on API
         * that are hardcoded values
         */
        var ratingBar: RatingBar = itemView.findViewById(com.lakmalz.qtemper.R.id.rating_bar)
        var tvReviews: TextView = itemView.findViewById(com.lakmalz.qtemper.R.id.tv_reviews)
    }

    /**
     * Title set as a spannable text
     * change textColor / textSize / TypeSpace of distance property label
     */
    private fun spannableString(text: String, start: Int, end: Int): SpannableString {
        val spannableString = SpannableString(text)
        spannableString.setSpan(
            android.text.style.StyleSpan(Typeface.NORMAL),
            start,
            end,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        spannableString.setSpan(
            ForegroundColorSpan(ContextCompat.getColor(mContext, R.color.text_light_gray)),
            start,
            end,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        spannableString.setSpan(RelativeSizeSpan(0.85f), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        return spannableString
    }

    /**
     * calculate image width
     */
    private fun getImageWidth(): Int {
        return deviceWidth - Utils.dpToPx(32)
    }

    /**
     * calculate image height
     */
    private fun getImageHeight(width: Int): Int {
        return (width / homeBannerRatio).roundToInt()
    }
}