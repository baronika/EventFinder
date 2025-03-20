package com.baronika2004.eventfinder.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.baronika2004.eventfinder.Categories
import com.baronika2004.eventfinder.CategoryAdapter
import com.baronika2004.eventfinder.R
import com.baronika2004.eventfinder.TrendAdapter
import com.baronika2004.eventfinder.Trends
import com.baronika2004.eventfinder.databinding.FragmentHomeBinding

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class HomeFragment : Fragment() {

    private lateinit var tRecyclerView: RecyclerView
    private lateinit var tAdapter: TrendAdapter

    private lateinit var cRecyclerView: RecyclerView
    private lateinit var cAadapter: CategoryAdapter

    private val trendsList= listOf(
        Trends(R.drawable._480536,"Metaverse"),
        Trends(R.drawable.screenshot_2025_03_04_at_10_19_51am,"Sitar for Mental Health"),
        Trends(R.drawable.sufi,"Sufi Heritage Festival"),
        Trends(R.drawable.https___cdn_evbuc_com_images_852198579_272798929277_1_original,"India Tech Summit"),
        Trends(R.drawable.noidacomedy," "),
        Trends(R.drawable.flat_people_vr_goggles_ai_robot_metaverse_solve_problem_data_privacy_88138_1611,"Flat People"),
        Trends(R.drawable._50879662_10521037,"Singing"),
        Trends(R.drawable._388486,"Dancing")
    )

    private val categoryList= listOf(
        Categories(R.drawable.female_singer_portrait_isolated_blue_studio_wall_neon_light,"Music"),
        Categories(R.drawable.process_creating_stand_up_comedy__1_,"Comedy"),
        Categories(R.drawable._923396_3079006,"Winter")
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view=inflater.inflate(R.layout.fragment_home, container, false)

        tRecyclerView=view.findViewById(R.id.recyclerViewTrends)
        tRecyclerView.layoutManager=LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
        tAdapter= TrendAdapter(trendsList)
        tRecyclerView.adapter=tAdapter

        cRecyclerView=view.findViewById(R.id.recyclerViewCategories)
        cRecyclerView.layoutManager=LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
        cAadapter=CategoryAdapter(categoryList)
        cRecyclerView.adapter=cAadapter

        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val searchBar=view.findViewById<com.google.android.material.search.SearchBar>(R.id.search_bar)
        searchBar.setOnClickListener {
            val searchEventFragment=EventSearchFragment()
            val transaction=parentFragmentManager.beginTransaction()
            transaction.replace(R.id.frame_layout,searchEventFragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }
    }
}