package com.tatar.weatherify.ui.base

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.tatar.weatherify.R
import kotlinx.android.synthetic.main.view_temperature.view.*

class TemperatureCompoundView : ConstraintLayout {

    private var temperature: Int? = 0

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    init {
        LayoutInflater.from(context).inflate(R.layout.view_temperature, this, true)
    }

    fun setTemperature(temperature: Int?) {
        this.temperature = temperature
        setupView()
    }

    private fun setupView() {
        temperature_tv.text = if (temperature == null) "--" else temperature.toString()
    }
}