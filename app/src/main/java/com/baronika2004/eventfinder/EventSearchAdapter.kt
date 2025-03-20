package com.baronika2004.eventfinder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class EventSearchAdapter(private val eList:List<EventSearch>) :
    RecyclerView.Adapter<EventSearchAdapter.EventSearchViewHolder>(),Filterable{

    private var filteredEventSearchList: List<EventSearch> =eList

    class EventSearchViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val eImageView:ImageView=itemView.findViewById(R.id.image_view_event_search)
        val eTextView:TextView=itemView.findViewById(R.id.text_view_event_search)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventSearchViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.event_search_item,parent,false)
        return EventSearchViewHolder(view)
    }

    override fun getItemCount(): Int {
        return filteredEventSearchList.size
    }

    override fun onBindViewHolder(holder: EventSearchViewHolder, position: Int) {
        val eventSearch=filteredEventSearchList[position]
        holder.eTextView.text=eventSearch.eTitle
        Picasso.get().load(eventSearch.eImage).into(holder.eImageView)
//        holder.eImageView.setImageResource(eventSearch.eImage)

    }

    override fun getFilter(): Filter {
        return object :Filter(){
            override fun performFiltering(p0: CharSequence?): FilterResults {
                val query=p0?.toString()?.lowercase()?.trim()
                val filteredList= if(query.isNullOrEmpty()){
                    eList
                }else{
                    eList.filter { it.eTitle.lowercase().contains(query) }
                }
                return FilterResults().apply { values=filteredList }
            }

            override fun publishResults(p0: CharSequence?, results: FilterResults?) {
                filteredEventSearchList=results?.values as? List<EventSearch>?: emptyList()
                notifyDataSetChanged()
            }
        }
    }
}