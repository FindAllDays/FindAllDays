package com.ift.slip.connect.url;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpRequestBase;

public class RESTInvoker {
    private final String baseUrl;
    private final String username;
    private final String password;
    private int socketTimeout = 10000;
   	private int connectTimeout= 10000;
   	private int connectionRequestTimeout=10000;
 
    public RESTInvoker(String baseUrl, String username, String password) {
        this.baseUrl = baseUrl;
        this.username = username;
        this.password = password;
    }
 
  /*  public String getRESTResponse(String accountId){
       getDataFromServer("account/" + accountId);
    }*/
 
    public String getDataFromServer(String path) {
        StringBuilder sb = new StringBuilder();
        try {
            URL url = new URL(baseUrl + path);
            URLConnection urlConnection = setUsernamePassword(url);
            BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            reader.close();
 
            return sb.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
 
    public String getDataFromServer(String path,Map<String,String>paras) {
        StringBuilder sb = new StringBuilder();
        try {
            URL url = new URL(baseUrl + path);
            URLConnection urlConnection = setUsernamePassword(url);
            
            for (Map.Entry<String, String> entry : paras.entrySet()) {
            	urlConnection.setRequestProperty(entry.getKey(), entry.getValue());
        	}
            
            BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            reader.close();
 
            return sb.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
   
	
	private void setTimeout(HttpRequestBase request) {
		RequestConfig requestConfig = RequestConfig.custom()
				  .setSocketTimeout(socketTimeout)
				  .setConnectTimeout(connectTimeout)
				  .setConnectionRequestTimeout(connectionRequestTimeout)
				  .build();
		request.setConfig(requestConfig);
	}
	
    
    private URLConnection setUsernamePassword(URL url) throws IOException {
        URLConnection urlConnection = url.openConnection();
        String authString = username + ":" + password;
        String authStringEnc = new String(Base64.encodeBase64(authString.getBytes()));
        urlConnection.setRequestProperty("Authorization", "Basic " + authStringEnc);
        return urlConnection;
    }
}