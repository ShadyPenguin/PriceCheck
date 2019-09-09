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

/**
 * Setting up all servlets and Guice bindings for the application
 * @author Jake Sikora
 * @since 09/2019
 */
public class PriceCheck extends GuiceServletContextListener {

  @Override
  protected Injector getInjector() {
    return Guice.createInjector(
        new ServletModule() {
          /**
           * Configuring servlets
           */
          @Override
          protected void configureServlets() {
            ResourceConfig resourceConfig = new PackagesResourceConfig("com.pricecheck.service");
            for (Class<?> resource : resourceConfig.getClasses()) {
              bind(resource);
            }
            serve("/*").with(GuiceContainer.class);
          }
        }, new AbstractModule() {
          /**
           * Setting up Guice bindings
           */
          @Override
          protected void configure() {
            bind(RateService.class).to(RateServiceImpl.class);
            bind(RateDAO.class).to(RateDAOImpl.class);
            bind(JacksonJsonProvider.class).in(Scopes.SINGLETON);
            bind(ObjectMapper.class).in(Scopes.SINGLETON);
          }

          /**
           * Providing the system with a known set of {@link Rates} that would normally be supplied from a database
           */
          @Provides
          private Rates providesRateCache() throws IOException {
            File f = new File(getClass().getClassLoader().getResource("data.json").getFile());
            return new ObjectMapper().readValue(f, Rates.class);
          }
        }
    );
  }
}
