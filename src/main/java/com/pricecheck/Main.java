package com.pricecheck;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;
import com.google.inject.servlet.ServletModule;
import com.pricecheck.resource.PriceResource;
import com.pricecheck.resource.impl.PriceResourceImpl;
import com.pricecheck.service.PriceService;
import com.pricecheck.service.impl.PriceServiceImpl;
import com.sun.jersey.api.core.PackagesResourceConfig;
import com.sun.jersey.api.core.ResourceConfig;
import com.sun.jersey.guice.spi.container.servlet.GuiceContainer;

public class Main extends GuiceServletContextListener {

  @Override
  protected Injector getInjector() {
    return Guice.createInjector(
        new ServletModule() {
          @Override
          protected void configureServlets() {
            bind(PriceResource.class).to(PriceResourceImpl.class);
            bind(PriceService.class).to(PriceServiceImpl.class);

            ResourceConfig resourceConfig = new PackagesResourceConfig("com.pricecheck.resource");
            for (Class<?> resource : resourceConfig.getClasses()) {
              bind(resource);
            }

            serve( "/*" ).with( GuiceContainer.class );
          }
        }
    );
  }
}
