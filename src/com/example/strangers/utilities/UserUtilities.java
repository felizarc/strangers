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
import com.example.strangers.model.AuthenticationResponse;

/**
 * Classe contenant les m�thodes pour appeler les services li�s aux utilisateurs.
 * @author Xavier
 *
 */
public class UserUtilities {
	
	public static AuthenticationResponse connexion(Context context, String login, String password) {
		
		String baseUrl = context.getString(R.string.service_base_url);
		//pas de service de connexion pour l'instant
		//String authenticationService = context.getString(R.string.services_authentication);
		
		HttpClient httpclient = new DefaultHttpClient();
        HttpPost httppost = new HttpPost(baseUrl/*+authenticationService*/);
        
        //Add http basic auth
        String authParams = login+":"+password;
        httppost.setHeader("Authorization", "Basic "+Base64.encodeToString(authParams.getBytes(), Base64.NO_WRAP));
	
        
        AuthenticationResponse authentication = null;
        
        try {
            // Add your data
            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
            nameValuePairs.add(new BasicNameValuePair("username", login));
            nameValuePairs.add(new BasicNameValuePair("password", password));
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
				
				//construct the authentication object with the json object
				authentication = new AuthenticationResponse(jsonObjectResponse.getString("message"), 
													jsonObjectResponse.getString("token"), 
													jsonObjectResponse.getInt("expiration"));
				
			} catch (JSONException e) {
				authentication = new AuthenticationResponse();
				authentication.setMessage("JSONException");
			}
            
        } catch (ClientProtocolException e) {
        	authentication = new AuthenticationResponse();
			authentication.setMessage("ClientProtocolException");
        } catch (IOException e) {
        	authentication = new AuthenticationResponse();
			authentication.setMessage("IOException");
        }
        
        return authentication;
	}
	
	public static AuthenticationResponse inscription(Context context, String login, String password) {
			
		String baseUrl = context.getString(R.string.service_base_url);
		String registrationService = context.getString(R.string.new_user);
		
		HttpClient httpclient = new DefaultHttpClient();
        HttpPost httppost = new HttpPost(baseUrl+registrationService);
        
        //Add http basic auth
        String authParams = login+":"+password;
        httppost.setHeader("Authorization", "Basic "+Base64.encodeToString(authParams.getBytes(), Base64.NO_WRAP));
        
        AuthenticationResponse authentication = null;
        
        try {
            // Add your data
            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
            nameValuePairs.add(new BasicNameValuePair("username", login));
            nameValuePairs.add(new BasicNameValuePair("password", password));
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
				
				//construct the authentication object with the json object
				authentication = new AuthenticationResponse(jsonObjectResponse.getString("message"), 
													jsonObjectResponse.getString("token"), 
													jsonObjectResponse.getInt("expiration"));
				
			} catch (JSONException e) {
				authentication = new AuthenticationResponse();
				authentication.setMessage("JSONException");
			}
            
        } catch (ClientProtocolException e) {
        	authentication = new AuthenticationResponse();
			authentication.setMessage("ClientProtocolException");
        } catch (IOException e) {
        	authentication = new AuthenticationResponse();
			authentication.setMessage("IOException");
        }
        
        return authentication;
	}

}