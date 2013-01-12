package com.example.strangers.tasks;

import java.util.ArrayList;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.example.strangers.R;
import com.example.strangers.controller.NumberSearch;
import com.example.strangers.model.SearchResponse;
import com.example.strangers.utilities.SearchPhoneNumberUtilities;

public class TaskSearchPhoneNumber extends AsyncTask<Object, Integer, ArrayList<SearchResponse>> {
	
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
	protected ArrayList<SearchResponse> doInBackground(Object... params) {
		
		Context taskContext = (Context)params[0];
		String currentUserLogin = String.valueOf(params[1]);
		String currentUserPassword = String.valueOf(params[2]);		
		String phoneNumber = String.valueOf(params[3]);		

		ArrayList<SearchResponse> listResponse = SearchPhoneNumberUtilities.addMailBox(taskContext, currentUserLogin, currentUserPassword, phoneNumber);
		return listResponse;
	}
	
	@Override
	protected void onPostExecute(ArrayList<SearchResponse> listResponse) {
		NumberSearch Intent = (NumberSearch) activity;
		Intent.afterSearchPhoneNumber(listResponse);
		
		if(dialog!=null)
        {
            dialog.dismiss();
        }
		super.onPostExecute(listResponse);
	}
}
