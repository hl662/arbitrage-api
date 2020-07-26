package web;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import javax.net.ssl.HttpsURLConnection;
import utils.Currency;
import utils.parser.Parser;

public class HttpGet {

    public static final String baseExchangeRateURL = "https://api.exchangeratesapi.io/latest"; // By default, gets the base EUR.
    public static final String[] currencies = {"HRK", "CHF", "MXN", "ZAR", "INR", "THB", "CNY", "AUD", "ILS", "KRW", "JPY", "PLN", "GBP", "IDR", "HUF", "PHP", "TRY", "RUB", "HKD", "ISK", "DKK", "CAD", "USD", "MYR", "BGN", "NOK", "RON", "SGD", "CZK", "SEK", "NZD", "BRL", "GBP"};
    /**
     *  Sends a HTTP Request GET to fetch the latest currency rates in string formatted JSON.
     *  Uses the 'base' query param of the url to determine which
     */
    public static String fetchCurrency(String url) throws IOException {
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

    public static ArrayList<Currency> fetchAllLatestCurrencies() throws IOException {
        ArrayList<Currency> result = new ArrayList<Currency>();
        for (String currency : HttpGet.currencies) {
            String url = HttpGet.baseExchangeRateURL.concat(String.format("?base=%s", currency));
            result.add(Parser.parseJSONCurrency(fetchCurrency(url)));
        }
        return result;
    }

    public static void main(String[] args) throws IOException{
        System.out.println(HttpGet.fetchAllLatestCurrencies());
    }
}