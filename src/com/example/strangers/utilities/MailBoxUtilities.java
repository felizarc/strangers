package com.example.strangers.utilities;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.util.Base64;

import com.example.strangers.R;
import com.example.strangers.model.AddMailBoxResponse;

public class MailBoxUtilities {

	public static AddMailBoxResponse addMailBox(Context context, String currentUserLogin, String currentUserPassword, 
												String host, String port, String login, String password, String description) {
		
		String baseUrl = context.getString(R.string.service_base_url);
		String registrationService = context.getString(R.string.new_account);
		
		HttpClient httpclient = new DefaultHttpClient();
        HttpPost httppost = new HttpPost(baseUrl+registrationService);
        
        //Add http basic auth
        String authParams = currentUserLogin+":"+currentUserPassword;
        httppost.setHeader("Authorization", "Basic "+Base64.encodeToString(authParams.getBytes(), Base64.NO_WRAP));
        
        AddMailBoxResponse addMailBoxResponse = null;
        
        try {
            // Add your data
            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
            nameValuePairs.add(new BasicNameValuePair("host", host));
            nameValuePairs.add(new BasicNameValuePair("port", port));
            nameValuePairs.add(new BasicNameValuePair("username", login));
            nameValuePairs.add(new BasicNameValuePair("password", password));
            nameValuePairs.add(new BasicNameValuePair("description", description));
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

            // Execute HTTP Post Request
            HttpResponse response = httpclient.execute(httppost);
                       
            //get the response as a string
            String jsonString = EntityUtils.toString(response.getEntity());
            
            //create the json object
            JSONObject jsonObjectResponse;
            
			try {
				//construct the json object with the response String
				jsonObjectResponse = new JSONObject(jsonString);
				
				//construct the addMailBoxResponse object with the json object
				addMailBoxResponse = new AddMailBoxResponse(jsonObjectResponse.getInt("status"));
				
			} catch (JSONException e) {
				//TODO complete exception
			}
            
        } catch (ClientProtocolException e) {
			//TODO complete exception
        } catch (IOException e) {
			//TODO complete exception
        }
        
        return addMailBoxResponse;
	}
}
