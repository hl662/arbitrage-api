import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Currency {
    String base;
    HashMap<String, double[]> rates;

    public ArrayList<String> getAvailableCurrencies() {
        ArrayList<String> result = new ArrayList<String>();
        result.add(this.base);
        for (String currency : this.rates.keySet()) {
            result.add(currency);
        }
        return result; // Use toString() to print out result later.
    }
}