package com.example.strangers.tasks;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.example.strangers.utilities.UserUtilities;

public class TaskCheckUser extends AsyncTask<Object, Integer, Integer> {
	
	ProgressDialog dialog;
	Activity activity;
	
	public TaskCheckUser (Activity activity) {
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
	protected Integer doInBackground(Object... params) {
		
		Context taskContext = (Context)params[0];
		String currentUserLogin = String.valueOf(params[1]);
		String currentUserPassword = String.valueOf(params[2]);
		

		Integer status = UserUtilities.connexion(taskContext, currentUserLogin, currentUserPassword);
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
