package com.example.strangers.tasks;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.example.strangers.R;
import com.example.strangers.utilities.MailBoxUtilities;

public class TaskNewAccount extends AsyncTask<Object, Integer, Integer> {
	
	ProgressDialog dialog;
	Activity activity;
	
	public TaskNewAccount (Activity activity) {
		super();
		this.activity=activity;
		this.dialog = new ProgressDialog(activity);
	}
	
	protected void onPreExecute() {		
		this.dialog.setMessage(activity.getApplicationContext().getString(R.string.account_creation_process));
		this.dialog.show();
        super.onPreExecute();
	}

	@Override
	protected void onProgressUpdate(Integer... values){
		super.onProgressUpdate(values);
		dialog.setProgress(values[0]);
	}
	
	@Override
	protected Integer doInBackground(Object... params) {
		
		Context taskContext = (Context)params[0];
		String currentUserLogin = String.valueOf(params[1]);
		String currentUserPassword = String.valueOf(params[2]);
		String host = String.valueOf(params[3]);
		String port = String.valueOf(params[4]);
		String login = String.valueOf(params[5]);
		String password = String.valueOf(params[6]);
		String description = String.valueOf(params[7]);

		Integer status = MailBoxUtilities.addMailBox(taskContext, currentUserLogin, 
																			currentUserPassword, host, 
																			port, login, password, description);
		
		return status;
	}
	
	@Override
	protected void onPostExecute(Integer status) {
		if(dialog!=null)
        {
            dialog.dismiss();
        }
		super.onPostExecute(status);
	}
}
