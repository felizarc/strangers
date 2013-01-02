package com.example.strangers.controler;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.strangers.R;

public class Inscription extends Activity {

	 @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_inscription);
	        
	        //Permet d'autoriser la navigation par l'actionBar avec le up
	        ActionBar actionBar = getActionBar();
	        actionBar.setDisplayHomeAsUpEnabled(true);
	        
	    }

	    
	    //Méthode permettant de capter les évennements de la bar de menu
	    public boolean onOptionsItemSelected(MenuItem item) {
	        switch (item.getItemId()) {

	            case android.R.id.home:
	            	
	            	Intent intent = new Intent(this, Login.class);
	            	intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
	    	    	startActivity(intent);
	    	    	
	                return true;

	            default:
	                return super.onOptionsItemSelected(item);
	        }
	    }

}
