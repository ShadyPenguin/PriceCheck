package com.pricecheck.dao.impl;

import com.pricecheck.dao.RateDAO;
import com.pricecheck.model.Rate;
import com.pricecheck.model.Rates;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.Instant;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Implementation for Rate DAO layer to update and retrieve {@link Rate}s from it's in-memory store
 * @author Jake Sikora
 * @since 09/2019
 */
@Singleton
public class RateDAOImpl implements RateDAO {
  private DateTimeFormatter DAY = DateTimeFormat.forPattern("E");
  private Rates rates;

  @Inject
  RateDAOImpl(Rates rates) {
    this.rates = rates;
  }

  /**
   * @return cached in-memory {@link Rates}
   */
  @Override
  public Rates getAll() {
    return rates;
  }

  /**
   * Overwrites stored {@link Rates} with passed rates
   */
  @Override
  public void update(Rates rates) {
    this.rates = rates;
  }

  /**
   * Validate time range and find {@link Rate} for given time range
   * @param start Lower bound for {@link Rate} time range
   * @param end Upper bound for {@link Rate} time range
   * @return price for given time range or "unavailable" if time range is unsupported
   */
  @Override
  public String get(String start, String end) {
    if (isValid(DateTime.parse(start), DateTime.parse(end))) {
      return "something";
    }
    return "unavailable";
  }

  private boolean isValid(DateTime start, DateTime end) {
    return Days.daysBetween(start.toLocalDate(), end.toLocalDate()).isLessThan(Days.ONE);
  }
}
