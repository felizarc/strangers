package com.example.strangers.tasks;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.example.strangers.model.AddMailBoxResponse;
import com.example.strangers.utilities.MailBoxUtilities;

public class TaskNewAccount extends AsyncTask<Object, Integer, AddMailBoxResponse> {
	
	ProgressDialog dialog;
	Activity activity;
	
	public TaskNewAccount (Activity activity) {
		super();
		this.activity=activity;
		this.dialog = new ProgressDialog(activity);
	}
	
	protected void onPreExecute() {		
		this.dialog.setMessage("Ajout de la boite mail.");
		this.dialog.show();
        super.onPreExecute();
	}

	@Override
	protected void onProgressUpdate(Integer... values){
		super.onProgressUpdate(values);
		dialog.setProgress(values[0]);
	}
	
	@Override
	protected AddMailBoxResponse doInBackground(Object... params) {
		
		Context taskContext = (Context)params[0];
		String currentUserLogin = String.valueOf(params[1]);
		String currentUserPassword = String.valueOf(params[2]);
		String host = String.valueOf(params[3]);
		String port = String.valueOf(params[4]);
		String login = String.valueOf(params[5]);
		String password = String.valueOf(params[6]);
		String description = String.valueOf(params[7]);

		AddMailBoxResponse addMailBoxResponse = MailBoxUtilities.addMailBox(taskContext, currentUserLogin, 
																			currentUserPassword, host, 
																			port, login, password, description);
		
		return addMailBoxResponse;
	}
	
	@Override
	protected void onPostExecute(AddMailBoxResponse addMailBoxResponse) {
		if(dialog!=null)
        {
            dialog.dismiss();
        }
		super.onPostExecute(addMailBoxResponse);
	}
}
