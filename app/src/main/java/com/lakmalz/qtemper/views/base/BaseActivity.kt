package com.lakmalz.qtemper.views.base

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.support.v7.app.AppCompatActivity
import com.lakmalz.qtemper.R
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

open class BaseActivity : AppCompatActivity() {

    private var mProgress: Dialog? = null
    private val mSubscriptions = CompositeDisposable()

    fun subscribe(disposable: Disposable): Disposable {
        mSubscriptions.add(disposable)
        return disposable
    }

    override fun onStop() {
        super.onStop()
        mSubscriptions.clear()
    }

    fun showProgress() {
        try {
            if (mProgress == null) {
                mProgress = Dialog(this, R.style.Progressbar)
                mProgress!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                mProgress!!.setCancelable(false)
                mProgress!!.show()
                mProgress!!.setContentView(R.layout.dialog_progress_spinner)
            }

            if (!mProgress!!.isShowing) {
                mProgress!!.show()
            }
        } catch (e: Exception) {

        }
    }

    fun dismissProgress() {
        try {
            if (mProgress != null) {
                if (mProgress!!.isShowing) {
                    mProgress!!.dismiss()
                    mProgress = null
                }
            }
        } catch (_e: Exception) {

        }

    }
}