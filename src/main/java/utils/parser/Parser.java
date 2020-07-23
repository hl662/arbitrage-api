package utils.parser;

import org.json.JSONObject;

import utils.Currency;

import java.util.HashMap;

public class Parser {
  public static Currency parseJSONCurrency(String jsonString) {
    JSONObject currency = parseJSONData(jsonString);
    return new Currency(currency.getString("base"), parseRates(currency.getJSONObject("rates")));
  }
  public static JSONObject parseJSONData(String jsonString) {
    return new JSONObject(jsonString);
  }

  public static HashMap<String, Double> parseRates(JSONObject jsonRates) {
      HashMap<String, Double> result = new HashMap<String, Double>();
      for (String targetCurrency : jsonRates.keySet()) {
        result.put(targetCurrency, jsonRates.getDouble(targetCurrency));
      }
      return result;
  }

  public static void main(String[] args) {
    String jsonTest = "{\"rates\":{\"CAD\":1.7099550275,\"HKD\":9.8499805674,\"ISK\":176.8918993948,\"PHP\":62.6894675476,\"DKK\":8.2670590195,\"HUF\":389.362056521,\"CZK\":29.3476208983,\"GBP\":1.0,\"RON\":5.3736050192,\"SEK\":11.3697185054,\"IDR\":18688.579201599,\"INR\":94.8092832158,\"BRL\":6.7087890733,\"RUB\":90.037532619,\"HRK\":8.3615568264,\"JPY\":136.250069402,\"THB\":40.1909943923,\"CHF\":1.1926045195,\"EUR\":1.1104325135,\"MYR\":5.4149131087,\"BGN\":2.1717839098,\"TRY\":8.7023485648,\"CNY\":8.8814613292,\"NOK\":11.6521014935,\"NZD\":1.9216034645,\"ZAR\":20.9656321137,\"USD\":1.2706679252,\"MXN\":28.4170784521,\"SGD\":1.7645883071,\"AUD\":1.7937926822,\"ILS\":4.3531175393,\"KRW\":1519.0272611182,\"PLN\":4.9261007162},\"base\":\"GBP\",\"date\":\"2020-07-21\"}\n";
    System.out.println(parseJSONCurrency(jsonTest));
  }
}
