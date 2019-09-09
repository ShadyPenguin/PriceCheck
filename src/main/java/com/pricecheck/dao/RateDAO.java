package com.pricecheck.dao;

import com.pricecheck.model.Rates;

public interface RateDAO {
  /**
   * @return cached {@link Rates}
   */
  Rates getAll();

  /**
   * overwrites stored {@link Rates} with passed rates
   */
  void update(Rates rates);
}
