package com.lakmalz.qtemper.utils

import android.content.res.Resources

class Utils{
    companion object{
        fun dpToPx(dp: Int): Int {
            return (dp * Resources.getSystem().displayMetrics.density).toInt()
        }
    }
}