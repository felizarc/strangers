package com.example.strangers.controller;

import java.util.ArrayList;
import java.util.Iterator;
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
import android.widget.ListView;
import android.widget.Toast;

import com.example.strangers.R;
import com.example.strangers.model.AccountResponse;
import com.example.strangers.model.SearchResponse;
import com.example.strangers.model.User;
import com.example.strangers.tasks.TaskDeleteAccount;
import com.example.strangers.tasks.TaskDeleteUser;
import com.example.strangers.tasks.TaskGetAccounts;
import com.example.strangers.tasks.TaskSearchPhoneNumber;
import com.example.strangers.utilities.ResponseAdapter;

public class NumberSearch extends Activity {

	private User currentUser;
	private ArrayList<AccountResponse> listeComptes;
	
	public ArrayList<AccountResponse> getListeComptes() {
		return listeComptes;
	}

	public void setListeComptes(ArrayList<AccountResponse> listeComptes) {
		this.listeComptes = listeComptes;
	}

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
		    
			case R.id.deleteMailAccount:
				showUserAccounts();
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
	}
    
    public void afterSearchPhoneNumber(ArrayList<SearchResponse> searchPhoneNumberResponseList) {
    	if(searchPhoneNumberResponseList != null && searchPhoneNumberResponseList.size() > 0) {
    		
	    	ListView listMessagesView = (ListView) findViewById(R.id.searchResults);

	    	//Remplissage de la liste par un adapter
	    	ResponseAdapter adapter = new ResponseAdapter(getApplicationContext(), searchPhoneNumberResponseList);
	    	listMessagesView.setAdapter(adapter);
    	}
    }
    
    public void deleteUser() {
    	Log.v("user", "Suppression");
				
		String login = currentUser.getLogin();
		String password = currentUser.getPassword();
		
		Object params[] = {getApplicationContext(), login, password};
    	
    	TaskDeleteUser taskDeleteUser = new TaskDeleteUser(this);
    	taskDeleteUser.execute(params);		
    }
    
    public void afterUserDeletion(Integer status) {
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
    
    public void showUserAccounts() {
    	Object params[] = {getApplicationContext(), currentUser.getLogin(), currentUser.getPassword()};
    	
    	TaskGetAccounts taskGetAccounts = new TaskGetAccounts(this);
    	taskGetAccounts.execute(params);
    }
    
    public void afterUserAccountsList(ArrayList<AccountResponse> listeComptes) {
    	setListeComptes(listeComptes);
    	
		if(listeComptes != null && listeComptes.size() > 0) {
			ArrayList<String> libellesComptes = new ArrayList<String>();
			
			Iterator<AccountResponse> iterator = this.listeComptes.iterator();
			while(iterator.hasNext()) {
				AccountResponse compte = iterator.next();
				libellesComptes.add(compte.getDescription() + ": " + compte.getHost() + " / " + compte.getPort() + " / " + compte.getUsername());
			}
	    	
	    	AlertDialog.Builder builder = new AlertDialog.Builder(this);
	        builder.setTitle(R.string.delete_account_dialog_title);
	        
	        builder.setItems(libellesComptes.toArray(new CharSequence[listeComptes.size()]), new DialogInterface.OnClickListener() {
	        	public void onClick(DialogInterface dialog, int which) {	        		
	        		confirmAccountDelete(which);
	        	}
	        });
	        
	        AlertDialog delete_account_dialog = builder.create();
			delete_account_dialog.show();
    	} else {
    		int duration = Toast.LENGTH_SHORT;
			String text = getApplicationContext().getString(R.string.user_account_list_error);
			Toast toastError = Toast.makeText(getApplicationContext(), text, duration);
			toastError.show();
    	}    	
    }
    
    public void confirmAccountDelete(int choix) {
    	ArrayList<AccountResponse> listeComptes = getListeComptes();
    	final int account_id = listeComptes.get(choix).getId();
    	
    	AlertDialog.Builder confirm_builder = new AlertDialog.Builder(this);
		confirm_builder.setMessage(R.string.delete_account_message).setTitle(R.string.confirm_delete_account_dialog_title);
		
		confirm_builder.setPositiveButton(R.string.confirm_delete, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				deleteAccount(account_id);
			}
		});
		
		confirm_builder.setNegativeButton(R.string.infirm_delete, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {}
		});
		
		AlertDialog confirm_dialog = confirm_builder.create();
		confirm_dialog.show();
    }
    
    public void deleteAccount(int choix) {
    	String login = currentUser.getLogin();
		String password = currentUser.getPassword();
		
		Object params[] = {getApplicationContext(), login, password, choix};
    	
    	TaskDeleteAccount taskDeleteAccount = new TaskDeleteAccount(this);
    	taskDeleteAccount.execute(params);		
    }
    
    public void afterAccountDeletion(Integer status) {
    	if(status != null && status == HttpStatus.SC_OK) {
			int duration = Toast.LENGTH_SHORT;
			String text = getApplicationContext().getString(R.string.account_deletion_accepted);
			Toast toastOk = Toast.makeText(getApplicationContext(), text, duration);
			toastOk.show();						
		}
		else {
			int duration = Toast.LENGTH_SHORT;
			String text = getApplicationContext().getString(R.string.account_deletion_denied);
			Toast toastError = Toast.makeText(getApplicationContext(), text, duration);
			toastError.show();
			Log.e("Erreur", String.valueOf(status));
		}
    }
}
