package com.pricecheck.service;

import com.pricecheck.model.Rate;
import com.pricecheck.model.Rates;

public interface RateService {
  /**
   * Ingest {@link Rates} and update internal cache with new rates
   */
  void update(Rates rates);

  /**
   * Query internal {@link Rates} cache for given time range to find the {@link Rate#getPrice()}
   */
  String findRate(String start, String end);
}
