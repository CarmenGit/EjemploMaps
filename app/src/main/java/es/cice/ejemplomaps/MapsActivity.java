package es.cice.ejemplomaps;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import es.cice.ejemplomaps.adapters.infoAdapter;

//el mapa lo mete en un fragmento

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Marker marcador;
    private ImageView fotoIV;
    private TextView descripcionTX;
    private TextView posicion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {

            }
        });


        infoAdapter adapter = new infoAdapter(this);
        mMap.setInfoWindowAdapter(adapter);

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        //el marker es el globo rojo
        Marker marker=mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));


        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

        //para cambiar el tipo de mapa
        mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);


        //activar botones de zoom
        mMap.getUiSettings().setZoomControlsEnabled(true);

        LatLng madrid = new LatLng(40.417325, -3.68308);
        CameraPosition camPos=new CameraPosition.Builder().target(madrid).zoom(15).bearing(45).tilt(70).build();

        CameraUpdate camUpd=CameraUpdateFactory.newCameraPosition(camPos);


        //googleMap.animateCamera(camUpd);
        //mMap.addMarker(new MarkerOptions().position())
        googleMap.moveCamera(camUpd);
        googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                Marker marker =mMap.addMarker(new MarkerOptions().position(latLng).title(""+ latLng.latitude + latLng.longitude).snippet("esto es el snippet"));
                marker.setDraggable(true);

            }
        });


       /* mMap.setOnMarkerDragListener(new GoogleMap.OnMarkerDragListener() {
            @Override
            public void onMarkerDragStart(Marker marker) {

            }

            @Override
            public void onMarkerDrag(Marker marker) {

            }

            @Override
            public void onMarkerDragEnd(Marker marker) {

                //marker.setIcon(BitmapDescriptorFactory.fromFile("ic_launcher.png"));
                marker.setIcon(BitmapDescriptorFactory.fromResource(R.mipmap.gumball));

            }
        });
        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                                          @Override
                                          public boolean onMarkerClick(Marker marker) {
                                              marker.remove();
                                              return true;
                                          }
                                      }
        );*/

    }

}
