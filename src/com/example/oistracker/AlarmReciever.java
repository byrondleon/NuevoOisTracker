package com.example.oistracker;

import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

public class AlarmReciever extends BroadcastReceiver
{

	@Override
	public void onReceive(Context context, Intent intent) 
	{
		
		
		// TODO Auto-generated method stub
		String latitude 	= intent.getStringExtra("latitude");
		String longitude 	= intent.getStringExtra("longitude");
		String idUsuario = "2";
		 
		Toast.makeText(context, ""+latitude + " ----- "+longitude, Toast.LENGTH_LONG).show();
		//jsonclass.registrar(latitude, "longitude", "2");
		//new GetDataTask(latitude, longitude, idUsuario).execute();
		JsonClass jsonclass = new JsonClass();
		jsonclass.registrar(latitude, longitude, idUsuario);
		
	}
	
	private class GetDataTask extends AsyncTask<Void, Void, Void> {
		private String latitude, longitude, idUsuario;
		

		public GetDataTask(String latitude, String longitude, String idUsuario) 
		{
			this.latitude = latitude;
			this.longitude = longitude;
			this.idUsuario = idUsuario;
	 
		}

		@Override
		protected void onPreExecute() {
	 
		}

		@Override
		protected Void doInBackground(Void... params) {
			JsonClass jsonclass = new JsonClass();
			jsonclass.registrar(latitude, longitude, idUsuario);
		return null;
		}

		@Override
		protected void onPostExecute(Void result) {
		 
	 
		 
		super.onPostExecute(result);
		}
	}
	
}

