package us.roob.googl;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class Googl {

    private static final String apiKey = "12345";

    public static String shortenURL(String urlToShorten) {

	String urlParameters = "{\"longUrl\": \"" + urlToShorten + "\"}";
	String apiURL = "https://www.googleapis.com/urlshortener/v1/url?key=" + apiKey;
	BufferedReader rd = null;
	StringBuilder sb = null;
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

	    DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
	    wr.writeBytes(urlParameters);
	    wr.flush();
	    wr.close();
	    connection.getInputStream();
	    // read the result from the server
	    rd = new BufferedReader(new InputStreamReader(connection.getInputStream()));
	    sb = new StringBuilder();

	    while ((line = rd.readLine()) != null) {
		sb.append(line + '\n');
	    }

	    System.out.println(sb.toString());

	    connection.disconnect();

	} catch (Exception e) {

	}
	return "";
    }

    public static String expandURL(String url) {

	return "";
    }

}
