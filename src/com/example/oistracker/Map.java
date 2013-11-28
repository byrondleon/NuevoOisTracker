package com.example.oistracker;

import java.util.GregorianCalendar;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class Map extends FragmentActivity 
{

	// Google Map
	private GoogleMap googleMap;
	View v;
	double latitude, longitude;

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.map);
		setUpMapifNeeded();
		scheduleAlarm(v, latitude, longitude);
	}

	private void setUpMapifNeeded() 
	{
		if(googleMap == null)
		{
			googleMap = ((SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.map)).getMap();
			if(googleMap != null)
			{
				setUpMap();
			}
		} 
	}

	private void setUpMap() 
	{
		googleMap.setMyLocationEnabled(true); 
		
		// Getting location object -> System service LOCATION_SERVICE
		LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE); 
		Criteria criteria = new Criteria();
		String provider = locationManager.getBestProvider(criteria, true);
		
		// Current location
		Location myLocation = locationManager.getLastKnownLocation(provider);
		googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
		
		latitude	= myLocation.getLatitude();
		longitude 	= myLocation.getLongitude();
		
		//parsing Double -> String
		String strLatitude 	= new Double(latitude).toString();
		String strLongitude	= new Double(longitude).toString(); 
	 
		LatLng latLng = new LatLng(latitude, longitude);
  
		googleMap.animateCamera(CameraUpdateFactory.zoomTo(12));
        CameraUpdate camUpd2 = CameraUpdateFactory.newLatLngZoom(new LatLng(latitude, longitude), 12F);
        googleMap.animateCamera(camUpd2); 
	}
	
	public void scheduleAlarm(View V, double latitude2, double longitude2)
    { 
        Long time = new GregorianCalendar().getTimeInMillis()+60*1000;
        Long calendar = new GregorianCalendar().getTimeInMillis(); 
        
        Intent intentAlarm = new Intent(this, AlarmReciever.class);  
        Bundle bundle = new Bundle();
        bundle.putString("latitude", latitude+"");
        bundle.putString("longitude", longitude+"");
        intentAlarm.putExtras(bundle); 
        
        
        // create the object
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 1, intentAlarm, PendingIntent.FLAG_UPDATE_CURRENT);
        //set the alarm for particular time
        //alarmManager.set(AlarmManager.RTC_WAKEUP,time, PendingIntent.getBroadcast(this, 1, intentAlarm, PendingIntent.FLAG_UPDATE_CURRENT));
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar, 35000, pendingIntent);
        Toast.makeText(this, "Tracking ...", Toast.LENGTH_LONG).show(); 
    } 
}