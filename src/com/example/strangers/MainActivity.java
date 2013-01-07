package com.example.strangers;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.strangers.controller.Login;
import com.example.strangers.controller.NumberSearch;
import com.example.strangers.model.User;
import com.example.strangers.utilities.ObjectAndString;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.activity_main_layout);
		
		//test si utilisateur d�j� enregistr�
		SharedPreferences stockPreferences = getSharedPreferences("strangers", Activity.MODE_PRIVATE);
		String registeredUserString = stockPreferences.getString("registeredUser", null);
		User registeredUser = (User) ObjectAndString.stringToObject(registeredUserString);
		
		//HashMap<String, ArrayList<String>> smsList = SmsUtilities.getSMS(getApplicationContext());
		//ArrayList<ArrayList<String>> smsList = SmsUtilities.getSMS(getApplicationContext());
		//Log.v("List sms", smsList.toString());
		
		//envoyer sur la page de login
		if(registeredUser == null) {
			Intent intent = new Intent(this, Login.class);
	    	startActivity(intent);
		}
		//envoyer sur la page principale
		else {
			Bundle bundle = new Bundle();
			bundle.putParcelable("com.example.strangers.model.User", registeredUser);
			Intent intent = new Intent(this, NumberSearch.class);
			intent.putExtra("currentUserBundle", bundle);
	    	startActivity(intent);
		}
		
		
		
	}

}
