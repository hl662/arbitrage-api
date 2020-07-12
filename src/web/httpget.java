package web;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;
public class httpget {

    public static final String exchangeRateURL = "https://api.exchangeratesapi.io/latest";
    
    // TODO: Create file called json-parser (unless java comes builtin with one)
    public static void sendGET() throws IOException {
        URL getURL = new URL(exchangeRateURL);
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
            System.out.println(response);
        } else {
            System.out.println(con.getResponseCode());
            System.out.println(con.getResponseMessage());
        }
    }

    public static void main(String[] args) throws IOException{
        httpget.sendGET();
    }
}