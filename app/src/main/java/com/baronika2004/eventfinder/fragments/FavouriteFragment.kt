package com.baronika2004.eventfinder.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView.OnQueryTextListener
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.baronika2004.eventfinder.FavouriteAdapter
import com.baronika2004.eventfinder.R
import com.baronika2004.eventfinder.databinding.FragmentFavouriteBinding
import com.baronika2004.eventfinder.favourites

class FavouriteFragment : Fragment() {

    private lateinit var fRecyclerView: RecyclerView
    private lateinit var fAdapter: FavouriteAdapter

    private val favList= listOf(
        favourites(R.drawable.sitar2_1,"Sitar for Mental Health"),
        favourites(R.drawable.sufi11,"Sufi Heritage Festival"),
        favourites(R.drawable.femalecard,"Singing Contest"),
        favourites(R.drawable.noidacomedy_1,"Noida Comedy Carnival"),
        favourites(R.drawable.https___cdn_evbuc_com_images_852198579_272798929277_1_original,"India Tech Summit")
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_favourite,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val searchView=view.findViewById<SearchView>(R.id.search_viewf)
        fRecyclerView=view.findViewById(R.id.recyclerViewFavourite)
        fAdapter= FavouriteAdapter(favList)
        fRecyclerView.layoutManager=LinearLayoutManager(requireContext())
        fRecyclerView.adapter=fAdapter

        searchView.setOnQueryTextListener(object :SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                fAdapter.getFilter().filter(query)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                fAdapter.getFilter().filter(newText)
                return true
            }

        })

    }
}