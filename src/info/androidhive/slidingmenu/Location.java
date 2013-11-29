package info.androidhive.slidingmenu;
 
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.oistracker.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
 
public class Location extends Fragment {
  
	public Location(){}

	  private GoogleMap mapa;
	  View view;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState)
    { 
    	try{
    		
    		view = inflater.inflate(R.layout.mapa, container, false);
            Log.w("IN OnCreate","onCreate Erro View Inflate");
    		
    	}catch(Exception e){
            Log.w("A ocurrido un error", "error en aplicacion"); 
            Intent g= new Intent(getActivity().getApplicationContext(), Mapa.class);    
            startActivity(g); 
    	} 
    	
        Log.w("SUPPORT"," FRAGMENT ERROR");
 	    mapa = ((SupportMapFragment) getActivity().getSupportFragmentManager().findFragmentById(R.id.map)).getMap();
    	
 	    return view;
    }
    
    
    
    public void onDestroyView() {
	   super.onDestroyView(); 
       Log.w("En DESTROY", "IN DESTROYVIEW"); 

	   Fragment fragment = (getFragmentManager().findFragmentById(R.id.map));   
	   FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
	   ft.remove(fragment);
	   ft.commit(); 
	}
  
}