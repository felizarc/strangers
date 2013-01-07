package com.example.strangers.utilities;

import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

import com.example.strangers.model.SmsSimple;

public class SmsUtilities {

	
	public static ArrayList<ArrayList<String>>/*HashMap<String, ArrayList<String>>*/ getSMS(Context context) {
		
		Cursor cursor = context.getContentResolver().query(Uri.parse("content://sms/inbox"), null, null, null, null);
		cursor.moveToFirst();

		//HashMap<String, ArrayList<String>> listAllMessagesPerUser = new HashMap<String, ArrayList<String>>();
		ArrayList<ArrayList<String>> listAllMessagesPerUser = new ArrayList<ArrayList<String>>();
		

		do{   
			String rebootUser = null;
			ArrayList<String> listMessagesPerUser = new ArrayList<String>();

			for(int i=0;i<cursor.getColumnCount();i++)
			{
				SmsSimple currentSms = new SmsSimple(null, null);
				
				if(cursor.getColumnName(i).equals("address")) {
					currentSms.setBody(cursor.getString(i));
				}
				
				if(cursor.getColumnName(i).equals("body")) {
					currentSms.setBody(cursor.getString(i));
				}
			}
				
		} while(cursor.moveToNext());
		
		Log.v("MESSAGES", listAllMessagesPerUser.toString());
		
		return listAllMessagesPerUser;
	}
}
