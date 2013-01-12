package com.example.strangers.tasks;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.example.strangers.R;
import com.example.strangers.controller.Login;
import com.example.strangers.utilities.UserUtilities;

public class TaskCheckUser extends AsyncTask<Object, Integer, Object[]> {
	
	ProgressDialog dialog;
	Activity activity;
	
	public TaskCheckUser(Activity activity) {
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
	protected Object[] doInBackground(Object... params) {
		
		Context taskContext = (Context)params[0];
		String currentUserLogin = String.valueOf(params[1]);
		String currentUserPassword = String.valueOf(params[2]);		

		Integer status = UserUtilities.verification(taskContext, currentUserLogin, currentUserPassword);
		Object[] result = {status, currentUserLogin, currentUserPassword};
		return result; 
	}
	
	@Override
	protected void onPostExecute(Object... result) {
		Integer status = (Integer) result[0];
		String login = (String) result[1];
		String password = (String) result[2];		
		
		Login loginActivity = (Login) activity;
		loginActivity.afterLogin(status, login, password);
		
		if(dialog!=null)
        {
            dialog.dismiss();
        }
		super.onPostExecute(result);
	}
}
