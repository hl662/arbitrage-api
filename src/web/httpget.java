package web;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;
public class Httpget {

    // TODO: research java.org.JSONObject;
    public static final String baseExchangeRateURL = "https://api.exchangeratesapi.io/latest"; // By default, gets the base EUR.
    // TODO: Add a "?base={currentCurrency}" and do a for each in the currencies loop
    public static final String[] currencies = {"EUR","CAD","GBP","JPY","AUD","USD","HKD","ISK","PHP","DKK","HUF","CZK","AUD","RON","SEK"};
    /**
     *  Sends a HTTP Request GET to fetch the latest currency rates in string formatted JSON. 
     */
    public static String sendGET(String url) throws IOException {
        URL getURL = new URL(url);
        HttpsURLConnection con = (HttpsURLConnection) getURL.openConnection();
        con.setRequestMethod("GET");
        int responseCode = con.getResponseCode();
        if (responseCode == HttpsURLConnection.HTTP_OK) {
            BufferedReader buffer = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = buffer.readLine()) != null) {
                response.append(inputLine);
            }
            buffer.close();
            return response.toString();
        } else {
            StringBuffer error = new StringBuffer();
            error.append(con.getResponseCode() + ": ");
            error.append(con.getResponseMessage() + "\n");
            return error.toString();
        }
    }

    public static void main(String[] args) throws IOException{
        System.out.println(Httpget.sendGET(Httpget.baseExchangeRateURL));
    }
}