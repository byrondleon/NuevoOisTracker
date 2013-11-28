package info.androidhive.slidingmenu;


import com.example.oistracker.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.SupportMapFragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Location extends Fragment {

	private GoogleMap mapa;
	 public View onCreateView(LayoutInflater inflater, ViewGroup container,
             Bundle savedInstanceState) {

		 super.onCreateView(inflater, container, savedInstanceState);
		    View view = inflater.inflate(R.layout.mapa, container, false);

		    mapa = ((MapFragment) getFragmentManager().findFragmentById(
	                 R.id.map)).getMap();
            setupGmap();
		    return view;
	 
	     }
	private void setupGmap() {
		// TODO Auto-generated method stub
	     mapa.setMapType(GoogleMap.MAP_TYPE_SATELLITE);

	}
	    	    
	    	     

	

}
