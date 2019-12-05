package com.tatar.weatherify.ui.detail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tatar.weatherify.R
import com.tatar.weatherify.data.network.model.Wind
import com.tatar.weatherify.di.PerActivity
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.wind_adapter_item.*
import javax.inject.Inject

@PerActivity
class WindAdapter @Inject constructor() : RecyclerView.Adapter<WindAdapter.PlaceViewHolder>() {

    private var winds: List<Wind> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaceViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.wind_adapter_item, parent, false)
        return PlaceViewHolder(view)
    }

    override fun getItemCount(): Int {
        return winds.size
    }

    override fun onBindViewHolder(holder: PlaceViewHolder, position: Int) {
        holder.bind(winds[position])
    }

    fun setPlaces(winds: List<Wind>) {
        this.winds = winds
        notifyDataSetChanged()
    }

    inner class PlaceViewHolder(override val containerView: View) :
        RecyclerView.ViewHolder(containerView),
        LayoutContainer {

        fun bind(wind: Wind) = with(itemView) {
            name_tv.text = wind.name
            direction_tv.text = containerView.context.getString(R.string.direction_tv_txt, wind.direction)
            max_speed_tv.text = containerView.context.getString(R.string.max_speed_tv_txt, wind.speedMax)
            min_speed_tv.text = containerView.context.getString(R.string.min_speed_tv_txt, wind.speedMin)
        }
    }
}