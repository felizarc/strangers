package com.example.strangers.tasks;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.example.strangers.R;
import com.example.strangers.controller.NumberSearch;
import com.example.strangers.utilities.MailBoxUtilities;

public class TaskDeleteAccount extends AsyncTask<Object, Integer, Integer> {
	
	ProgressDialog dialog;
	Activity activity;
	
	public TaskDeleteAccount (Activity activity) {
		super();
		this.activity=activity;
		this.dialog = new ProgressDialog(activity);
	}
	
	protected void onPreExecute() {		
		this.dialog.setMessage(activity.getApplicationContext().getString(R.string.account_deletion_process));
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
		String login = String.valueOf(params[1]);
		String password = String.valueOf(params[2]);
		int account_id = (Integer) params[3];

		Integer status = MailBoxUtilities.suppression(taskContext, login, password, account_id);
		
		return status;
	}
	
	@Override
	protected void onPostExecute(Integer status) {
		NumberSearch number = (NumberSearch) activity;
		number.afterAccountDeletion(status);
		
		if(dialog!=null)
        {
            dialog.dismiss();
        }
		super.onPostExecute(status);
	}
}
