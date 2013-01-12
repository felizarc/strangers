package com.example.strangers.tasks;

import java.util.ArrayList;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.example.strangers.R;
import com.example.strangers.model.AccountResponse;
import com.example.strangers.utilities.MailBoxUtilities;

public class TaskGetAccounts extends AsyncTask<Object, Integer, ArrayList<AccountResponse>> {
	
	ProgressDialog dialog;
	Activity activity;
	
	public TaskGetAccounts (Activity activity) {
		super();
		this.activity=activity;
		this.dialog = new ProgressDialog(activity);
	}
	
	protected void onPreExecute() {		
		this.dialog.setMessage(activity.getApplicationContext().getString(R.string.user_check_process));
		this.dialog.show();
        super.onPreExecute();
	}

	@Override
	protected void onProgressUpdate(Integer... values){
		super.onProgressUpdate(values);
		dialog.setProgress(values[0]);
	}
	
	@Override
	protected ArrayList<AccountResponse> doInBackground(Object... params) {
		
		Context taskContext = (Context)params[0];
		String currentUserLogin = String.valueOf(params[1]);
		String currentUserPassword = String.valueOf(params[2]);	

		ArrayList<AccountResponse> listResponse = MailBoxUtilities.getList(taskContext, currentUserLogin, currentUserPassword);
		return listResponse;
	}
	
	@Override
	protected void onPostExecute(ArrayList<AccountResponse> listResponse) {
		if(dialog!=null)
        {
            dialog.dismiss();
        }
		super.onPostExecute(listResponse);
	}
}
