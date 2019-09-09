package com.pricecheck.model;

import java.util.List;

/**
 * Container class for all {@link Rate}s
 * @author Jake Sikora
 * @since 09/2019
 */
public class Rates {
  private List<Rate> rates;

  public List<Rate> getRates() {
    return rates;
  }

  public void setRates(List<Rate> rates) {
    this.rates = rates;
  }
}