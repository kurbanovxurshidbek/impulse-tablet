package com.impulse.impulse_driver.fragments

import android.Manifest
import android.R
import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import android.net.Uri
import android.os.*
import android.provider.Settings
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.location.*
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.impulse.impulse_driver.databinding.FragmentReferencePageBinding
import com.impulse.impulse_driver.service.FetchAddressIntentService
import com.impulse.impulse_driver.utils.Connections
import com.impulse.impulse_driver.utils.Constants
import com.impulse.impulse_driver.utils.PermissionGPS
import java.util.ArrayList


class PageReferenceFragment : Fragment(), GoogleMap.OnMarkerClickListener, GoogleMap.OnMapClickListener,
    OnMapReadyCallback,
    GoogleApiClient.ConnectionCallbacks,
    GoogleApiClient.OnConnectionFailedListener{


    companion object {

        private val TAG = PageReferenceFragment::class.java.simpleName

        private const val DEFAULT_ZOOM = 2f

        private const val UPDATE_INTERVAL: Long = 500
        private const val FASTEST_UPDATE_INTERVAL = UPDATE_INTERVAL / 5
        private const val LOCATION_PERMISSION_REQUEST_CODE = 1
        private var gpsFirstOn = true

        const val MY_PERMISSIONS_REQUEST_LOCATION = 99
    }

    private var _binding: FragmentReferencePageBinding? = null
    private val binding get() = _binding!!


    //    belongs to the map
    private val LocationA = LatLng(41.311081, 69.240562)

    private var map: GoogleMap? = null
    private var fusedLocationProvider: FusedLocationProviderClient? = null
    private var locationRequest: LocationRequest? = null
    private var locationCallback: LocationCallback? = null
    private var locationgps: Location? = null
    private var locationgpsS: Location? = null
    private var resultReceiver: ResultReceiver? = null
    private var selectedMarker: Marker? = null
    private var enabled: Boolean? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentReferencePageBinding.inflate(inflater, container, false)
        val view = binding.root

        initViews()

        return view
    }


    private fun initViews() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            checkLocationPermission()
        }

        if (!Connections.checkConnection(requireContext())) {
            Toast.makeText(requireContext(), "", Toast.LENGTH_SHORT).show()
            requireActivity().finish()
        }



        binding.apply {

            /** openContact with your device**/

            var tv_call = _binding!!.tvCall.text.toString()
            openContact.setOnClickListener {
                openCallContact(tv_call)
            }
        }

        init()

        fusedLocationProvider = LocationServices.getFusedLocationProviderClient(requireActivity())
        resultReceiver = object : ResultReceiver(Handler()) {
            override fun onReceiveResult(resultCode: Int, resultData: Bundle) {
                val addressOutput = resultData.getString(Constants.RESULT_DATA_KEY)
                Toast.makeText(requireActivity().applicationContext, addressOutput, Toast.LENGTH_SHORT).show()
            }
        }

        locationgps = Location("Point A")
        locationgpsS = Location("Point B")

    }


    private fun openCallContact(tvCall: String) {
        val callIntent = Intent(Intent.ACTION_DIAL,Uri.parse("tel:"+Uri.encode(tvCall)))
        startActivity(callIntent)
    }

    /** hence the map functions **/

    private fun init() {

        binding.apply {
            fblocations.setOnClickListener {
                try {
                    searchLocation(map!!)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }

            fbsatelits.setOnClickListener {
                if (map != null) {
                    val MapType = map!!.mapType
                    if (MapType == 1) {
                        fbsatelits.setImageResource(android.R.drawable.presence_invisible)
//                        map!!.mapType = GoogleMap.MAP_TYPE_SATELLITE
                        map!!.mapType = GoogleMap.MAP_TYPE_HYBRID
                    } else {
                        fbsatelits.setBackgroundResource(R.drawable.presence_online)
                        map!!.mapType = GoogleMap.MAP_TYPE_NORMAL
                    }
                }
            }

            fbgpss.setOnClickListener {
                getDeviceLocation(false)
                if (!Geocoder.isPresent()) {
                    Toast.makeText(requireContext(),"no_geocoder_available", Toast.LENGTH_SHORT).show()
                } else {
                    showAddress()
                }
            }
        }


        locationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult) {
                if (locationResult.equals(null))
                    return

                for (locationUpdate in locationResult.locations) {
                    locationgps = locationUpdate
                    if (gpsFirstOn) {
                        gpsFirstOn = false
                        getDeviceLocation(true)
                    }
                }
            }
        }

        locationRequest = LocationRequest()
        locationRequest!!.interval = UPDATE_INTERVAL
        locationRequest!!.fastestInterval = FASTEST_UPDATE_INTERVAL
        locationRequest!!.priority = LocationRequest.PRIORITY_HIGH_ACCURACY

        var mapFragment = childFragmentManager.findFragmentById(com.impulse.impulse_driver.R.id.relative) as SupportMapFragment?
        mapFragment?.getMapAsync(this)

    }

    override fun onMapReady(gMap: GoogleMap) {

        getDeviceLocation(false)
        map = gMap

        map!!.setOnMapClickListener(this)
        map!!.setOnMarkerClickListener(this)

        map!!.moveCamera(CameraUpdateFactory.newLatLngZoom(LocationA, DEFAULT_ZOOM))

        map!!.uiSettings.isMapToolbarEnabled = false
        map!!.uiSettings.isMyLocationButtonEnabled = false

        // TODO : location
        map!!.projection.visibleRegion

        if (!checkPermission())
            requestPermission()

        getDeviceLocation(false)
    }

    private fun getDeviceLocation(MyLocation: Boolean) {
        if (!MyLocation)

            if (checkPermission()) {
                if (map != null)
                    map!!.isMyLocationEnabled = true

                val locationResult = fusedLocationProvider!!.lastLocation
                locationResult.addOnCompleteListener(requireActivity()) { task ->
                    if (task.isSuccessful && task.result != null) {
                        if (task != null) {
                            locationgps = task.result
                            val currentLatLng = LatLng(task.result.latitude, task.result.longitude)

                            map!!.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng, 15f))
                        }

                    } else {
                        Log.w(TAG, "getLastLocation:exception", task.exception)
                        Toast.makeText(requireContext(),"no_location_detected", Toast.LENGTH_SHORT).show()
                        val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        startActivity(intent);
                    }
                }
            } else
                Log.d(TAG, "Current location is null. Permission Denied.")
    }

    override fun onMapClick(point: LatLng) {
        selectedMarker = null
    }

    override fun onMarkerClick(marker: Marker): Boolean {
        if (marker == selectedMarker) {
            selectedMarker = null
            return true
        }

        Toast.makeText(requireContext(), marker.title, Toast.LENGTH_SHORT).show()
        selectedMarker = marker
        return false
    }

    private fun showAddress() {
        val intent = Intent(requireContext(), FetchAddressIntentService::class.java)
        intent.putExtra(Constants.RECEIVER, resultReceiver)
        intent.putExtra(Constants.LOCATION_DATA_EXTRA, locationgps)
        requireContext().startService(intent)
    }

    override fun onStart() {
        super.onStart()
        if (Connections.checkConnection(requireContext())) {
            PermissionGPS(requireActivity())

        }
    }


    override fun onResume() {
        super.onResume()
        if (Connections.checkConnection(requireContext())) {
            if (checkPermission())
                fusedLocationProvider!!.requestLocationUpdates(locationRequest!!, locationCallback!!,
                    Looper.myLooper()!!)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        Log.i(TAG, "onRequestPermissionResult")
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            when {
                grantResults.isEmpty() -> Log.i(TAG, "User interaction was cancelled.") // grantResults.length > 0
                grantResults[0] == PackageManager.PERMISSION_GRANTED -> getDeviceLocation(false)
                else -> requestPermission()
            }
        }
    }


    private fun requestPermission() {
        ActivityCompat.requestPermissions(requireActivity(), arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), LOCATION_PERMISSION_REQUEST_CODE)

    }

    private fun checkPermission(): Boolean {
        return ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
    }

    private fun checkLocationPermission(): Boolean {
        if (ContextCompat.checkSelfPermission(requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(requireActivity(),
                    Manifest.permission.ACCESS_FINE_LOCATION)) {

                ActivityCompat.requestPermissions(requireActivity(),
                    arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                    MY_PERMISSIONS_REQUEST_LOCATION)

            } else {
                ActivityCompat.requestPermissions(requireActivity(),
                    arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                    MY_PERMISSIONS_REQUEST_LOCATION)
            }
            return false
        } else {
            return true
        }
    }



    override fun onConnected(p0: Bundle?) {
        if (!enabled!!) {
            val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            startActivity(intent);
            getDeviceLocation(false)
            if (!Geocoder.isPresent()) {
                Toast.makeText(requireContext(),"no_geocoder_available", Toast.LENGTH_SHORT).show()
            } else {
                showAddress()
            }
        }

    }

    @SuppressLint("MissingPermission")
    fun searchLocation(googleMap: GoogleMap) {

        map = googleMap

        // Add a marker in Sydney and move the camera
        val sydney = LatLng(41.335797, 69.221273)
        map!!.addMarker(MarkerOptions().position(sydney).title("Нерафшон-стрит"))
        map!!.animateCamera(CameraUpdateFactory.newLatLngZoom(sydney, 15f))

        val locationResult = fusedLocationProvider!!.lastLocation
        locationResult.addOnCompleteListener(requireActivity()) { task ->
            if (task.isSuccessful && task.result != null) {
                if (task != null) {
                    locationgpsS = task.result
                    val currentLatLng = LatLng(sydney.latitude, sydney.longitude)

                    map!!.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng, 15f))
                }

            } else {
                Log.w(TAG, "getLastLocation:exception", task.exception)
                Toast.makeText(requireContext(),"no_location_detected", Toast.LENGTH_SHORT).show()
                val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(intent);
            }
        }

        val intent = Intent(requireContext(), FetchAddressIntentService::class.java)
        intent.putExtra(Constants.RECEIVER, resultReceiver)
        intent.putExtra(Constants.LOCATION_DATA_EXTRA, locationgpsS)
        requireContext().startService(intent)
    }


    override fun onConnectionSuspended(p0: Int) {

    }

    override fun onConnectionFailed(p0: ConnectionResult) {
    }
}