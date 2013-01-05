package com.example.strangers.controller;

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
import android.widget.Toast;

import com.example.strangers.R;
import com.example.strangers.model.User;
import com.example.strangers.tasks.TaskDeleteUser;

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
    }
    
    public void deleteUser() {
    	Log.v("user", "Suppression");
				
		String login = currentUser.getLogin();
		String password = currentUser.getPassword();
		
		Object params[] = {getApplicationContext(), login, password};
    	
		//Todo: Check why this is not working
    	TaskDeleteUser taskDeleteUser = new TaskDeleteUser(this);
    	taskDeleteUser.execute(params);
    	
    	Integer status = null;
		try {
			status = taskDeleteUser.get();
			Log.v("User Delete Status", status.toString());
			Log.v("User Delete Login", login.toString());
			Log.v("User Delete Password", password.toString());
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		
		if(status != null && status == HttpStatus.SC_OK) {
			int duration = Toast.LENGTH_SHORT;
			String text = "Suppression du compte prise en compte";
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
			String text = "Erreur lors de la suppression du compte";
			Toast toastError = Toast.makeText(getApplicationContext(), text, duration);
			toastError.show();
		}
    }
}
