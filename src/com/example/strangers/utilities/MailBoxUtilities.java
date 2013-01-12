package com.example.strangers.utilities;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.util.Base64;
import android.util.Log;

import com.example.strangers.R;
import com.example.strangers.model.AccountResponse;

public class MailBoxUtilities {

	public static Integer addMailBox(Context context, String currentUserLogin, String currentUserPassword, 
												String host, String port, String login, String password, String description) {
		
		Integer status = null;
		
		String baseUrl = context.getString(R.string.service_base_url_https);
		String registrationService = context.getString(R.string.new_account);
		
		HttpClient httpClient = MySSLSocketFactory.getNewHttpClient();
		//HttpClient httpclient = new DefaultHttpClient();
        HttpPost httppost = new HttpPost(baseUrl+registrationService);
        
        //Add http basic auth
        String authParams = currentUserLogin+":"+currentUserPassword;
        httppost.setHeader("Authorization", "Basic "+Base64.encodeToString(authParams.getBytes(), Base64.NO_WRAP));
                
        try {
            // Add your data
            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
            nameValuePairs.add(new BasicNameValuePair("account[host]", host));
            nameValuePairs.add(new BasicNameValuePair("account[port]", port));
            nameValuePairs.add(new BasicNameValuePair("account[username]", login));
            nameValuePairs.add(new BasicNameValuePair("account[password]", password));
            nameValuePairs.add(new BasicNameValuePair("account[description]", description));
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

            // Execute HTTP Post Request
            HttpResponse response = httpClient.execute(httppost);
                     
            StatusLine statusLine = response.getStatusLine();
            status = statusLine.getStatusCode();
            
        } catch (ClientProtocolException e) {
			//TODO complete exception
        } catch (IOException e) {
			//TODO complete exception
        } catch (SecurityException e) {
        	//TODO complete Exception
        }
        
        return status;
	}

	public static ArrayList<AccountResponse> getList(Context context, String currentUserLogin, String currentUserPassword) {
		String baseUrl = context.getString(R.string.service_base_url_https);
		String accountListService = context.getString(R.string.list_accounts);
		
		HttpClient httpClient = MySSLSocketFactory.getNewHttpClient();
        HttpGet httpget = new HttpGet(baseUrl+accountListService);
        
        String authParams = currentUserLogin+":"+currentUserPassword;
        httpget.setHeader("Authorization", "Basic "+Base64.encodeToString(authParams.getBytes(), Base64.NO_WRAP));
                
        ArrayList<AccountResponse> accountResponseList = new ArrayList<AccountResponse>();
        
        try {

            // Execute HTTP Post Request
            HttpResponse response = httpClient.execute(httpget);
                     
            
            
            //get the response as a string
            String jsonString = EntityUtils.toString(response.getEntity());
            
			
            
            try {
            	JSONArray jsonArrayResponse = new JSONArray(jsonString);
            	JSONObject jsonObjectResponse;
            	
            	for(int i=0; i < jsonArrayResponse.length(); i++) {
    				jsonObjectResponse = (JSONObject) jsonArrayResponse.get(i);
    				Log.v("json", jsonObjectResponse.toString());
    				
    				AccountResponse accountResponse = new AccountResponse(Integer.decode(jsonObjectResponse.getString("id")),
    																	Integer.decode(jsonObjectResponse.getString("user_id")),
    																	jsonObjectResponse.getString("host"),
    																	Integer.decode(jsonObjectResponse.getString("port")),
    																	jsonObjectResponse.getString("username"),
    																	jsonObjectResponse.getString("description"));
    				
    				accountResponseList.add(accountResponse);
            	}
            	
			} catch (JSONException e) {				
				Log.e("JSON Account List", e.getMessage());
			}            
            
        } catch (ClientProtocolException e) {
			//TODO complete exception
        } catch (IOException e) {
			//TODO complete exception
        } catch (SecurityException e) {
        	
        }
        
        return accountResponseList;
	}
	
	public static Integer suppression(Context context, String login, String password, Integer account_id) {
		
		String baseUrl = context.getString(R.string.service_base_url_https);
		String userdeletionService = context.getString(R.string.delete_account);
		
		HttpClient httpClient = MySSLSocketFactory.getNewHttpClient();
        HttpDelete httpdelete = new HttpDelete(baseUrl+userdeletionService+String.valueOf(account_id));
        
        String authParams = login+":"+password;
        httpdelete.setHeader("Authorization", "Basic "+Base64.encodeToString(authParams.getBytes(), Base64.NO_WRAP));
		
		Integer status = null;
		
		try {
            HttpResponse response = httpClient.execute(httpdelete);
            StatusLine statusLine = response.getStatusLine();
            status = statusLine.getStatusCode();
            
        } catch (ClientProtocolException e) {
        	//TODO error message
        } catch (IOException e) {
        	//TODO error message
        } catch (SecurityException e) {
        	//TODO error message
        }
		
		return status;
	}
}
