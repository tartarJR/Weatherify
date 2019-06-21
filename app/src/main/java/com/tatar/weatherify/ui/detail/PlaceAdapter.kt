package com.tatar.weatherify.ui.detail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tatar.weatherify.R
import com.tatar.weatherify.data.network.model.Place
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.place_weather_item.*
import javax.inject.Inject

class PlaceAdapter @Inject constructor(private val itemClickListener: ItemClickListener) :
    RecyclerView.Adapter<PlaceAdapter.PlaceViewHolder>() {

    private var places: List<Place> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaceViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.place_weather_item, parent, false)
        return PlaceViewHolder(view)
    }

    override fun getItemCount(): Int {
        return places.size
    }

    override fun onBindViewHolder(holder: PlaceViewHolder, position: Int) {
        holder.bind(places[position], itemClickListener)
    }

    fun setPlaces(places: List<Place>) {
        this.places = places
        notifyDataSetChanged()
    }

    inner class PlaceViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),
        LayoutContainer {

        fun bind(place: Place, itemClickListener: ItemClickListener) = with(itemView) {
            val context = containerView.context

            name_tv.text = place.name

            max_temp_tv.text = if (place.tempmax != null) context.getString(
                R.string.max_temp_tv_txt,
                place.tempmax
            ) else context.getString(R.string.max_temp_tv_na_txt)

            min_temp_tv.text = if (place.tempmin != null) context.getString(
                R.string.min_temp_tv_txt,
                place.tempmin
            ) else context.getString(R.string.min_temp_tv_na_txt)

            setOnClickListener { itemClickListener.onItemClick(place) }
        }
    }

    interface ItemClickListener {
        fun onItemClick(place: Place)
    }
}