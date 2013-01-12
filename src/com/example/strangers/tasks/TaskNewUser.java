package com.example.strangers.tasks;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.example.strangers.R;
import com.example.strangers.controller.Inscription;
import com.example.strangers.controller.Login;
import com.example.strangers.utilities.UserUtilities;

public class TaskNewUser extends AsyncTask<Object, Integer, Object[]> {
	
	ProgressDialog dialog;
	Activity activity;
	
	public TaskNewUser (Activity activity) {
		super();
		this.activity=activity;
		this.dialog = new ProgressDialog(activity);
	}
	
	protected void onPreExecute() {		
		this.dialog.setMessage(activity.getApplicationContext().getString(R.string.user_creation_process));
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
		String login = String.valueOf(params[1]);
		String password = String.valueOf(params[2]);


		Integer status = UserUtilities.inscription(taskContext, login, password);
		Object[] result = {status, login, password};
		
		return result;
	}
	
	@Override
	protected void onPostExecute(Object... result) {
		Integer status = (Integer) result[0];
		String login = (String) result[1];
		String password = (String) result[2];
		
		Inscription registrationActivity = (Inscription) activity;
		registrationActivity.afterSubscribe(status, login, password);
		
		if(dialog!=null)
        {
            dialog.dismiss();
        }
		super.onPostExecute(result);
	}
}
