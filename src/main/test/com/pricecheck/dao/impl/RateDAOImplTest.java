package com.pricecheck.dao.impl;

import com.pricecheck.dao.RateDAO;
import com.pricecheck.model.Rate;
import com.pricecheck.model.Rates;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;

import static org.junit.Assert.assertEquals;

public class RateDAOImplTest {

  private RateDAO rateDAO;
  private Rates rates;

  @Before
  public void setUp() {
    rates = new Rates();
    rateDAO = new RateDAOImpl(rates);
  }

  @Test
  public void getAll() {
    assertEquals(rates, rateDAO.getAll());
  }

  @Test
  public void update() {
    Rate rate = new Rate();
    Rates updatedRates = new Rates();
    updatedRates.setRates(Collections.singletonList(rate));
    assertEquals(rates, rateDAO.getAll());
    rateDAO.update(updatedRates);
    assertEquals(updatedRates, rateDAO.getAll());
  }
}