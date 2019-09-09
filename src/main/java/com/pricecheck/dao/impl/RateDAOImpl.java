package com.pricecheck.dao.impl;

import com.pricecheck.dao.RateDAO;
import com.pricecheck.model.Rate;
import com.pricecheck.model.Rates;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Implementation for Rate DAO layer to update and retrieve {@link Rate}s from it's in-memory store
 * @author Jake Sikora
 * @since 09/2019
 */
@Singleton
public class RateDAOImpl implements RateDAO {
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
}
