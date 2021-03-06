package com.example.strangers.controller;

import org.apache.http.HttpStatus;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.strangers.R;
import com.example.strangers.model.User;
import com.example.strangers.tasks.TaskNewAccount;

public class NewMailAccount extends Activity {

	private User currentUser;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_mail_account);
		
		ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        
        Bundle bundle = getIntent().getBundleExtra("currentUserBundle");
		this.currentUser = bundle.getParcelable("com.example.strangers.model.User");
	}
	
	public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
            	Bundle bundle = new Bundle();
				bundle.putParcelable("com.example.strangers.model.User", currentUser);
				Intent intent = new Intent(this, NumberSearch.class);
				intent.putExtra("currentUserBundle", bundle);
		    	startActivity(intent);  	    	
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
	
	
    public void addMailAccount(View v) {
    	
    	EditText hostInput = (EditText) findViewById(R.id.add_mail_host_input);
		EditText portInput = (EditText) findViewById(R.id.add_mail_port_input);
		EditText loginInput = (EditText) findViewById(R.id.add_mail_login_input);
		EditText passwordInput = (EditText) findViewById(R.id.add_mail_password_input);
		EditText descriptionInput = (EditText) findViewById(R.id.add_mail_description_input);

		String host = hostInput.getText().toString();
		String port = portInput.getText().toString();
		String login = loginInput.getText().toString();
		String password = passwordInput.getText().toString();
		String description = descriptionInput.getText().toString();
		
		//Pr�paration et appel du thread d'inscription
		Object params[] = {getApplicationContext(), currentUser.getLogin(), 
							currentUser.getPassword(), host, port, login, password, description};

		TaskNewAccount taskNewAccount = new TaskNewAccount(this);
    	taskNewAccount.execute(params);
    }
    
    public void afterAccountCreation(Integer status) {
    	if(status != null && status == HttpStatus.SC_CREATED) {
			//switch to main activity
			Bundle bundle = new Bundle();
			bundle.putParcelable("com.example.strangers.model.User", currentUser);
			Intent intent = new Intent(this, NumberSearch.class);
			intent.putExtra("currentUserBundle", bundle);
			startActivity(intent);
		}
		else {
			if(status != null) {
				Log.e("Mail", status.toString());
			} else {
				Log.e("Mail", "Null");
			}
			int duration = Toast.LENGTH_SHORT;
			String text = getApplicationContext().getString(R.string.account_creation_error);
			Toast toastError = Toast.makeText(getApplicationContext(), text, duration);
			toastError.show();
		}
    }

    public void changePasswordInputType(View checkbox) {
    	CheckBox show_password_checkbox = (CheckBox) checkbox;
    	EditText passwordInput = (EditText) findViewById(R.id.add_mail_password_input);
    	
    	if(show_password_checkbox.isChecked()) {
    		passwordInput.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
    	} else {
    		passwordInput.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
    	}
    }
}
