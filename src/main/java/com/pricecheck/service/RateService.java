package com.pricecheck.service;

import com.pricecheck.model.Rate;
import com.pricecheck.model.Rates;

/**
 * Interface for services that update and find time-based {@link Rate}s
 * @author Jake Sikora
 * @since 09/2019
 */
public interface RateService {
  /**
   * Returns all {@link Rate}s stored in memory
   */
  Rates getAll();

  /**
   * Ingest {@link Rates} and update internal cache with new rates
   */
  void update(Rates rates);

  /**
   * Query internal {@link Rates} cache for given time range to find the {@link Rate#getPrice()}
   */
  String get(String start, String end);
}
