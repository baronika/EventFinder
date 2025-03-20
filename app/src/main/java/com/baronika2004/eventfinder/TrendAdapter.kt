package com.baronika2004.eventfinder

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.baronika2004.eventfinder.activities.ITSActivity
import com.baronika2004.eventfinder.activities.SitarActivity
import com.baronika2004.eventfinder.activities.SufiActivity

class TrendAdapter(private val trendlist:List<Trends>):RecyclerView.Adapter<TrendAdapter.TrendViewHolder>() {
    class TrendViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        val trendImageView: ImageView=itemView.findViewById(R.id.image_view_trend)
        val trendDetailsView:TextView=itemView.findViewById(R.id.text_view_trend_details)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrendViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.trending_item,parent,false)
        return TrendViewHolder(view)
    }

    override fun getItemCount(): Int {
        return trendlist.size
    }

    override fun onBindViewHolder(holder: TrendViewHolder, position: Int) {
        val trends = trendlist[position]
        holder.trendImageView.setImageResource(trends.trendImage)
        holder.trendDetailsView.text = trends.trendDetails

        holder.itemView.setOnClickListener {
            val context = holder.itemView.context
            val intent = when (trends.trendDetails) {
                "India Tech Summit" -> Intent(context, ITSActivity::class.java)
                "Sitar for Mental Health" -> Intent(context, SitarActivity::class.java)
                "Sufi Heritage Festival" -> Intent(context,SufiActivity::class.java)
                else -> null
            }
            intent?.let { context.startActivity(it) }
        }
    }
}