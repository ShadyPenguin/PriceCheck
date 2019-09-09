package com.pricecheck.dao.impl;

import com.pricecheck.dao.RateDAO;
import com.pricecheck.model.Rates;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class RateDAOImpl implements RateDAO {
  private Rates rates;

  @Inject
  RateDAOImpl(Rates rates) {
    this.rates = rates;
  }

  @Override
  public Rates getAll() {
    return rates;
  }

  @Override
  public void update(Rates rates) {
    this.rates = rates;
  }
}
