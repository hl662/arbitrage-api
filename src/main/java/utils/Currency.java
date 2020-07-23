package utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Currency {
    String base;
    HashMap<String, Double> rates; // Use this to construct the edge.

    @Override
    public String toString() {
        StringBuffer result = new StringBuffer();
        result.append(String.format("\nBase: %s\nRates:\n", this.base));
        for (String currency : this.rates.keySet()) {
            result.append(String.format("\t%s: %.6f\n", currency, this.rates.get(currency)));
        }
        return result.toString();
    }

    public ArrayList<String> getAvailableCurrencies() {
        ArrayList<String> result = new ArrayList<String>();
        result.add(this.base);
        for (String currency : this.rates.keySet()) {
            result.add(currency);
        }
        return result; // Use toString() to print out result later.
    }

    public Currency(String base, HashMap<String, Double> rates) {
        this.base = base;
        this.rates = rates;
    }
}