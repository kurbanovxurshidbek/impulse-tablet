package com.impulse.impulse_driver.fragments

import android.Manifest
import android.annotation.SuppressLint
import android.app.AlertDialog
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
import android.widget.Button
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.location.*
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.firebase.database.FirebaseDatabase
import com.impulse.impulse_driver.adapter.MapsAndInfoAdapter
import com.impulse.impulse_driver.adapter.PageMedicineAdapter
import com.impulse.impulse_driver.database.entity.Medicine
import com.impulse.impulse_driver.databinding.FragmentInfoMapsBinding
import com.impulse.impulse_driver.model.MyLocation
import com.impulse.impulse_driver.model.PatientInfo
import com.impulse.impulse_driver.service.FetchAddressIntentService
import com.impulse.impulse_driver.service.LocationService
import com.impulse.impulse_driver.service.LocationService.CompanionObject.locationArrayList
import com.impulse.impulse_driver.utils.Connections
import com.impulse.impulse_driver.utils.Constants
import com.impulse.impulse_driver.utils.PermissionGPS
import com.impulse.impulse_driver.utils.Util
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File

/**
   * The first fragment to open is a google map and for short information about the patient
   * **/

class MapsInfoFragment : BaseFragment(), GoogleMap.OnMarkerClickListener, GoogleMap.OnMapClickListener,
    OnMapReadyCallback,
    GoogleApiClient.ConnectionCallbacks,
    GoogleApiClient.OnConnectionFailedListener{

    private var fragment: MapsInfoFragment? = null
    private var patientInfo : PatientInfo? = null

    fun newInstance(): MapsInfoFragment?{
        if (fragment == null){
            fragment = MapsInfoFragment()
        }
        return fragment
    }

     companion object {
          private const val MY_FINE_LOCATION_REQUEST = 99
          private const val MY_BACKGROUND_LOCATION_REQUEST = 100
          private val TAG = MapsInfoFragment::class.java.simpleName

          private const val DEFAULT_ZOOM = 2f

          private const val UPDATE_INTERVAL: Long = 500
          private const val FASTEST_UPDATE_INTERVAL = UPDATE_INTERVAL / 5
          private const val LOCATION_PERMISSION_REQUEST_CODE = 1
          private var gpsFirstOn = true

          const val MY_PERMISSIONS_REQUEST_LOCATION = 99
      }

    private var _binding: FragmentInfoMapsBinding? = null
    private val binding get() = _binding!!
    var mLocationService: LocationService = LocationService()
    lateinit var mServiceIntent: Intent
    private lateinit var adapter : MapsAndInfoAdapter

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
        _binding = FragmentInfoMapsBinding.inflate(inflater, container, false)
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

            lottieAnimationClick.setAnimation("click.json")
            lottieAnimationClick.setOnClickListener {
                callLocation()
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

        initRecyclerView()

    }


    private fun initRecyclerView() {
        binding.apply {
            rvPInfo.layoutManager = LinearLayoutManager(requireContext())
            adapter = MapsAndInfoAdapter(requireContext())
            rvPInfo.adapter = adapter
            displaySubscribersList()
        }

    }

    private fun displaySubscribersList() {
        var array = ArrayList<PatientInfo>()
        patientInfo = PatientInfo()
        array.add(PatientInfo(patientInfo?.fullName!!,
            patientInfo?.street!!,patientInfo?.callStatus!!,patientInfo?.phoneNumber!!))
        adapter.setList(array)
        adapter.notifyDataSetChanged()

    }


    /** hence the map functions **/

    private fun starServiceFunc(){
        mLocationService = LocationService()
        mServiceIntent = Intent(requireContext(), mLocationService.javaClass)
        if (!Util.isMyServiceRunning(mLocationService.javaClass, requireActivity())) {
            requireContext().startService(mServiceIntent)

            Toast.makeText(requireContext(), getString(com.impulse.impulse_driver.R.string.service_start_successfully), Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(requireContext(), getString(com.impulse.impulse_driver.R.string.service_already_running), Toast.LENGTH_SHORT).show()
        }
    }

      override fun onDestroy() {
          /*  if (::mServiceIntent.isInitialized) {
                stopService(mServiceIntent)
            }*/
          super.onDestroy()
      }

      @RequiresApi(Build.VERSION_CODES.Q)
      private fun requestBackgroundLocationPermission() {
          ActivityCompat.requestPermissions(requireActivity(),
              arrayOf(Manifest.permission.ACCESS_BACKGROUND_LOCATION), MY_BACKGROUND_LOCATION_REQUEST)
      }

      private fun requestFineLocationPermission() {
          ActivityCompat.requestPermissions(requireActivity(), arrayOf(Manifest.permission.ACCESS_FINE_LOCATION,), MY_FINE_LOCATION_REQUEST)
      }

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
//                        map!!.mapType = GoogleMap.MAP_TYPE_SATELLITE
                        map!!.mapType = GoogleMap.MAP_TYPE_HYBRID
                    } else {
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

            if (ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    if (ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_BACKGROUND_LOCATION)
                        != PackageManager.PERMISSION_GRANTED) {
                        starServiceFunc()
                        requestBackgroundLocationPermission()

                    }else if (ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_BACKGROUND_LOCATION)
                        == PackageManager.PERMISSION_GRANTED){
                        starServiceFunc()
                    }
                }else{
                    starServiceFunc()
                }

            }else if (ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED){
                if (ActivityCompat.shouldShowRequestPermissionRationale(requireActivity(), Manifest.permission.ACCESS_FINE_LOCATION)) {
                    AlertDialog.Builder(requireContext())
                        .setTitle("ACCESS_FINE_LOCATION")
                        .setMessage("Location permission required")
                        .setPositiveButton(
                            "OK"
                        ) { _, _ ->
                            requestFineLocationPermission()
                        }
                        .create()
                        .show()
                } else {
                    requestFineLocationPermission()
                }
            }
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

    override fun onPause() {
        Log.d("MyTime","OnPause")
        super.onPause()
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
            startActivity(intent)
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
        // map default camera
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