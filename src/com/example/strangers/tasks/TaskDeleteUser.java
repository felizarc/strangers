package com.example.strangers.tasks;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.example.strangers.R;
import com.example.strangers.controller.NumberSearch;
import com.example.strangers.utilities.UserUtilities;

public class TaskDeleteUser extends AsyncTask<Object, Integer, Integer> {
	
	ProgressDialog dialog;
	Activity activity;
	
	public TaskDeleteUser (Activity activity) {
		super();
		this.activity=activity;
		this.dialog = new ProgressDialog(activity);
	}
	
	protected void onPreExecute() {		
		this.dialog.setMessage(activity.getApplicationContext().getString(R.string.user_deletion_process));
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

		Integer status = UserUtilities.suppression(taskContext, login, password);
		
		return status;
	}
	
	@Override
	protected void onPostExecute(Integer status) {
		NumberSearch number = (NumberSearch) activity;
		number.afterUserDeletion(status);
		
		if(dialog!=null)
        {
            dialog.dismiss();
        }
		super.onPostExecute(status);
	}
}
