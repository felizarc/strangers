package com.example.strangers.controller;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import org.apache.http.HttpStatus;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.strangers.R;
import com.example.strangers.model.SearchResponse;
import com.example.strangers.model.User;
import com.example.strangers.tasks.TaskCheckUser;
import com.example.strangers.tasks.TaskDeleteUser;
import com.example.strangers.tasks.TaskSearchPhoneNumber;
import com.example.strangers.utilities.ObjectAndString;

public class NumberSearch extends Activity {

	private User currentUser;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_number_search);
		setTitle(R.string.title_activity_number_search);
		
		Bundle bundle = getIntent().getBundleExtra("currentUserBundle");
		this.currentUser = bundle.getParcelable("com.example.strangers.model.User");
		
		Log.v("user", currentUser.getLogin());
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.number_search_menu_layout, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		
			case R.id.unregister:
				SharedPreferences stockPreferences = getSharedPreferences("strangers", Activity.MODE_PRIVATE);
				Editor edit = stockPreferences.edit();
				edit.remove("registeredUser");
				edit.apply();
				
				Intent intent = new Intent(this, Login.class);
		    	startActivity(intent);
		    	return true;
		    	
			case R.id.newMailAccount:
				Bundle bundle = new Bundle();
				bundle.putParcelable("com.example.strangers.model.User", currentUser);
				Intent intentMail = new Intent(this, NewMailAccount.class);
				intentMail.putExtra("currentUserBundle", bundle);
		    	startActivity(intentMail);
		    	return true;
		    	
			case R.id.deleteUser:
				AlertDialog.Builder builder = new AlertDialog.Builder(this);
				builder.setMessage(R.string.delete_user_message).setTitle(R.string.delete_user_title);
				
				builder.setPositiveButton(R.string.confirm_delete, new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						deleteUser();
					}
				});
				
				builder.setNegativeButton(R.string.infirm_delete, new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {}
				});
				
				AlertDialog delete_dialog = builder.create();
				delete_dialog.show();
		    	return true;
		}
		return super.onOptionsItemSelected(item);
	}

    public void searchPhoneNumber(View v) {
    	
    	EditText phoneNumberInput = (EditText) findViewById(R.id.phoneNumberInput);
		String phoneNumber = phoneNumberInput.getText().toString();
		Object params[] = {getApplicationContext(), currentUser.getLogin(), currentUser.getPassword(), phoneNumber};
    	
    	TaskSearchPhoneNumber taskSearchPhoneNumber = new TaskSearchPhoneNumber(this);
    	taskSearchPhoneNumber.execute(params);
    	
    	ArrayList<SearchResponse> searchPhoneNumberResponseList = null;
		try {
			searchPhoneNumberResponseList = taskSearchPhoneNumber.get();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		
		/*if(status != null && status == HttpStatus.SC_OK) {
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
		}    */
	}
    
    public void deleteUser() {
    	Log.v("user", "Suppression");
				
		String login = currentUser.getLogin();
		String password = currentUser.getPassword();
		
		Object params[] = {getApplicationContext(), login, password};
    	
    	TaskDeleteUser taskDeleteUser = new TaskDeleteUser(this);
    	taskDeleteUser.execute(params);
    	
    	Integer status = null;
		try {
			status = taskDeleteUser.get();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		
		if(status != null && status == HttpStatus.SC_OK) {
			int duration = Toast.LENGTH_SHORT;
			String text = getApplicationContext().getString(R.string.user_deletion_accepted);
			Toast toastError = Toast.makeText(getApplicationContext(), text, duration);
			toastError.show();
			
			SharedPreferences stockPreferences = getSharedPreferences("strangers", Activity.MODE_PRIVATE);
			Editor edit = stockPreferences.edit();
			edit.remove("registeredUser");
			edit.apply();
			
			Intent intent = new Intent(this, Login.class);
	    	startActivity(intent);						
		}
		else {
			int duration = Toast.LENGTH_SHORT;
			String text = getApplicationContext().getString(R.string.user_deletion_denied);
			Toast toastError = Toast.makeText(getApplicationContext(), text, duration);
			toastError.show();
		}
    }
}
