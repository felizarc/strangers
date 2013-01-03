package com.example.strangers.controler;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.example.strangers.R;
import com.example.strangers.model.User;
import com.example.strangers.utilities.ObjectAndString;

public class Inscription extends Activity {

	 @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_inscription);
	        setTitle(R.string.title_activity_inscription);
	        
	        //Permet d'autoriser la navigation par l'actionBar avec le up
	        ActionBar actionBar = getActionBar();
	        actionBar.setDisplayHomeAsUpEnabled(true);
	        
	    }

	    
	    //Mï¿½thode permettant de capter les ï¿½vennements de la bar de menu
	    public boolean onOptionsItemSelected(MenuItem item) {
	        switch (item.getItemId()) {

	            case android.R.id.home:
	    			NavUtils.navigateUpFromSameTask(this);
	            	/*Intent intent = new Intent(this, Login.class);
	            	intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
	    	    	startActivity(intent);*/
	    	    	
	                return true;

	            default:
	                return super.onOptionsItemSelected(item);
	        }
	    }
	    
	    public void subscribe(View v) {
	    	
	    	EditText loginInput = (EditText) findViewById(R.id.loginInput);
			EditText passwordInput1 = (EditText) findViewById(R.id.passwordInput1);
			EditText passwordInput2 = (EditText) findViewById(R.id.passwordInput2);

			//create user
			String login = loginInput.getText().toString();
			String password1 = passwordInput1.getText().toString();
			String password2 = passwordInput2.getText().toString();
			
			if(password1.equals(password2)){
				
				User user = new User(1, login, password1);
				
				//TODO create user in database and load accounts
				
				//store user
				SharedPreferences stockPreferences = getSharedPreferences("strangers", Activity.MODE_PRIVATE);

				Editor editor = stockPreferences.edit();
				editor.putString("registeredUser", ObjectAndString.objectToString(user));
				editor.apply();
				
				//switch to main activity
				Bundle bundle = new Bundle();
				bundle.putParcelable("com.example.strangers.model.User", user);
				Intent intent = new Intent(this, NumberSearch.class);
				intent.putExtra("currentUserBundle", bundle);
		    	startActivity(intent);
			}
			else {				
				passwordInput1.setError("Passwords différents !");
				passwordInput2.setError("Passwords différents !");
			}

			
	    }
	    
}
