package com.pricecheck.service.impl;

import com.pricecheck.dao.RateDAO;
import com.pricecheck.model.Rate;
import com.pricecheck.model.Rates;
import com.pricecheck.service.RateService;
import org.joda.time.DateTime;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * Implementation for service that updates and finds time-based {@link Rate}s
 * @author Jake Sikora
 * @since 09/2019
 */
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

  /**
   * Return all rates stored in memory
   */
  @GET
  @Path("/all")
  @Override
  public Rates getAll() {
    return rateDAO.getAll();
  }

  /**
   * Ingest {@link Rates} and update internal cache with new rates
   * @param rates Object containing all {@link Rate}s
   */
  @POST
  @Override
  public void update(Rates rates) {
    rateDAO.update(rates);
  }

  /**
   * Query internal {@link Rates} cache for given time range to find the {@link Rate#getPrice()}
   * @param start Beginning of time range for price check
   * @param end End of time range for price check
   * @return price for given time range or "unavailable" if time range is unsupported
   */
  @GET
  @Override
  public String get(@QueryParam("start") String start, @QueryParam("end") String end) {
    return rateDAO.get(DateTime.parse(start), DateTime.parse(end));
  }
}
