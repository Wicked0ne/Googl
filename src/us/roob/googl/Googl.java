package us.roob.googl;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import json.JSONObject;


public class Googl {

    private static final String apiKey = "12345";

    public static String shortenURL(String urlToShorten) {

	String urlParameters = "{\"longUrl\": \"" + urlToShorten + "\"}";
	String apiURL = "https://www.googleapis.com/urlshortener/v1/url?key=" + apiKey;
	BufferedReader buffrdr = null;
	StringBuilder stringbldr = null;
	String line = null;
	String shortURL = "";

	try {
	    URL url = new URL(apiURL);
	    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
	    connection.setDoOutput(true);
	    connection.setDoInput(true);
	    connection.setInstanceFollowRedirects(false);
	    connection.setRequestMethod("POST");
	    connection.setRequestProperty("Content-Type", "application/json");
	    // connection.setRequestProperty("charset", "utf-8");
	    connection.setRequestProperty("Content-Length", "" + Integer.toString(urlParameters.getBytes().length));
	    connection.setUseCaches(false);

	    //write data to server.
	    DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
	    wr.writeBytes(urlParameters);
	    wr.flush();
	    wr.close();
	    
	    //get data back from the server
	    connection.getInputStream();
	    // read the result from the server
	    buffrdr = new BufferedReader(new InputStreamReader(connection.getInputStream()));
	    stringbldr = new StringBuilder();

	    while ((line = buffrdr.readLine()) != null) {
		stringbldr.append(line + '\n');
	    }

	    System.out.println(stringbldr.toString());
	    
	    JSONObject jObject  = new JSONObject(stringbldr.toString());
//	    shortURL = jObject.getJSONObject("id").toString();
	    shortURL = jObject.getString("id");
	    
	    //close the connection...no shit....
	    connection.disconnect();

	} catch (Exception e) {
	    System.out.println(e);
	}
	return shortURL;
    }

    public static String expandURL(String url) {

	return "";
    }

}
