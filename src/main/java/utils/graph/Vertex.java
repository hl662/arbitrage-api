package utils.graph;

import utils.Currency;

public class Vertex {
  String currency;

  // Constructor
  public Vertex(String newCurrency) {
    this.currency = newCurrency;
  }

  public static Vertex parseCurrencyToVertex(Currency currency) {
    return new Vertex(currency.base);
  }

  @Override
  public String toString() {
    return this.currency;
  }
}
