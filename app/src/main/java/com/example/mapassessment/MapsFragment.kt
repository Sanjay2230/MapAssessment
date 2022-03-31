package com.example.mapassessment

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.PolylineOptions


class MapsFragment : Fragment(), OnMapReadyCallback {

    private lateinit var googleMap: GoogleMap
    private lateinit var locationMarkerList: ArrayList<LatLng>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_maps, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeView()
    }

    fun initializeView(){
        locationMarkerList = ArrayList()
        locationMarkerList.add(LatLng(9.9312, 76.2673))
        locationMarkerList.add(LatLng( 11.0168, 76.9558))
        locationMarkerList.add(LatLng(9.9252, 78.1198))
        locationMarkerList.add(LatLng(10.0889, 77.0595))
        locationMarkerList.add(LatLng(9.9312, 76.2673))
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(this)
    }

    override fun onMapReady(map: GoogleMap) {
        googleMap = map
        val sydney = LatLng(9.9312, 76.2673)
        val options = PolylineOptions().width(5f).color(Color.RED).geodesic(true)
        options.addAll(locationMarkerList)
        googleMap.addPolyline(options)
        val builder = LatLngBounds.Builder()
        for (latLng in locationMarkerList) {
            builder.include(latLng)
        }
        val bounds = builder.build()

        //BOUND_PADDING is an int to specify padding of bound.. try 100.

        //BOUND_PADDING is an int to specify padding of bound.. try 100.
        val cu = CameraUpdateFactory.newLatLngBounds(bounds, 5)
        googleMap.animateCamera(cu)

        /*locationMarkerList.forEachIndexed { index, latLng ->
            googleMap.addMarker(MarkerOptions().position(latLng).title("Marker $index"))
        }*/
//        googleMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
//        googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
    }
}