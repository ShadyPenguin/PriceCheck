package com.pricecheck.service.impl;

import com.pricecheck.dao.RateDAO;
import com.pricecheck.model.Rates;
import com.pricecheck.service.RateService;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("rates")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Singleton
public class RateServiceImpl implements RateService {
  private final RateDAO rateDAO;

  @Inject
  RateServiceImpl(RateDAO rateDAO) {
    this.rateDAO = rateDAO;
  }

  @POST
  @Override
  public void update(Rates rates) {
    rateDAO.update(rates);
  }

  @GET
  @Override
  public String findRate(@QueryParam("start") String start, @QueryParam("end") String end) {
    return String.valueOf(rateDAO.getAll().getRates().get(0).getPrice());
  }

}
