package com.baronika2004.eventfinder.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.baronika2004.eventfinder.EventSearch
import com.baronika2004.eventfinder.EventSearchAdapter
import com.baronika2004.eventfinder.R

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class EventSearchFragment : Fragment() {

    private lateinit var eRecyclerView: RecyclerView
    private lateinit var eAdapter:EventSearchAdapter
    private lateinit var searchView: SearchView

    private val eventList= listOf(
        EventSearch(R.drawable.sitar2_1,"Sitar for Mental Health"),
        EventSearch(R.drawable.sufi11,"Sufi Heritage Festival"),
        EventSearch(R.drawable.femalecard,"Singing Contest"),
        EventSearch(R.drawable.noidacomedy_1,"Noida Comedy Carnival"),
        EventSearch(R.drawable.https___cdn_evbuc_com_images_852198579_272798929277_1_original,"India Tech Summit")
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_event_search, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        eRecyclerView=view.findViewById(R.id.recyclerViewEventSearch)
        eAdapter=EventSearchAdapter(eventList)
        eRecyclerView.layoutManager= LinearLayoutManager(requireContext())
        eRecyclerView.adapter=eAdapter

        searchView=view.findViewById(R.id.search_viewe)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                eAdapter.filter.filter(query)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                eAdapter.filter.filter(newText)
                return true
            }
        })

//        fetchEventData()
    }

//    private fun fetchEventData() {
//        val apiService = Retrofit.Builder()
//            .baseUrl("https://eventbrite-events-data.p.rapidapi.com/")
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//            .create(ApiInterface::class.java)
//
//        val eventUrl = "https://www.eventbrite.com/e/marketing-essentials-1-day-training-in-new-york-city-ny-tickets-814175078577"
//
//        val retrofitData = apiService.getData(eventUrl)
//        retrofitData.enqueue(object : Callback<MyData?> {
//            override fun onResponse(call: Call<MyData?>, response: Response<MyData?>) {
//                val dataList = response.body()?.events
//                Log.e("Tag on response", "onResponse: $dataList")
//            }
//
//            override fun onFailure(call: Call<MyData?>, t: Throwable) {
//                t.printStackTrace()
//            }
//        })
//    }

}
