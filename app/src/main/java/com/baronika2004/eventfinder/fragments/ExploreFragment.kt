package com.baronika2004.eventfinder.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.baronika2004.eventfinder.R
import com.google.android.gms.common.api.Status
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.api.net.PlacesClient
import com.google.android.libraries.places.widget.AutocompleteSupportFragment
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener

class ExploreFragment : Fragment(), OnMapReadyCallback {

    private var googleMap: GoogleMap? = null
    private var autocompleteFragment: AutocompleteSupportFragment? = null
    private lateinit var placesClient: PlacesClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize Places API
        if (!Places.isInitialized()) {
            Places.initialize(requireContext(), "AIzaSyDGuSiRwYwmwXozXNUNFKXTklVRhYQbFEw")
        }
        placesClient = Places.createClient(requireContext()) // Initialize Places Client
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout first
        val view = inflater.inflate(R.layout.fragment_explore, container, false)

        // Initialize the map
        val mapFragment =
            childFragmentManager.findFragmentById(R.id.fragment_map) as? SupportMapFragment
        mapFragment?.getMapAsync(this)

        // Initialize Autocomplete Fragment
        autocompleteFragment =
            childFragmentManager.findFragmentById(R.id.autoComplete_map) as? AutocompleteSupportFragment

        autocompleteFragment?.setPlaceFields(
            listOf(Place.Field.ID, Place.Field.NAME, Place.Field.LAT_LNG)
        )

        autocompleteFragment?.setOnPlaceSelectedListener(object : PlaceSelectionListener {
            override fun onPlaceSelected(place: Place) {
                place.latLng?.let { latLng ->
                    Toast.makeText(requireContext(), latLng.toString(), Toast.LENGTH_SHORT).show()
                    googleMap?.apply {
                        clear()
                        addMarker(MarkerOptions().position(latLng).title(place.name))
                        moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 12f))
                    }
                }
            }

            override fun onError(status: Status) {
                Log.e("Error", status.statusMessage ?: "Unknown error")
            }
        })

        return view
    }

    override fun onMapReady(map: GoogleMap) {
        googleMap = map
        val location = LatLng(22.5744, 88.3629)
        googleMap?.addMarker(MarkerOptions().position(location).title("Kolkata"))
        googleMap?.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 12f))
    }
}


