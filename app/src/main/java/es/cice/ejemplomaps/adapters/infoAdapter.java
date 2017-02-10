package es.cice.ejemplomaps.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

import es.cice.ejemplomaps.R;

/**
 * Created by cice on 10/2/17.
 */

public class infoAdapter implements GoogleMap.InfoWindowAdapter {

    //se necesita para inflar el xml
    Activity activity;

    public infoAdapter(Activity activity) {
        this.activity = activity;
    }

    @Override
    public View getInfoWindow(Marker marker) {
        LayoutInflater inflater=LayoutInflater.from(activity);
        View row=inflater.inflate(R.layout.info_marker, null);
        ImageView fotoIV=(ImageView) row.findViewById(R.id.fotoIV);
        TextView descripcionTX=(TextView) row.findViewById(R.id.descripcionTX);
        TextView posicionTX=(TextView) row.findViewById(R.id.posicionTX);
        fotoIV.setImageResource(R.mipmap.gumball);
        descripcionTX.setText("Estamos en el planeta Android");
        String posicion=marker.getPosition().latitude + "-" + marker.getPosition().longitude;
        posicionTX.setText(posicion);
        return row;

    }

    @Override
    public View getInfoContents(Marker marker) {
        return null;
    }


}
