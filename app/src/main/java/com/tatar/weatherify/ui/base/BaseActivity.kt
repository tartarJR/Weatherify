package com.tatar.weatherify.ui.base

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.tatar.weatherify.R

abstract class BaseActivity : AppCompatActivity(), BaseMvpView {

    private var progressBar: ProgressBar? = null
    private var statusTextView: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())

        // TODO is it necessary or possible to eliminate findViewById here
        progressBar = findViewById(R.id.progress_bar)
        statusTextView = findViewById(R.id.status_tv)

        provideDependencies()
        initViews()
        init()
    }

    override fun onDestroy() {
        super.onDestroy()
        releasePresenterResources()
    }

    protected abstract fun getLayoutId(): Int
    protected abstract fun provideDependencies()
    protected abstract fun initViews()
    protected abstract fun init()
    protected abstract fun releasePresenterResources()

    override fun showProgressBar() {
        progressBar?.visibility = View.VISIBLE
    }

    override fun hideProgressBar() {
        progressBar?.visibility = View.GONE
    }

    override fun showStatusTv() {
        statusTextView?.visibility = View.VISIBLE
    }

    override fun hideStatusTv() {
        statusTextView?.visibility = View.GONE
    }

    override fun displayErrorMessage() {
        statusTextView?.text = getString(R.string.error_txt)
    }

    override fun displayNoInternetWarning() {
        statusTextView?.text = getString(R.string.no_internet_connection_txt)
    }
}