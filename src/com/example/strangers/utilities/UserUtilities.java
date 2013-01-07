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

import android.content.Context;
import android.util.Base64;

import com.example.strangers.R;

/**
 * Classe contenant les m�thodes pour appeler les services li�s aux utilisateurs.
 * @author Xavier
 *
 */
public class UserUtilities {
	
	public static Integer inscription(Context context, String login, String password) {
			
		String baseUrl = context.getString(R.string.service_base_url_https);
		String registrationService = context.getString(R.string.new_user);		
		
		HttpClient httpClient = MySSLSocketFactory.getNewHttpClient();
		//HttpClient httpclient = new DefaultHttpClient();
        
		HttpPost httppost = new HttpPost(baseUrl+registrationService);
        
        Integer status = null;
        
        try {
            // Add your data
            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
            nameValuePairs.add(new BasicNameValuePair("user[login]", login));
            nameValuePairs.add(new BasicNameValuePair("user[password]", password));
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

            // Execute HTTP Post Request
            HttpResponse response = httpClient.execute(httppost);
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
	
	public static Integer suppression(Context context, String login, String password) {

		
		String baseUrl = context.getString(R.string.service_base_url_https);
		String userdeletionService = context.getString(R.string.delete_user);
		
		HttpClient httpClient = MySSLSocketFactory.getNewHttpClient();
		//HttpClient httpclient = new DefaultHttpClient();
        HttpDelete httpdelete = new HttpDelete(baseUrl+userdeletionService);
        
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
	
	public static Integer verification(Context context, String login, String password) {
		String baseUrl = context.getString(R.string.service_base_url_https);
		String usercheckService = context.getString(R.string.check_user);
		
		HttpClient httpClient = MySSLSocketFactory.getNewHttpClient();
		//HttpClient httpclient = new DefaultHttpClient();
        HttpGet httpget = new HttpGet(baseUrl+usercheckService);
        
        String authParams = login+":"+password;
        httpget.setHeader("Authorization", "Basic "+Base64.encodeToString(authParams.getBytes(), Base64.NO_WRAP));
		
		Integer status = null;
		
		try {
            HttpResponse response = httpClient.execute(httpget);
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
