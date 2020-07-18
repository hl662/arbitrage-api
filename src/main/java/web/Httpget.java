package web;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;
import org.json.JSONObject;
//import org.json.JSONArray;
public class Httpget {

    // TODO: research java.org.JSONObject;
    public static final String baseExchangeRateURL = "https://api.exchangeratesapi.io/latest"; // By default, gets the base EUR.
    // TODO: Add a "?base={currentCurrency}" and do a for each in the currencies loop
    public static final String[] currencies = {"HRK", "CHF", "MXN", "ZAR", "INR", "THB", "CNY", "AUD", "ILS", "KRW", "JPY", "PLN", "GBP", "IDR", "HUF", "PHP", "TRY", "RUB", "HKD", "ISK", "DKK", "CAD", "USD", "MYR", "BGN", "NOK", "RON", "SGD", "CZK", "SEK", "NZD", "BRL", "GBP"};
    /**
     *  Sends a HTTP Request GET to fetch the latest currency rates in string formatted JSON. 
     */
    public static String fetchLatestRates(String url) throws IOException {
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

    public static void getAllLatestCurrencies() throws IOException {
        for (String currency : currencies) {
            String url = Httpget.baseExchangeRateURL.concat(String.format("?base=%s", currency));
            System.out.println(Httpget.fetchLatestRates(url));
        }
    }

    // Move to separate class file later.
    public static JSONObject parseJSONData(String jsonString) {
        return new JSONObject(jsonString);
    }

    public static void main(String[] args) throws IOException{
//        String jsonString = Httpget.fetchLatestRates(Httpget.baseExchangeRateURL);
//        JSONObject jsonObj = Httpget.parseJSONData(jsonString);
//        JSONObject rates = (JSONObject) jsonObj.get("rates");
//        System.out.println(rates.keySet());
        Httpget.getAllLatestCurrencies();
    }
}