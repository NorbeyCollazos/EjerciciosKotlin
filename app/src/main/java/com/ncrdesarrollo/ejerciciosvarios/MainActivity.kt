package com.ncrdesarrollo.ejerciciosvarios

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*

import com.ncrdesarrollo.ejerciciosvarios.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var binding: ActivityMainBinding


    private lateinit var map: GoogleMap

    companion object {
        const val REQUEST_CODE_LOCATION = 0
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        //Thread.sleep(2000) //esto sirve para retardar
        setTheme(R.style.Theme_EjerciciosVarios)//se debe colocar para que tome el theme principal
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        createMapFragment()

    }

    private fun createMapFragment() {
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.fragmentMap) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap?) {
        map = googleMap!!
        createMarker()
        enableMyLocation()//para la geolocalización
        createPolylines()//lama la funcion de polilineas
    }

    private fun createMarker() {
        val favoritePlace = LatLng(40.419173113350965,-3.705976009368897)
        map.addMarker(MarkerOptions().position(favoritePlace).title("El balcón del Huila"))
        map.animateCamera(
            CameraUpdateFactory.newLatLngZoom(favoritePlace, 18f),
            2000,
            null
        )
    }

    //funcion para polilineas
    private fun createPolylines(){
        val polylineOptions = PolylineOptions()
            .add(LatLng(40.419173113350965,-3.705976009368897))
            .add(LatLng( 40.4150807746539, -3.706072568893432))
            .add(LatLng( 40.41517062907432, -3.7012016773223873))
            .add(LatLng( 40.41713105928677, -3.7037122249603267))
            .add(LatLng( 40.41926296230622,  -3.701287508010864))
            .add(LatLng( 40.419173113350965, -3.7048280239105225))
            //.add(LatLng(40.419173113350965,-3.705976009368897))

            .width(30f)//para el ancho de la linea
            .color(ContextCompat.getColor(this, R.color.purple_500))//para el color de la linea

        val polyline = map.addPolyline(polylineOptions)
        polyline.startCap = RoundCap()//redondea la punta inicial
        polyline.endCap = RoundCap()//redondea la punta final
        //polyline.endCap = CustomCap(BitmapDescriptorFactory.fromResource(R.drawable.hexagram)) //coloca imagen en la punta final
    }



    //se verifica si estan dados los permisos de ubicación
    private fun isPermissionsGranted() = ContextCompat.checkSelfPermission(
        this,
        Manifest.permission.ACCESS_FINE_LOCATION
    ) == PackageManager.PERMISSION_GRANTED

    private fun enableMyLocation() {
        if (!::map.isInitialized) return //se verifica si el mapa ya se cargo
        if (isPermissionsGranted()) {
            map.isMyLocationEnabled = true //se inicializa la ubicación en tiempo real
        } else {
            requestLocationPermission()
        }
    }


    //metodo para requerir los permisos de localización
    private fun requestLocationPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                Manifest.permission.ACCESS_FINE_LOCATION)) {
            Toast.makeText(this, "Ve a ajustes y acepta los permisos", Toast.LENGTH_SHORT).show()
        } else {
            ActivityCompat.requestPermissions(this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                REQUEST_CODE_LOCATION)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            REQUEST_CODE_LOCATION -> if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                map.isMyLocationEnabled = true
            } else {
                Toast.makeText(
                    this,
                    "Para activar la localización ve a ajustes y acepta los permisos",
                    Toast.LENGTH_SHORT
                ).show()
            }
            else -> {}
        }
    }

    //se sobreecribe este metodo para en caso de que el usuario salga de la pantalla
    // y desactive los permisos cuando vuelva se los pida
    override fun onResumeFragments() {
        super.onResumeFragments()
        if (!::map.isInitialized) return
        if(!isPermissionsGranted()){
            map.isMyLocationEnabled = false
            Toast.makeText(this, "Para activar la localización ve a ajustes y acepta los permisos", Toast.LENGTH_SHORT).show()
        }
    }


}