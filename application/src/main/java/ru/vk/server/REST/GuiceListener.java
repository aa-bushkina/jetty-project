package ru.vk.server.REST;

import com.google.inject.AbstractModule;
import com.google.inject.Module;
import org.jboss.resteasy.plugins.guice.GuiceResteasyBootstrapServletContextListener;

import javax.servlet.ServletContext;
import java.util.Collections;
import java.util.List;

@SuppressWarnings("NotNullNullableValidation")
public final class GuiceListener extends GuiceResteasyBootstrapServletContextListener {

  @Override
  protected List<? extends Module> getModules(ServletContext context) {
    return Collections.singletonList(new GuiceModule());
  }

  private static final class GuiceModule extends AbstractModule {
    @SuppressWarnings("PointlessBinding")
    @Override
    protected void configure() {
      bind(QueryREST.class);
      bind(ProductREST.class);
    }
  }
}