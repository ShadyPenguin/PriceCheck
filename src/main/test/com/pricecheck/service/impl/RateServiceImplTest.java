package com.pricecheck.service.impl;

import com.pricecheck.dao.RateDAO;
import com.pricecheck.dao.impl.RateDAOImpl;
import com.pricecheck.model.Rates;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class RateServiceImplTest extends Mockito {

  private RateServiceImpl rateService;
  @Mock RateDAO rateDAO;
  @Mock Rates rates;

  @Before
  public void setUp() {
    rateService = new RateServiceImpl(rateDAO);
  }

  @Test
  public void update() {
    rateService.update(rates);
    verify(rateDAO).update(rates);
  }

  @Test
  public void findRate() {
  }
}