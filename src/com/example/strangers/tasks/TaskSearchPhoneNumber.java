package com.example.strangers.tasks;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.example.strangers.R;
import com.example.strangers.controller.NumberSearch;
import com.example.strangers.model.GeneralSearchNumberResponse;
import com.example.strangers.utilities.SearchPhoneNumberUtilities;

public class TaskSearchPhoneNumber extends AsyncTask<Object, Integer, GeneralSearchNumberResponse> {
	
	ProgressDialog dialog;
	Activity activity;
	
	public TaskSearchPhoneNumber (Activity activity) {
		super();
		this.activity=activity;
		this.dialog = new ProgressDialog(activity);
	}
	
	@Override
	protected void onPreExecute() {		
		this.dialog.setMessage(activity.getApplicationContext().getString(R.string.numberSearch_process));
		this.dialog.show();
        super.onPreExecute();
	}

	@Override
	protected void onProgressUpdate(Integer... values){
		super.onProgressUpdate(values);
		dialog.setProgress(values[0]);
	}
	
	@Override
	protected GeneralSearchNumberResponse doInBackground(Object... params) {
		
		Context taskContext = (Context)params[0];
		String currentUserLogin = String.valueOf(params[1]);
		String currentUserPassword = String.valueOf(params[2]);		
		String phoneNumber = String.valueOf(params[3]);		

		GeneralSearchNumberResponse generalSearchNumberResponse = SearchPhoneNumberUtilities.addMailBox(taskContext, currentUserLogin, currentUserPassword, phoneNumber);
		
		return generalSearchNumberResponse;
	}
	
	@Override
	protected void onPostExecute(GeneralSearchNumberResponse generalSearchNumberResponse) {
		NumberSearch Intent = (NumberSearch) activity;
		Intent.afterSearchPhoneNumber(generalSearchNumberResponse);
		
		if(dialog!=null)
        {
            dialog.dismiss();
        }
		super.onPostExecute(generalSearchNumberResponse);
	}
}
