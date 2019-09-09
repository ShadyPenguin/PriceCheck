package com.pricecheck.dao.impl;

import com.pricecheck.dao.RateDAO;
import com.pricecheck.model.Rate;
import com.pricecheck.model.Rates;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;

import static org.junit.Assert.assertEquals;

public class RateDAOImplTest {

  private static DateTime START = DateTime.parse( "2015-07-01T15:00:00Z");
  private static DateTime END = DateTime.parse( "2015-07-01T17:00:00Z");
  private static String UNAVAILABLE = "unavailable";
  private RateDAO rateDAO;
  private Rates rates;
  private Rate rate;

  @Before
  public void setUp() {
    rateDAO = new RateDAOImpl(rates);
    rates = new Rates();
    rate = new Rate();
    rate.setDays("weds");
    rate.setTimes("1000-1200");
    rate.setTz("America/Chicago");
    rate.setPrice(100L);
    rates.setRates(Collections.singletonList(rate));
    rateDAO.update(rates);
  }

  @Test
  public void getAll() {
    assertEquals(rates, rateDAO.getAll());
    assertEquals(1L, rateDAO.getAll().getRates().size());
  }

  @Test
  public void update() {
    Rates updatedRates = new Rates();
    assertEquals(rates, rateDAO.getAll());
    rateDAO.update(updatedRates);
    assertEquals(updatedRates, rateDAO.getAll());
  }

  @Test
  public void get_rangeAcrossMoreThanOneDay_fail() {
    assertEquals(UNAVAILABLE, rateDAO.get(START, END.plusDays(1)));
  }

  @Test
  public void get_wrongDate_fail() {
    assertEquals(UNAVAILABLE, rateDAO.get(START.plusDays(1), END.plusDays(1)));
  }

  @Test
  public void get_startBeforeRate_fail() {
    assertEquals(UNAVAILABLE, rateDAO.get(START.minusMinutes(5), END));
  }

  @Test
  public void get_endAfterRate_fail() {
    assertEquals(UNAVAILABLE, rateDAO.get(START, END.plusMinutes(5)));
  }

  @Test
  public void get_success() {
    assertEquals("100", rateDAO.get(START, END));
  }
}