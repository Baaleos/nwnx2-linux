package org.baaleos.systems.lang;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import com.google.gson.*;

public class Apertium {

	//https://www.apertium.org/apy/translate?langpair=eng|spa&q=Hello+my+name+is+Jonathan
	@SuppressWarnings("deprecation")
	public static String getTranslation(String from, String to, String strQuery){
		strQuery = URLEncoder.encode(strQuery);
		String url = "https://www.apertium.org/apy/translate?langpair="+from+"|"+to+"&q="+strQuery;
		try {
			TranslationResponse t = sendGet(url);
			String s = t.getResponseData().getTranslatedText();
			return s;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
	
	public static final String USER_AGENT = "Mozilla/5.0";
	// HTTP GET request
		private static TranslationResponse sendGet(String url) throws IOException {

			//String url = "http://www.google.com/search?q=mkyong";
			
			URL obj = new URL(url);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();

			// optional default is GET
			con.setRequestMethod("GET");

			//add request header
			con.setRequestProperty("User-Agent", USER_AGENT);

			int responseCode = con.getResponseCode();
			System.out.println("\nSending 'GET' request to URL : " + url);
			System.out.println("Response Code : " + responseCode);

			BufferedReader in = new BufferedReader(
			        new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			//print result
			System.out.println(response.toString());

			Gson gson = new GsonBuilder().create();
            TranslationResponse p = gson.fromJson(response.toString(), TranslationResponse.class);
            return p; 
		}
	
	
}
