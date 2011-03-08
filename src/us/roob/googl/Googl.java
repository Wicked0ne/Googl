package us.roob.googl;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

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

    public static String expandURL(String urlToExpand) {
	String expandedURL = "";
	
	try {
            // Convert spaces to +, etc. to make a valid URL
            String queryEncoded = URLEncoder.encode(urlToExpand, "UTF-8");
            URL webUrl = new URL("https://www.googleapis.com/urlshortener/v1/url?shortUrl=" + queryEncoded);
//            System.out.println("http://api.bit.ly/v3/shorten?login=" + APIUSER + "&apiKey=" + APIKEY + "&longUrl=" + queryEncoded + "&format=json");
//            URL webUrl = new URL("http://api.bit.ly/shorten?version=2.0.1&longUrl=" + queryEncoded + "&login=" + APIUSER + "&apiKey=" + APIKEY);
            
            URLConnection connection = webUrl.openConnection();

            // Get the JSON response
            String line;
            StringBuilder builder = new StringBuilder();
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            //reads everything in
            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }

            //builds string from input
            String response = builder.toString();
            //creates json object from input string
            JSONObject json = new JSONObject(response);

            //assembles the finalString from the json object. for api v2.something
//            finalString = json.getJSONObject("results").getJSONObject(query).getString("shortUrl");

            //for api 3.something
            expandedURL = json.getString("longUrl");

        } catch (Exception e) {
            System.err.println("Something went wrong with trying to expand the url...");
            e.printStackTrace();
        }
	

	/*String urlParameters = "{\"longUrl\": \"" + urlToExpand + "\"}";
	String apiURL = "https://www.googleapis.com/urlshortener/v1/url?key=" + apiKey;
	BufferedReader buffrdr = null;
	StringBuilder stringbldr = null;
	String line = null;
	

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
	    expandedURL = jObject.getString("id");
	    
	    //close the connection...no shit....
	    connection.disconnect();

	} catch (Exception e) {
	    System.out.println(e);
	}*/
        
	return expandedURL;
	
    }

}
