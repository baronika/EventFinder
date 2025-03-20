package com.baronika2004.eventfinder

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.baronika2004.eventfinder.activities.ComedyNoidaActivity
import com.baronika2004.eventfinder.activities.SufiActivity
import com.baronika2004.eventfinder.fragments.EventSearchFragment

class CategoryAdapter(private val cList: List<Categories>):RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    class CategoryViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        val cImageView:ImageView=itemView.findViewById(R.id.image_view_category)
        val cTitleView:TextView=itemView.findViewById(R.id.text_view_ctitle)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.category_item,parent,false)
        return CategoryViewHolder(view)
    }

    override fun getItemCount(): Int {
        return cList.size
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val category=cList[position]
        holder.cImageView.setImageResource(category.cImage)
        holder.cTitleView.text=category.cTitle

        holder.itemView.setOnClickListener {
            val context=holder.itemView.context
            val intent=when(category.cTitle){
                "Music" ->Intent(context,SufiActivity::class.java)
                "Comedy" ->Intent(context,ComedyNoidaActivity::class.java)
                else ->null
            }
            intent?.let { context.startActivity(it) }
        }
    }
}