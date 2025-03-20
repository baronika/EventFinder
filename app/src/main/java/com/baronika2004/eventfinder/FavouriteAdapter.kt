package com.baronika2004.eventfinder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class FavouriteAdapter(private val fList: List<favourites>) :
    RecyclerView.Adapter<FavouriteAdapter.FavouriteViewHolder>(), Filterable {

    private var filteredFavList: List<favourites> = fList

    class FavouriteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val fImageView: ImageView = itemView.findViewById(R.id.image_view_favourite)
        val fTextView: TextView = itemView.findViewById(R.id.text_view_favourite)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavouriteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return FavouriteViewHolder(view)
    }

    override fun getItemCount(): Int {
        return filteredFavList.size
    }

    override fun onBindViewHolder(holder: FavouriteViewHolder, position: Int) {
        val favourite = filteredFavList[position]
        holder.fTextView.text = favourite.fTitle
        holder.fImageView.setImageResource(favourite.fImage)
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(p0: CharSequence?): FilterResults {
                val query = p0?.toString()?.lowercase()?.trim()
                val filteredList = if (query.isNullOrEmpty()) {
                    fList
                } else {
                    fList.filter { it.fTitle.lowercase().contains(query) }
                }

                return FilterResults().apply { values = filteredList }
            }

            override fun publishResults(p0: CharSequence?, results: FilterResults?) {
                filteredFavList = results?.values as? List<favourites> ?: emptyList()
                notifyDataSetChanged()
            }
        }
    }
}
