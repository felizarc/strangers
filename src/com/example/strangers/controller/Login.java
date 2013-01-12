package com.example.strangers.controller;

import org.apache.http.HttpStatus;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.strangers.R;
import com.example.strangers.model.User;
import com.example.strangers.tasks.TaskCheckUser;
import com.example.strangers.utilities.ObjectAndString;

public class Login extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		setTitle(R.string.title_activity_login);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login_menu_layout, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		/*case android.R.id.home:
			NavUtils.navigateUpFromSameTask(this);
			return true;*/
		case R.id.newAccount:
			Intent intent = new Intent(this, Inscription.class);
	    	startActivity(intent);
	    	return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	//Action du bouton de connexion
    public void login(View v) {
    	
    	EditText loginInput = (EditText) findViewById(R.id.loginInput);
		EditText passwordInput = (EditText) findViewById(R.id.passwordInput);
		
		String login = loginInput.getText().toString();
		String password = passwordInput.getText().toString();		
		
		Object params[] = {getApplicationContext(), login, password};
    	
    	TaskCheckUser taskCheckUser = new TaskCheckUser(this);
    	taskCheckUser.execute(params);		
    }
    
    public void afterLogin(Integer status, String login, String password) {
    	if(status != null && status == HttpStatus.SC_OK) {
			//create user
			User user = new User(login, password);
			
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
		} else if(status != null && status == HttpStatus.SC_UNAUTHORIZED) {
			int duration = Toast.LENGTH_SHORT;
			String text = getApplicationContext().getString(R.string.user_login_denied);
			Toast toastError = Toast.makeText(getApplicationContext(), text, duration);
			toastError.show();
		} else {
			int duration = Toast.LENGTH_SHORT;
			String text = getApplicationContext().getString(R.string.user_check_error);
			Toast toastError = Toast.makeText(getApplicationContext(), text, duration);
			toastError.show();
		}
    }

}
