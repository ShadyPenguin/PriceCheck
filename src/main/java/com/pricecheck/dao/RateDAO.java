package com.pricecheck.dao;

import com.pricecheck.model.Rate;
import com.pricecheck.model.Rates;
import org.joda.time.DateTime;

/**
 * Interface for Rate DAO layer
 * @author Jake Sikora
 * @since 09/2019
 */
public interface RateDAO {
  /**
   * @return cached in-memory {@link Rates}
   */
  Rates getAll();

  /**
   * Overwrites stored {@link Rates} with passed rates
   */
  void update(Rates rates);

  /**
   * Search for a {@link Rate} encapsulating the given time range
   */
  String get(DateTime start, DateTime end);
}
