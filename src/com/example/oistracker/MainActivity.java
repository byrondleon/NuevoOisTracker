package com.example.oistracker;

import com.example.oistracker.R;
import info.androidhive.slidingmenu.Mapa;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
 
public class MainActivity extends Activity
{
	Button btnLogin;
	
  @Override
  public void onCreate(Bundle savedInstanceState) 
  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        btnLogin = (Button) findViewById(R.id.btnLogin);
        
        btnLogin.setOnClickListener(new Button.OnClickListener()
        { 
	    	@Override
	    	 public void onClick(View v) { 
	    		Intent i = new Intent(getApplicationContext(),  Mapa.class);
	    		startActivity(i);  
	    	 }
        });		
  }
}