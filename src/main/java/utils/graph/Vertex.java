package utils.graph;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import utils.Currency;

public class Vertex {
  String currency;
  double minimumWeight;
  // Constructor
  public Vertex(String newCurrency) {
    this.currency = newCurrency;
    this.minimumWeight = 99.0; // This will be regarded as infinity.
  }

  public String getCurrency() {
    return currency;
  }

  public double getMinimumWeight() {
    return this.minimumWeight;
  }

  public void setMinimumWeight(double minimumWeight) {
    this.minimumWeight = minimumWeight;
  }

  public static Vertex parseCurrencyToVertex(Currency currency) {
    return new Vertex(currency.base);
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder(19, 31).append(this.currency).toHashCode();
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof Vertex)) {
      return false;
    }
    if (obj == this) {
      return true;
    }
    Vertex compVertex = (Vertex) obj;
    return new EqualsBuilder().append(this.currency, compVertex.currency).isEquals();
  }
  @Override
  public String toString() {
    return this.currency;
  }
}
