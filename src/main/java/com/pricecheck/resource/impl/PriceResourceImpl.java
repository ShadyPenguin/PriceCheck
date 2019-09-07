package com.pricecheck.resource.impl;

import com.pricecheck.resource.PriceResource;
import com.pricecheck.service.PriceService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/price")
@Produces(MediaType.APPLICATION_JSON)
public class PriceResourceImpl implements PriceResource {

  private PriceService priceService;

  @Inject
  PriceResourceImpl(PriceService priceService) {
    this.priceService = priceService;
  }

  @GET
  public String find() {
    return priceService.find();
  }
}
