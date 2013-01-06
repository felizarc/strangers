package com.example.strangers.controller;

import java.util.concurrent.ExecutionException;

import org.apache.http.HttpStatus;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.strangers.R;
import com.example.strangers.model.User;
import com.example.strangers.tasks.TaskNewUser;
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

	    
	    //M�thode permettant de capter les �vennements de la bar de menu
	    public boolean onOptionsItemSelected(MenuItem item) {
	        switch (item.getItemId()) {

	            case android.R.id.home:
	    			//NavUtils.navigateUpFromSameTask(this);
	            	Intent intent = new Intent(this, Login.class);
	            	intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
	    	    	startActivity(intent);
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
				
				
				//Pr�paration et appel du thread d'inscription
				Object params[] = {getApplicationContext(), login, password1};
		    	
		    	TaskNewUser taskNewUser = new TaskNewUser(this);
		    	taskNewUser.execute(params);
		    	
		    	Integer status = null;
				try {
					status = taskNewUser.get();
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (ExecutionException e) {
					e.printStackTrace();
				}
				
				if(status != null && status == HttpStatus.SC_CREATED) {
					
					//TODO get the right id number if it's usefull or delete id for user class
					User user = new User(login, password1);
										
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
					int duration = Toast.LENGTH_SHORT;
					Toast toastError = Toast.makeText(getApplicationContext(), getApplicationContext().getString(R.string.user_creation_error), duration);
					toastError.show();
				}
			}
			else {				
				passwordInput1.setError(getApplicationContext().getString(R.string.different_passwords));
				passwordInput2.setError(getApplicationContext().getString(R.string.different_passwords));
			
			}

			
	    }
	    
}
