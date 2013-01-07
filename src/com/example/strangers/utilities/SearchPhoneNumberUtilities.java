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
import com.example.strangers.model.SearchResponse;

public class SearchPhoneNumberUtilities {

	public static ArrayList<SearchResponse> addMailBox(Context context, String currentUserLogin, String currentUserPassword, String phoneNumber) {
				
		String baseUrl = context.getString(R.string.service_base_url_https);
		String registrationService = context.getString(R.string.search_number);
		
		HttpClient httpClient = MySSLSocketFactory.getNewHttpClient();
		//HttpClient httpclient = new DefaultHttpClient();
        HttpPost httppost = new HttpPost(baseUrl+registrationService);
        
        //Add http basic auth
        String authParams = currentUserLogin+":"+currentUserPassword;
        httppost.setHeader("Authorization", "Basic "+Base64.encodeToString(authParams.getBytes(), Base64.NO_WRAP));
                
        ArrayList<SearchResponse> searchResponseList = new ArrayList<SearchResponse>();
        
        try {
            // Add your data
            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
            nameValuePairs.add(new BasicNameValuePair("number", phoneNumber));
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

            // Execute HTTP Post Request
            HttpResponse response = httpClient.execute(httppost);
                     
            
            
            //get the response as a string
            String jsonString = EntityUtils.toString(response.getEntity());
            
			
            
            try {
                //create the json object
                JSONObject jsonObjectResponse;
				jsonObjectResponse = new JSONObject(jsonString);
				Log.v("json", jsonObjectResponse.toString());
				
				SearchResponse searchResponse = new SearchResponse(jsonObjectResponse.getString("before"),
																	jsonObjectResponse.getString("number"),
																	jsonObjectResponse.getString("after"),
																	jsonObjectResponse.getString("from"),
																	jsonObjectResponse.getString("date"),
																	jsonObjectResponse.getString("account"),
																	jsonObjectResponse.getString("status"));
				
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			/*try {
				//construct the json object with the response String
				

				//fill the list of messages
				for(int i=0; i<jsonArrayListPhoneResponse.length(); i++) {
					
					JSONObject jsonObjectSearchPhoneResponse = jsonArrayListPhoneResponse.getJSONObject(i);

					SearchResponse searchResponse = new SearchResponse(jsonObjectSearchPhoneResponse.getString("responseMailAccount"), 
																		jsonObjectSearchPhoneResponse.getString("responseMailExpeditor"), 
																		jsonObjectSearchPhoneResponse.getString("responseMailDate"), 
																		jsonObjectSearchPhoneResponse.getString("responseMailContent"), 
																		jsonObjectSearchPhoneResponse.getString("responseMailShortContent"));
					
					searchResponseList.add(searchResponse);
				}
				//construct the authentication object with the json object
				
				
			} catch (JSONException e) {
				//TODO error response
 			}
			
			/////////////*/
            
            
        } catch (ClientProtocolException e) {
			//TODO complete exception
        } catch (IOException e) {
			//TODO complete exception
        } catch (SecurityException e) {
        	
        }
        
        return searchResponseList;
	}
}
