package com.pricecheck;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import com.google.inject.*;
import com.google.inject.servlet.GuiceServletContextListener;
import com.google.inject.servlet.ServletModule;
import com.pricecheck.dao.RateDAO;
import com.pricecheck.dao.impl.RateDAOImpl;
import com.pricecheck.model.Rates;
import com.pricecheck.service.RateService;
import com.pricecheck.service.impl.RateServiceImpl;
import com.sun.jersey.api.core.PackagesResourceConfig;
import com.sun.jersey.api.core.ResourceConfig;
import com.sun.jersey.guice.spi.container.servlet.GuiceContainer;

import java.io.File;
import java.io.IOException;

public class PriceCheck extends GuiceServletContextListener {

  @Override
  protected Injector getInjector() {
    return Guice.createInjector(
        new ServletModule() {
          @Override
          protected void configureServlets() {
            ResourceConfig resourceConfig = new PackagesResourceConfig("com.pricecheck.service");
            for (Class<?> resource : resourceConfig.getClasses()) {
              bind(resource);
            }
            serve("/*").with(GuiceContainer.class);
          }
        }, new AbstractModule() {
          @Override
          protected void configure() {
            bind(RateService.class).to(RateServiceImpl.class);
            bind(RateDAO.class).to(RateDAOImpl.class);
            bind(JacksonJsonProvider.class).in(Scopes.SINGLETON);
            bind(ObjectMapper.class).in(Scopes.SINGLETON);
          }

          @Provides
          private Rates providesRateCache() throws IOException {
            File f = new File(getClass().getClassLoader().getResource("data.json").getFile());
            return new ObjectMapper().readValue(f, Rates.class);
          }
        }
    );
  }
}
