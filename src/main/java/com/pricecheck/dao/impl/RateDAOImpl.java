package com.pricecheck.dao.impl;

import com.pricecheck.dao.RateDAO;
import com.pricecheck.model.Rate;
import com.pricecheck.model.Rates;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.Days;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.Integer.parseInt;

/**
 * Implementation for Rate DAO layer to update and retrieve {@link Rate}s from it's in-memory store
 * @author Jake Sikora
 * @since 09/2019
 */
@Singleton
public class RateDAOImpl implements RateDAO {
  private static final DateTimeFormatter DAY = DateTimeFormat.forPattern("E");
  private static final DateTimeFormatter TIME = DateTimeFormat.forPattern("Hmm");
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
  public String get(DateTime start, DateTime end) {
    if (isOneDay(start, end)) {
      List<Rate> availableRates = rates.getRates().stream()
          // Filter Rates with the correct day
          .filter(r -> r.getDays().contains(start.withZone(DateTimeZone.forID(r.getTz())).toString(DAY).toLowerCase()))
          // Filter Rates with lower bound correct
          .filter(r -> parseInt(r.getTimes().split("-")[0]) <= parseInt(start.withZone(DateTimeZone.forID(r.getTz())).toString(TIME)))
          // Filter Rates with upperr bound correct
          .filter(r -> parseInt(r.getTimes().split("-")[1]) >= parseInt(end.withZone(DateTimeZone.forID(r.getTz())).toString(TIME)))
          .collect(Collectors.toList());
      if (availableRates.size() == 1) {
        return String.valueOf(availableRates.get(0).getPrice());
      }
    }
    return "unavailable";
  }

  private boolean isOneDay(DateTime start, DateTime end) {
    return Days.daysBetween(start.toLocalDate(), end.toLocalDate()).isLessThan(Days.ONE);
  }
}
