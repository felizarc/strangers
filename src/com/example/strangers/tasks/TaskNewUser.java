package com.example.strangers.tasks;

import com.example.strangers.model.AuthenticationResponse;
import com.example.strangers.utilities.UserUtilities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

public class TaskNewUser extends AsyncTask<Object, Integer, AuthenticationResponse> {
	
	ProgressDialog dialog;
	Activity activity;
	
	public TaskNewUser (Activity activity) {
		super();
		this.activity=activity;
		this.dialog = new ProgressDialog(activity);
	}
	
	protected void onPreExecute() {		
		this.dialog.setMessage("Création du compte.");
		this.dialog.show();
        super.onPreExecute();
	}

	@Override
	protected void onProgressUpdate(Integer... values){
		super.onProgressUpdate(values);
		dialog.setProgress(values[0]);
	}
	
	@Override
	protected AuthenticationResponse doInBackground(Object... params) {
		
		Context taskContext = (Context)params[0];
		String login = String.valueOf(params[1]);
		String password = String.valueOf(params[2]);


		AuthenticationResponse authenticationResult = UserUtilities.inscription(taskContext, login, password);
		
		return authenticationResult;
	}
	
	@Override
	protected void onPostExecute(AuthenticationResponse authenticationResult) {
		if(dialog!=null)
        {
            dialog.dismiss();
        }
		super.onPostExecute(authenticationResult);
	}
}
